package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;

public class CmdPickupStow extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;

    public CmdPickupStow(BallPath ballPathSubsystem, Pickup pickupSubsystem) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;

        addRequirements(_pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        _pickupSubsystem.setLeftMotor(0);
        _pickupSubsystem.setPrimaryMotor(0);
        _pickupSubsystem.setRightMotor(0);

        if (_ballPathSubsystem.isUpperTrackRaised())
        {
            _pickupSubsystem.stowPickup();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
