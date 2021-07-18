package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;

public class CmdBallPathIncrementBallCount extends SwartdogCommand
{
    private BallPath _ballPathSubsystem;

    public CmdBallPathIncrementBallCount(BallPath ballPathSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
    }

    @Override
    public void initialize()
    {
        _ballPathSubsystem.incrementBallCount();
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
