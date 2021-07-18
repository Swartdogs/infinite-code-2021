package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Shooter;

public class CmdShooterDefault extends SwartdogCommand 
{
    private Dashboard _dashboardSubsystem;
    private Shooter   _shooterSubsystem;
    private BallPath  _ballPathSubsystem;

    public CmdShooterDefault(Dashboard dashboardSubsystem, Shooter shooterSubsystem, BallPath ballPathSubsystem) 
    {
        _dashboardSubsystem = dashboardSubsystem;
        _shooterSubsystem   = shooterSubsystem;
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
            _shooterSubsystem.setShooterMotor(calculateShooterRPM());
        }

        else
        {
            _shooterSubsystem.setShooterMotor(0);
        }

        _shooterSubsystem.setHoodSetpoint(calculateHoodAngle());
        _shooterSubsystem.setHoodMotor(_shooterSubsystem.hoodExec());
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }

    private double calculateHoodAngle() 
    { 
        double target = _dashboardSubsystem.getHoodMinPosition();

        if (_shooterSubsystem.getTargetDistance() == Constants.SHOOTER_NEAR_DISTANCE)
        {
            target = _dashboardSubsystem.getHoodNearPosition();
        }

        else if (_shooterSubsystem.getTargetDistance() == Constants.SHOOTER_FAR_DISTANCE)
        {
            target = _dashboardSubsystem.getHoodFarPosition();
        }

        return target;
    };

    private double calculateShooterRPM()
    {
        double target = 0;

        if (_shooterSubsystem.getTargetDistance() == Constants.SHOOTER_NEAR_DISTANCE)
        {
            target = _dashboardSubsystem.getShooterNearSpeed();
        }

        else if (_shooterSubsystem.getTargetDistance() == Constants.SHOOTER_FAR_DISTANCE)
        {
            target = _dashboardSubsystem.getShooterFarSpeed();
        }

        return target;
    };
}
