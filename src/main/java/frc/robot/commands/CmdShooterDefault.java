package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Shooter;

public class CmdShooterDefault extends SwartdogCommand 
{
    private Shooter  _shooterSubsystem;
    private BallPath _ballPathSubsystem;

    public CmdShooterDefault(Shooter shooterSubsystem, BallPath ballPathSubsystem) 
    {
        _shooterSubsystem    = shooterSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;

        addRequirements(_shooterSubsystem);
    }

    @Override
    public void initialize() 
    {
        _shooterSubsystem.setShooterMotor(0);
    }

    @Override
    public void execute() 
    {
        if (_shooterSubsystem.isShooterOn() && (_ballPathSubsystem.getBallCount() > 0))
        {
            _shooterSubsystem.setShooterMotor(Constants.SHOOTER_LOOKUP.applyAsDouble(_shooterSubsystem.getTargetDistance()));
        }
        else
        {
            _shooterSubsystem.setShooterMotor(0);
        }

        _shooterSubsystem.setHoodSetpoint(Constants.HOOD_LOOKUP.applyAsDouble(_shooterSubsystem.getTargetDistance()));
        _shooterSubsystem.setHoodMotor(_shooterSubsystem.hoodExec());
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
