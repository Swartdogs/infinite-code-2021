package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.DriveWithJoystick;

public class RobotContainer 
{
    public RobotContainer() 
    {
        Hardware.configureHardware();

        configureButtonBindings();
    }

    private void configureButtonBindings() 
    {
        Hardware.Drive.instance.setDefaultCommand(new DriveWithJoystick(Hardware.Drive.instance, 
                                                                        () -> Hardware.Controls.Joysticks.DRIVE.getY(),
                                                                        () -> Hardware.Controls.Joysticks.DRIVE.getX()));
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
