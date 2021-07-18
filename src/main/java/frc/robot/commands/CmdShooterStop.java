package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.drive.Drive;

public class CmdShooterStop extends SwartdogCommand 
{
    private Drive   _driveSubsystem;
    private Shooter _shooterSubsystem;

    public CmdShooterStop(Drive driveSubsystem, Shooter shooterSubsystem) 
    {
        _driveSubsystem     = driveSubsystem;
        _shooterSubsystem   = shooterSubsystem;
    }

    @Override
    public void initialize() 
    {
        _shooterSubsystem.stopShooter();
        _driveSubsystem.setRotateScaler(1.0);
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
