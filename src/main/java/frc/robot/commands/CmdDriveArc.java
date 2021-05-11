package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.Vector;

public class CmdDriveArc extends SwartdogCommand 
{
    private Drive    _driveSubsystem;
    private double   _heading;
    private Vector   _rotationAxis;
    private Vector   _oldOrigin;
    private double   _maxSpeed;
    private boolean  _fieldCentricAxis;

    public CmdDriveArc(Drive driveSubsystem, double heading, Vector rotationAxis, double maxSpeed, boolean fieldCentricAxis)
    {
        _driveSubsystem   = driveSubsystem;
        _heading          = heading;
        _rotationAxis     = rotationAxis;
        _maxSpeed         = maxSpeed;
        _fieldCentricAxis = fieldCentricAxis;

        addRequirements(_driveSubsystem);
    }

    @Override
    public void initialize()
    {
        Vector newOrigin = _rotationAxis.clone();

        if (_fieldCentricAxis) 
        {
            newOrigin.setTheta(newOrigin.getTheta() - _driveSubsystem.getHeading());
        }

        _oldOrigin = _driveSubsystem.getOrigin();
        _driveSubsystem.setOrigin(newOrigin);
      
        _driveSubsystem.rotateInit(_heading + _driveSubsystem.getHeading(), _maxSpeed);
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
        _driveSubsystem.setOrigin(_oldOrigin);
    }

    @Override
    public boolean isFinished()
    {
        return _driveSubsystem.rotateIsFinished();
    }
}
