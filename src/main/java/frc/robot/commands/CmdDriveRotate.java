package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.drive.Drive;

public class CmdDriveRotate extends SwartdogCommand 
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

        _driveSubsystem.setDriveInUse(true);
    }

    @Override
    public void execute()
    {
        double speed = _driveSubsystem.rotateExec();

        _driveSubsystem.drive(0, 0, speed);

    }

    @Override
    public void end(boolean interrupted)
    {
        _driveSubsystem.drive(0, 0, 0);

        _driveSubsystem.setDriveInUse(false);
    }

    @Override
    public boolean isFinished()
    {
        return _driveSubsystem.rotateIsFinished();
    }
}
