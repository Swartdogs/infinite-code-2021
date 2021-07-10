package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;

public class CmdPickupDeploy extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;
    private Shooter  _shooterSubsystem;

    public CmdPickupDeploy(BallPath ballPathSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;
        _shooterSubsystem  = shooterSubsystem;

        addRequirements(_pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (!_ballPathSubsystem.isJammed() &&
             _ballPathSubsystem.getBallCount() < Constants.MAX_BALL_COUNT &&
             !_shooterSubsystem.isShooterOn())
        {
            _pickupSubsystem.setLeftMotor(Constants.PICKUP_SPEED);
            _pickupSubsystem.setPrimaryMotor(Constants.PICKUP_SPEED);
            _pickupSubsystem.setRightMotor(Constants.PICKUP_SPEED);

            _pickupSubsystem.deployPickup();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
