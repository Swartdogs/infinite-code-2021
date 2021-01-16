package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.Drive;

public class RobotContainer 
{
    private Joystick            m_joystick;

    // Drivebase
    private Drive               m_drive;

    // motors
    private CANSparkMax         m_driveLeftPrimary;
    private CANSparkMax         m_driveLeftSecondary;
    private CANSparkMax         m_driveRightPrimary;
    private CANSparkMax         m_driveRightSecondary;

    // encoder
    private DutyCycleEncoder    m_leftEncoder;
    private DutyCycleEncoder    m_rightEncoder;

    // gyro
    private ADXRS450_Gyro       m_gyro;

    public RobotContainer() 
    {
        m_joystick              = new Joystick(0);

        m_driveLeftPrimary      = new CANSparkMax(0, MotorType.kBrushless);
        m_driveLeftSecondary    = new CANSparkMax(0, MotorType.kBrushless);
        m_driveRightPrimary     = new CANSparkMax(0, MotorType.kBrushless);
        m_driveRightSecondary   = new CANSparkMax(0, MotorType.kBrushless);

        m_leftEncoder           = new DutyCycleEncoder(0);
        m_rightEncoder          = new DutyCycleEncoder(0);

        m_gyro                  = new ADXRS450_Gyro();

        m_drive                 = new Drive(m_driveLeftPrimary, m_driveLeftSecondary, m_driveRightPrimary, m_driveRightSecondary,
                                            m_leftEncoder, m_rightEncoder, m_gyro);

        configureButtonBindings();
    }

    private void configureButtonBindings() 
    {
        m_drive.setDefaultCommand(new DriveWithJoystick(m_drive, 
                                                        () -> -m_joystick.getY(),
                                                        () -> m_joystick.getX()));
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
