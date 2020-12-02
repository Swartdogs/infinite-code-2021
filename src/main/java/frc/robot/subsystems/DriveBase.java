package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase {

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

    public DriveBase(CANSparkMax driveLeftPrimary, CANSparkMax driveLeftSecondary,
                     CANSparkMax driveRightPrimary, CANSparkMax driveRightSecondary,
                     DutyCycleEncoder leftEncoder, DutyCycleEncoder rightEncoder, ADXRS450_Gyro gyro) {

        m_driveLeftPrimary      = driveLeftPrimary;
        m_driveLeftSecondary    = driveLeftSecondary;
        m_driveRightPrimary     = driveRightPrimary;
        m_driveRightSecondary   = driveRightSecondary;

        m_leftEncoder           = leftEncoder;
        m_rightEncoder          = rightEncoder;

        m_gyro                  = gyro;

    }

    //all of our functions will go here

}
