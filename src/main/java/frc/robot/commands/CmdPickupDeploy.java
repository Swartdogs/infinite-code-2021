package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;

public class CmdPickupDeploy extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;

    public CmdPickupDeploy(BallPath ballPathSubsystem, Pickup pickupSubsystem) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;

        addRequirements(_pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (!_ballPathSubsystem.isJammed() &&
            _ballPathSubsystem.getBallCount() < Constants.MAX_BALL_COUNT)
        {
            _pickupSubsystem.getLeftMotor().set(Constants.PICKUP_SPEED);
            _pickupSubsystem.getPrimaryMotor().set(Constants.PICKUP_SPEED);
            _pickupSubsystem.getRightMotor().set(Constants.PICKUP_SPEED);

            _pickupSubsystem.getDeploySolenoid().retract();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
