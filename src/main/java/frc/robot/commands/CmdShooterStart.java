package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;

public class CmdShooterStart extends SwartdogCommand 
{
    private BallPath    _ballPathSubsystem;
    private Pickup      _pickupSubsystem;
    private Shooter     _shooterSubsystem;

    public CmdShooterStart(BallPath ballPathSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem) 
    {
        _ballPathSubsystem  = ballPathSubsystem;
        _pickupSubsystem    = pickupSubsystem;
        _shooterSubsystem   = shooterSubsystem;

        addRequirements(_shooterSubsystem);
    }

    @Override
    public void initialize() 
    {
        if ((_ballPathSubsystem.getBallCount() > 0) &&
            (!_pickupSubsystem.isPickupDeployed()) &&
            (_ballPathSubsystem.isUpperTrackRaised()))
        {
            _shooterSubsystem.startShooter();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
