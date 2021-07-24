package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.Vector;

public class CmdDriveDistance extends SwartdogCommand 
{
    private Drive   _driveSubsystem;
    private double  _distance;
    private double  _heading;
    private double  _maxSpeed;
    private double  _minSpeed;
    private boolean _resetEncoders;
    private boolean _absolute;

    public CmdDriveDistance(Drive driveSubsystem, double distance, double heading, double maxSpeed, double minSpeed, boolean resetEncoders, boolean absolute) 
    {
        _driveSubsystem = driveSubsystem;
        _distance       = distance;
        _heading        = heading;
        _maxSpeed       = maxSpeed;
        _minSpeed       = minSpeed;
        _resetEncoders  = resetEncoders;
        _absolute       = absolute;
    
        if (distance < 0)
        {
            _distance *= -1;
            _heading  += 180;
        }

        addRequirements(_driveSubsystem);
    }

    @Override
    public void initialize() 
    {
        _driveSubsystem.driveInit(_distance, _maxSpeed, _minSpeed, _resetEncoders);
        
        _driveSubsystem.setDriveInUse(true);
    }

    @Override
    public void execute() 
    {
        Vector translateVector = new Vector();
        
        translateVector.setPolarPosition(_driveSubsystem.driveExec(), _heading);
        _driveSubsystem.drive(translateVector, _driveSubsystem.rotateExec(), _absolute);

        System.out.println("Target: " + _distance + " Pos: " + _driveSubsystem.getAverageDistance() + " Speed: " + translateVector.getR());
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
        return _driveSubsystem.driveIsFinished();
    }
}
