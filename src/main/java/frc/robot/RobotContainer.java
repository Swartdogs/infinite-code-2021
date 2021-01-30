package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.DeployPickup;
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
        DeployPickup deployPickup = new DeployPickup(Hardware.Pickup.instance);

        Hardware.Drive.instance.setDefaultCommand(new DriveWithJoystick(Hardware.Drive.instance, 
                                                                        () -> Hardware.Controls.Joysticks.DRIVE.getY(),
                                                                        () -> Hardware.Controls.Joysticks.DRIVE.getX()));
        
        Hardware.Controls.Buttons.CO_DRIVE_BUTTON_6.whenPressed(deployPickup);
        Hardware.Controls.Buttons.CO_DRIVE_BUTTON_7.cancelWhenPressed(deployPickup);
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
