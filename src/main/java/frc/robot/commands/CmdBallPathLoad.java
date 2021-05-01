package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;

public class CmdBallPathLoad extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;

    private int      _loadTimer;
    private double   _trackMotorSpeed;

    public CmdBallPathLoad(BallPath ballPathSubsystem, Pickup pickupSubsystem) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;

        _loadTimer         = 0;
        _trackMotorSpeed   = 0;

        addRequirements(_ballPathSubsystem, _pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        _pickupSubsystem.getLeftMotor().set(0);
        _pickupSubsystem.getPrimaryMotor().set(0);
        _pickupSubsystem.getRightMotor().set(0);

        if (!_ballPathSubsystem.isJammed() && 
            _ballPathSubsystem.getBallCount() < Constants.MAX_BALL_COUNT)
        {
            _ballPathSubsystem.incrementBallCount();

            _loadTimer       = Math.max(0, (int)(50 * Constants.BALLPATH_JAM_TIME));
            _trackMotorSpeed = Constants.BALLPATH_RAMP_MIN;
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
        if (!_ballPathSubsystem.isJammed())
        {
            _loadTimer--;

            if (_loadTimer > 0)
            {
                _trackMotorSpeed += Constants.BALLPATH_RAMP_STEP;
                
                if (_trackMotorSpeed > Constants.BALLPATH_SPEED)
                {
                    _trackMotorSpeed = Constants.BALLPATH_SPEED;
                }
            }

            else
            {
                _trackMotorSpeed = 0;
                _ballPathSubsystem.setJammed(true);
            }

            _ballPathSubsystem.getTrackMotor().set(_trackMotorSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) 
    {
        _ballPathSubsystem.getTrackMotor().set(0);

        if (!_ballPathSubsystem.isJammed() && 
            _ballPathSubsystem.getBallCount() < Constants.MAX_BALL_COUNT)
        {
            _pickupSubsystem.getLeftMotor().set(Constants.PICKUP_SPEED);
            _pickupSubsystem.getPrimaryMotor().set(Constants.PICKUP_SPEED);
            _pickupSubsystem.getRightMotor().set(Constants.PICKUP_SPEED);
        }

        else if (_ballPathSubsystem.getUpperTrackSolenoid().get() == ExtendState.Retracted)
        {
            _pickupSubsystem.getDeploySolenoid().set(ExtendState.Extended);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return _ballPathSubsystem.isJammed() || 
               _ballPathSubsystem.getPosition2Sensor().transitionedTo(State.On);
    }
}
