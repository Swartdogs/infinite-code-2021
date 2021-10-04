package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;

public class CmdDriveWithShooter extends SwartdogCommand 
{
    private Drive          _driveSubsystem;
    private Shooter        _shooterSubsystem;
    private Vision         _visionSubsystem;
    private DoubleSupplier _drive;
    private DoubleSupplier _strafe;
    private DoubleSupplier _rotate;

    public CmdDriveWithShooter(Drive          driveSubsystem, 
                               Shooter        shooterSubsystem, 
                               Vision         visionSubsystem, 
                               DoubleSupplier drive, 
                               DoubleSupplier strafe, 
                               DoubleSupplier rotate) 
    {
        _driveSubsystem   = driveSubsystem;
        _shooterSubsystem = shooterSubsystem;
        _visionSubsystem  = visionSubsystem;
        _drive            = drive;
        _strafe           = strafe;
        _rotate           = rotate;

        addRequirements(_driveSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (_shooterSubsystem.isShooterOn())
        {
            _driveSubsystem.setDriveInUse(true);

            _visionSubsystem.setLEDs(State.On);
            _visionSubsystem.rotateInit();
        }
    }

    @Override
    public void execute() 
    {
        double rotate = 0;
        if (_visionSubsystem.targetFound()) 
        {
            rotate = _visionSubsystem.rotateExec();
            _shooterSubsystem.setTargetDistance(_visionSubsystem.getTargetDistance());
        }
        else
        {
            rotate = _rotate.getAsDouble();
        }

        _driveSubsystem.drive(_drive.getAsDouble(), _strafe.getAsDouble(), rotate);

    }

    @Override
    public void end(boolean interrupted) 
    {
        _driveSubsystem.setDriveInUse(false);

        _visionSubsystem.setLEDs(State.Off);
    }

    @Override
    public boolean isFinished() 
    {
        return !_shooterSubsystem.isShooterOn();
    }
}
