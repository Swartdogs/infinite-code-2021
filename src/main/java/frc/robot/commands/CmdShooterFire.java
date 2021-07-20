package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.drive.Drive;

public class CmdShooterFire extends SwartdogCommand 
{
    private Dashboard _dashboardSubsystem;
    private Drive     _driveSubsystem;
    private BallPath  _ballPathSubsystem;
    private Pickup    _pickupSubsystem;
    private Shooter   _shooterSubsystem;

    public CmdShooterFire(Dashboard dashboardSubsystem,
                          Drive     driveSubsystem,
                          BallPath  ballPathSubsystem, 
                          Pickup    pickupSubsystem, 
                          Shooter   shooterSubsystem) 
    {
        _dashboardSubsystem = dashboardSubsystem;
        _driveSubsystem     = driveSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;
        _pickupSubsystem    = pickupSubsystem;
        _shooterSubsystem   = shooterSubsystem;

        addRequirements(_ballPathSubsystem, _pickupSubsystem, _shooterSubsystem);
    }

    @Override
    public void execute() 
    {
        if (_shooterSubsystem.isShooterOn()) 
        {
            _ballPathSubsystem.setTrackMotor(_dashboardSubsystem.getBallPathSpeed());
            _pickupSubsystem.setPrimaryMotor(_dashboardSubsystem.getPickupSpeed());

            if (_ballPathSubsystem.shooterSensorTransitionedTo(State.Off)) {
                _ballPathSubsystem.decrementBallCount();
            }
        }
    }

    @Override
    public void end(boolean interrupted) 
    {
        _ballPathSubsystem.setTrackMotor(0);
        
        if (_shooterSubsystem.isShooterOn()) 
        {
            _pickupSubsystem.setPrimaryMotor(0);
        }

        if (!interrupted) 
        {
            _shooterSubsystem.stopShooter();
            _driveSubsystem.setRotateScaler(Constants.MAX_ROTATE_SPEED);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return (_ballPathSubsystem.getBallCount() <= 0) || 
               (!_shooterSubsystem.isShooterOn());
    }
}
