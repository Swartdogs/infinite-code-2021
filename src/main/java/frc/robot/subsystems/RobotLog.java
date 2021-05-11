package frc.robot.subsystems;

import java.util.Map;
import java.util.function.DoubleSupplier;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.SwartdogSubsystem;

public class RobotLog extends SwartdogSubsystem 
{
    public enum CommandMode
    {
        Start,
        Exec,
        End
    }

    public enum GameMode
    {
        Disabled,
        Autonomous,
        Teleop,
        Test
    }

    private DoubleSupplier _battery;

    private int            _time;
    private int            _gameTime;

    public RobotLog(String file, DoubleSupplier battery) 
    {

    }

    @Override
    public void periodic() 
    {

    }

    public void logCommand(SwartdogCommand command, CommandMode mode, Map<String, Object> logParams)
    {

    }

    public void logGameMode(GameMode newMode)
    {

    }

    public void save()
    {
        
    }
}
