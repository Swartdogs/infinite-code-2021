package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;

public class CmdBallPathLower extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Hanger   _hangerSubsystem;
    private Pickup   _pickupSubsystem;

    public CmdBallPathLower(BallPath ballPathSubsystem, Hanger hangerSubsystem, Pickup pickupSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
        _hangerSubsystem   = hangerSubsystem;
        _pickupSubsystem   = pickupSubsystem;

        addRequirements(_ballPathSubsystem, _pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (!_hangerSubsystem.isHangerReleased())
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
