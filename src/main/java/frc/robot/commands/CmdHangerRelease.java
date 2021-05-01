package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.Hanger;

public class CmdHangerRelease extends SwartdogCommand 
{
    private Hanger _hangerSubsystem;

    public CmdHangerRelease(Hanger hangerSubsystem)
    {
        _hangerSubsystem = hangerSubsystem;
    }

    @Override
    public void initialize() 
    {
        _hangerSubsystem.getReleaseSolenoid().retract();
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
