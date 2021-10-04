package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.drive.Drive;

public class CmdShooterStart extends SwartdogCommand 
{
    private BallPath    _ballPathSubsystem;
    private Drive       _driveSubsystem;
    private Pickup      _pickupSubsystem;
    private Shooter     _shooterSubsystem;

    public CmdShooterStart(BallPath ballPathSubsystem, Drive driveSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem) 
    {
        _ballPathSubsystem  = ballPathSubsystem;
        _driveSubsystem     = driveSubsystem;
        _pickupSubsystem    = pickupSubsystem;
        _shooterSubsystem   = shooterSubsystem;
    }

    @Override
    public void initialize() 
    {
        if ((_ballPathSubsystem.getBallCount() > 0) &&
            (!_pickupSubsystem.isPickupDeployed()) &&
            (_ballPathSubsystem.isUpperTrackRaised()) &&
            (!_ballPathSubsystem.isJammed()))
        {
            _shooterSubsystem.startShooter();
            _driveSubsystem.setRotateScaler(Constants.DEFAULT_SHOOTER_ROTATE_SCALER);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
