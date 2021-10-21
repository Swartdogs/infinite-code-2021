package frc.robot.commands;

import java.util.function.DoubleSupplier;
import java.lang.Math;

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
    private boolean        _finished;

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
        _finished         = false;

        addRequirements(_driveSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (_shooterSubsystem.isShooterOn())
        {
            _driveSubsystem.setDriveInUse(true);

            _visionSubsystem.enableVisionProcessing();
            _visionSubsystem.rotateInit();

            _finished = false;
        }
    }

    @Override
    public void execute() 
    {
        double rotate = 0;
        double manual = _rotate.getAsDouble();

        if (_visionSubsystem.targetFound()) 
        {
            rotate = _visionSubsystem.rotateExec();
        }
        else
        {
            rotate = manual;
        }

        _driveSubsystem.drive(_drive.getAsDouble(), _strafe.getAsDouble(), rotate);

        if (Math.abs(manual) >= 1) 
        {
            _finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) 
    {
        _driveSubsystem.setDriveInUse(false);

        _visionSubsystem.disableVisionProcessing();
    }

    @Override
    public boolean isFinished() 
    {
        return !_shooterSubsystem.isShooterOn() || _finished;
    }
}
