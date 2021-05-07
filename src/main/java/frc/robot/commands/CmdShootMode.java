package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;

public class CmdShootMode extends SwartdogCommand 
{
    private Drive          _driveSubsystem;
    private BallPath       _ballPathSubsystem;
    private Hanger         _hangerSubsystem;
    private Pickup         _pickupSubsystem;
    private Shooter        _shooterSubsystem;
    private Vision         _visionSubsystem;

    private DoubleSupplier _drive;
    private DoubleSupplier _strafe;
    private DoubleSupplier _rotate;

    public CmdShootMode(Drive          driveSubsystem, 
                        BallPath       ballPathSubsystem, 
                        Hanger         hangerSubsystem, 
                        Pickup         pickupSubsystem,
                        Shooter        shooterSubsystem,
                        Vision         visionSubsystem, 
                        DoubleSupplier drive,
                        DoubleSupplier strafe,
                        DoubleSupplier rotate) 
    {
        _driveSubsystem    = driveSubsystem;
        _ballPathSubsystem = ballPathSubsystem;
        _hangerSubsystem   = hangerSubsystem;
        _pickupSubsystem   = pickupSubsystem;
        _shooterSubsystem  = shooterSubsystem;
        _visionSubsystem   = visionSubsystem;  
        
        _drive             = drive;
        _strafe            = strafe;
        _rotate            = rotate;
        
        addRequirements(_driveSubsystem,
                        _ballPathSubsystem,
                        _hangerSubsystem,
                        _pickupSubsystem,
                        _shooterSubsystem);
    }

    @Override
    public void initialize() 
    {
        _visionSubsystem.setLEDs(State.On);
        _visionSubsystem.rotateInit();
        
        _pickupSubsystem.getLeftMotor().set(0);
        _pickupSubsystem.getPrimaryMotor().set(0);
        _pickupSubsystem.getRightMotor().set(0);

        _pickupSubsystem.getDeploySolenoid().extend();

        _shooterSubsystem.setHoodSetpoint(_shooterSubsystem.getHoodPositionSensor().get());
    }

    @Override
    public void execute() 
    {
        double rotate = _rotate.getAsDouble();
        double targetDistance = _visionSubsystem.getTargetDistance();
        
        if (_visionSubsystem.targetFound())
        {
            rotate = _visionSubsystem.rotateExec();
            
            _shooterSubsystem.setHoodSetpoint(Constants.HOOD_LOOKUP.applyAsDouble(targetDistance));

            _shooterSubsystem.getShooterMotor().set(Constants.SHOOTER_LOOKUP.applyAsDouble(targetDistance));
        }

        _driveSubsystem.drive(_drive.getAsDouble(), 
                              _strafe.getAsDouble(), 
                              rotate);

        _shooterSubsystem.getHoodMotor().set(_shooterSubsystem.hoodExec());
    }

    @Override
    public void end(boolean interrupted) 
    {
        _shooterSubsystem.getHoodMotor().set(0);
        _shooterSubsystem.getShooterMotor().set(0);

        _visionSubsystem.setLEDs(State.Off);
    }

    @Override
    public boolean isFinished() 
    {
        return _ballPathSubsystem.getBallCount() <= 0;
    }
}
