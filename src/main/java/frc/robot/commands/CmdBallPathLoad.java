package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;

public class CmdBallPathLoad extends SwartdogCommand 
{
    private final double PICKUP_DISABLE_TIME = 0.1;

    private Dashboard _dashboardSubsystem;
    private BallPath  _ballPathSubsystem;
    private Pickup    _pickupSubsystem;

    private int       _loadTimer;
    private int       _pickupDisableTimer;
    private double    _trackMotorSpeed;

    public CmdBallPathLoad(Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Pickup pickupSubsystem) 
    {
        _dashboardSubsystem = dashboardSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;
        _pickupSubsystem    = pickupSubsystem;

        _loadTimer          = 0;
        _trackMotorSpeed    = 0;

        //addRequirements(_ballPathSubsystem, _pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (!_ballPathSubsystem.isJammed() && 
            _ballPathSubsystem.getBallCount() < Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT)
        {
            _ballPathSubsystem.incrementBallCount();

            _loadTimer          = Math.max(0, (int)(50 * _dashboardSubsystem.getBallPathJamTime()));
            _pickupDisableTimer = Math.max(0, (int)(50 * PICKUP_DISABLE_TIME));
            _trackMotorSpeed    = _dashboardSubsystem.getBallPathRampMin();
        }

        else
        {
            _loadTimer       = 0;
            _trackMotorSpeed = 0;
        }
    }

    @Override
    public void execute() 
    {
        if (!_ballPathSubsystem.isJammed() && 
             _ballPathSubsystem.getBallCount() < Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT)
        {
            _loadTimer--;
            _pickupDisableTimer--;

            if (_pickupDisableTimer <= 0)
            {
                _pickupSubsystem.setLeftMotor(0);
                _pickupSubsystem.setPrimaryMotor(0);
                _pickupSubsystem.setRightMotor(0);        
            }

            if (_loadTimer > 0)
            {
                _trackMotorSpeed += _dashboardSubsystem.getBallPathRampStep();
                
                if (_trackMotorSpeed > _dashboardSubsystem.getBallPathSpeed())
                {
                    _trackMotorSpeed = _dashboardSubsystem.getBallPathSpeed();
                }
            }

            else
            {
                _trackMotorSpeed = 0;
                _ballPathSubsystem.setJammed(true);
            }

            _ballPathSubsystem.setTrackMotor(_trackMotorSpeed);
        }

        if (_ballPathSubsystem.shooterSensorTransitionedTo(State.Off))
        {
            _ballPathSubsystem.decrementBallCount();
        }

    }

    @Override
    public void end(boolean interrupted) 
    {
        _ballPathSubsystem.setTrackMotor(0);

        if (!_ballPathSubsystem.isJammed() && 
            _ballPathSubsystem.getBallCount() < Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT)
        {
            _pickupSubsystem.setLeftMotor(_dashboardSubsystem.getPickupSpeed());
            _pickupSubsystem.setPrimaryMotor(_dashboardSubsystem.getPickupSpeed());
            _pickupSubsystem.setRightMotor(_dashboardSubsystem.getPickupSpeed());
        }

        else if (_ballPathSubsystem.isUpperTrackRaised())
        {
            _pickupSubsystem.stowPickup();
        }

        if (_ballPathSubsystem.getBallCount() >= Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT)
        {
            _pickupSubsystem.setLeftMotor(0);
            _pickupSubsystem.setPrimaryMotor(0);
            _pickupSubsystem.setRightMotor(0);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return _ballPathSubsystem.isJammed() || 
               _ballPathSubsystem.position2SensorTransitionedTo(State.On) ||
               _ballPathSubsystem.getBallCount() >= Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT; 
    }
}
