package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.ExtendState;
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
        if (_ballPathSubsystem.getUpperTrackSolenoid().get() == ExtendState.Retracted)
        {
            _pickupSubsystem.getLeftMotor().set(0);
            _pickupSubsystem.getPrimaryMotor().set(0);
            _pickupSubsystem.getRightMotor().set(0);

            _pickupSubsystem.getDeploySolenoid().extend();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
