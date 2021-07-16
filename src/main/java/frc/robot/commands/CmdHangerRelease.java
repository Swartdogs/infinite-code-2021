package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Hanger;

public class CmdHangerRelease extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Hanger   _hangerSubsystem;

    public CmdHangerRelease(BallPath ballPathSubsystem, Hanger hangerSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
        _hangerSubsystem   = hangerSubsystem;

        addRequirements(_hangerSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (_ballPathSubsystem.isUpperTrackRaised())
        {
            _hangerSubsystem.releaseHanger();
        }
        
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
