package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.Drive;

public class CmdDriveRotate extends CommandBase 
{
    private Drive    _driveSubsystem;
    private double   _heading;
    private double   _maxSpeed;
    private boolean  _absolute;

    public CmdDriveRotate(Drive driveSubsystem, double heading, double maxSpeed, boolean absolute)
    {
        _driveSubsystem = driveSubsystem;
        _heading        = heading;
        _maxSpeed       = maxSpeed;
        _absolute       = absolute;

        addRequirements(_driveSubsystem);
    }

    @Override
    public void initialize()
    {
        double absoluteHeading = _heading;

        if (!_absolute)
        {
            absoluteHeading += _driveSubsystem.getHeading();
        }
        
        _driveSubsystem.rotateInit(absoluteHeading, _maxSpeed);
    }

    @Override
    public void execute()
    {
        _driveSubsystem.drive(0, 0, _driveSubsystem.rotateExec());
    }

    @Override
    public void end(boolean interrupted)
    {
        _driveSubsystem.drive(0, 0, 0);
    }

    @Override
    public boolean isFinished()
    {
        return _driveSubsystem.rotateIsFinished();
    }
}
