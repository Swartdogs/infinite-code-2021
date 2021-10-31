package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.abstraction.SwartdogSubsystem.GameMode;

public class Robot extends TimedRobot 
{
    private Command _autonomousCommand;

    private RobotHardware  _robotHardware;
    private RobotContainer _robotContainer;

    @Override
    public void robotInit() 
    {
        _robotHardware  = new RobotHardware();
        _robotContainer = new RobotContainer(_robotHardware);
    }

    @Override
    public void robotPeriodic() 
    {
        CommandScheduler.getInstance().run();
        _robotContainer.periodic();
    }

    @Override
    public void disabledInit() 
    {
        _robotContainer.setGameMode(GameMode.Disabled);
    }

    @Override
    public void disabledPeriodic() 
    {

    }

    @Override
    public void autonomousInit() 
    {
        _robotContainer.setGameMode(GameMode.Autonomous);

        _autonomousCommand = _robotContainer.getAutonomousCommand();

        if (_autonomousCommand != null) 
        {
            _autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() 
    {

    }

    @Override
    public void teleopInit() 
    {
        _robotContainer.setGameMode(GameMode.Teleop);

        if (_autonomousCommand != null) 
        {
            _autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() 
    {

    }

    @Override
    public void testInit() 
    {
        _robotContainer.setGameMode(GameMode.Test);

        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() 
    {

    }
}
