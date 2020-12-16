package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase 
{
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
                     DutyCycleEncoder leftEncoder, DutyCycleEncoder rightEncoder, ADXRS450_Gyro gyro) 
    {
        m_driveLeftPrimary      = driveLeftPrimary;
        m_driveLeftSecondary    = driveLeftSecondary;
        m_driveRightPrimary     = driveRightPrimary;
        m_driveRightSecondary   = driveRightSecondary;

        m_leftEncoder           = leftEncoder;
        m_rightEncoder          = rightEncoder;

        m_gyro                  = gyro;
    }

    public void arcadeDrive(double drive, double rotate) {
        double left = 0;
        double right = 0; 

        left = drive + rotate;  
        right = drive - rotate; 

        double max = Math.max(Math.abs(left), Math.abs(right));

        if (max > 1.0) {
            left /= max;
            right /= max;
        }

        m_driveLeftPrimary.set(left);
        m_driveLeftSecondary.set(left);
        m_driveRightPrimary.set(right);
        m_driveRightSecondary.set(right);
    }

    public double getHeading() {
        return m_gyro.getAngle();
    }

    public double getLeftDistance() {
        return m_leftEncoder.getDistance();
    }

    public double getRightDistance() {
        return m_rightEncoder.getDistance();
    }

    public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    public void resetGyro() {
        m_gyro.reset();
    }

}
