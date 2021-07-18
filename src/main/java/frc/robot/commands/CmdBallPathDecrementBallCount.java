package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;

public class CmdBallPathDecrementBallCount extends SwartdogCommand
{
    private BallPath _ballPathSubsystem;

    public CmdBallPathDecrementBallCount(BallPath ballPathSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
    }

    @Override
    public void initialize()
    {
        _ballPathSubsystem.decrementBallCount();
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

    @Override
    public boolean runsWhenDisabled()
    {
        return true;
    }
}
