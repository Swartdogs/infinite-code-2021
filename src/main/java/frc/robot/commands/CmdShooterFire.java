package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class CmdShooterFire extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;
    private Shooter  _shooterSubsystem;
    private Vision   _visionSubsystem;

    public CmdShooterFire(BallPath ballPathSubsystem, 
                          Pickup   pickupSubsystem, 
                          Shooter  shooterSubsystem, 
                          Vision   visionSubsystem) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;
        _shooterSubsystem  = shooterSubsystem;
        _visionSubsystem   = visionSubsystem;
    }

    @Override
    public void execute() 
    {
        if (_visionSubsystem.rotateIsFinished() &&
            _shooterSubsystem.hoodAtSetpoint() &&
            Math.abs(_shooterSubsystem.getShooterMotorSetpoint() - _shooterSubsystem.getShooterMotor()) < Constants.SHOOTER_RPM_DEADBAND)
        {
            _ballPathSubsystem.setTrackMotor(Constants.BALLPATH_SPEED);
            _pickupSubsystem.setPrimaryMotor(Constants.PICKUP_SPEED);
        }

        if (_ballPathSubsystem.shooterSensorTransitionedTo(State.Off))
        {
            _ballPathSubsystem.decrementBallCount();
        }
    }

    @Override
    public void end(boolean interrupted) 
    {
        _ballPathSubsystem.setTrackMotor(0);
        _pickupSubsystem.setPrimaryMotor(0);
    }

    @Override
    public boolean isFinished() 
    {
        return _ballPathSubsystem.getBallCount() <= 0;
    }
}
