package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.Preset;

public class CmdHangerRelease extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Hanger   _hangerSubsystem;
    private Shooter  _shooterSubsystem;

    public CmdHangerRelease(BallPath ballPathSubsystem, Hanger hangerSubsystem, Shooter shooterSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
        _hangerSubsystem   = hangerSubsystem;
        _shooterSubsystem  = shooterSubsystem;

        addRequirements(_hangerSubsystem, _shooterSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (_ballPathSubsystem.isUpperTrackRaised())
        {
            _hangerSubsystem.releaseHanger();
            _shooterSubsystem.setPreset(Preset.Near);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
