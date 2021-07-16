package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;

public class CmdBallPathLower extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Hanger   _hangerSubsystem;
    private Pickup   _pickupSubsystem;
    private Shooter  _shooterSubsystem;

    public CmdBallPathLower(BallPath ballPathSubsystem, Hanger hangerSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
        _hangerSubsystem   = hangerSubsystem;
        _pickupSubsystem   = pickupSubsystem;
        _shooterSubsystem  = shooterSubsystem;

        addRequirements(_ballPathSubsystem, _pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (!_hangerSubsystem.isHangerReleased() && !_shooterSubsystem.isShooterOn())
        {
            _pickupSubsystem.deployPickup();
            _ballPathSubsystem.lowerUpperTrack();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
