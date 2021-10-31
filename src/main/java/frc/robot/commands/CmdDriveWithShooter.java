package frc.robot.commands;

import java.util.function.DoubleSupplier;
import java.lang.Math;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.Preset;
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
    private Preset         _preset;
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
        _preset           = _shooterSubsystem.getPreset();
        _finished         = false;

        addRequirements(_driveSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (_shooterSubsystem.isShooterOn())
        {
            _preset = _shooterSubsystem.getPreset();

            _driveSubsystem.setDriveInUse(true);

            if (_preset == Preset.Vision)
            {
                _visionSubsystem.enableVisionProcessing();
                _visionSubsystem.rotateInit();
            }

            _finished = false;
        }
    }

    @Override
    public void execute() 
    {
        double rotate = 0;
        double manual = _rotate.getAsDouble();

        Preset currentPreset = _shooterSubsystem.getPreset();

        if (_preset != currentPreset)
        {
            _preset = currentPreset;

            if (_preset == Preset.Vision)
            {
                _visionSubsystem.enableVisionProcessing();
                _visionSubsystem.rotateInit();
            }
            
            else
            {
                _visionSubsystem.disableVisionProcessing();
            }
        }

        switch (_preset)
        {
            case Vision:
                if (_visionSubsystem.targetFound()) 
                {
                    rotate = _visionSubsystem.rotateExec();
                }
                else
                {
                    rotate = manual;
                }

                if (Math.abs(manual) >= 1) 
                {
                    _finished = true;
                }
                break;

            default:
                rotate = manual;
                break;
        }

        _driveSubsystem.drive(_drive.getAsDouble(), _strafe.getAsDouble(), rotate);
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
