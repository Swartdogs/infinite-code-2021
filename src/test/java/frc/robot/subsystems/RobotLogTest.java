package frc.robot.subsystems;

import java.util.HashMap;

import org.junit.Test;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.SwartdogSubsystem.GameMode;
import frc.robot.subsystems.RobotLog.CommandMode;

public class RobotLogTest 
{
    private class CmdExample extends SwartdogCommand
    {

    }

    @Test
    public void testLogWrite()
    {
        SwartdogSubsystem.REGISTER_SUBSYSTEMS = false;

        RobotLog logSubsystem = new RobotLog("test.csv", (() -> 1));
        CmdExample cmd        = new CmdExample();

        logSubsystem.periodic();
        logSubsystem.periodic();
        logSubsystem.periodic();

        logSubsystem.logGameMode(GameMode.Autonomous);

        logSubsystem.periodic();
        logSubsystem.periodic();

        logSubsystem.logCommand(cmd, CommandMode.Start, new HashMap<String, String>());

        logSubsystem.periodic();
        logSubsystem.periodic();

        logSubsystem.logCommand(cmd, CommandMode.End, new HashMap<String, String>());

        logSubsystem.periodic();
        logSubsystem.periodic();
        logSubsystem.periodic();

        logSubsystem.logGameMode(GameMode.Disabled);

    }
}
