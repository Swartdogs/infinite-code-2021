package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveWithJoystick extends CommandBase 
{
    private DriveBase       m_driveBase;
    private DoubleSupplier  m_drive;
    private DoubleSupplier  m_rotate;

    public DriveWithJoystick(DriveBase drivebase, DoubleSupplier drive, DoubleSupplier rotate) 
    {
        m_driveBase     = drivebase;

        m_drive         = drive;
        m_rotate        = rotate;
    }

    @Override
    public void execute() 
    {
        m_driveBase.arcadeDrive(m_drive.getAsDouble(), m_rotate.getAsDouble());
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
