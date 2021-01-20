package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveWithJoystick extends CommandBase 
{
    private Drive           m_drive;
    private DoubleSupplier  m_driveValue;
    private DoubleSupplier  m_rotateValue;

    public DriveWithJoystick(Drive drive, DoubleSupplier driveValue, DoubleSupplier rotateValue) 
    {
        m_drive         = drive;

        m_driveValue    = driveValue;
        m_rotateValue   = rotateValue;
    }

    @Override
    public void execute() 
    {
        m_drive.arcadeDrive(m_driveValue.getAsDouble(), m_rotateValue.getAsDouble());
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
