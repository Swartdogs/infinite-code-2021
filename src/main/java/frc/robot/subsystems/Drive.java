package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase 
{
    // motors
    private CANSparkMax         m_driveLeft;
    private CANSparkMax         m_driveRight;

    // encoder
    private DutyCycleEncoder    m_leftEncoder;
    private DutyCycleEncoder    m_rightEncoder;

    // gyro
    private ADXRS450_Gyro       m_gyro;

    public Drive(CANSparkMax driveLeft, CANSparkMax driveRight,
                 DutyCycleEncoder leftEncoder, DutyCycleEncoder rightEncoder, ADXRS450_Gyro gyro) 
    {
        m_driveLeft     = driveLeft;
        m_driveRight    = driveRight;

        m_leftEncoder   = leftEncoder;
        m_rightEncoder  = rightEncoder;

        m_gyro          = gyro;
    }

    public void arcadeDrive(double drive, double rotate) 
    {
        double left  = drive + rotate;
        double right = drive - rotate;
        double max   = Math.max(Math.abs(left), Math.abs(right));

        if (max > 1.0) 
        {
            left /= max;
            right /= max;
        }

        m_driveLeft.set(left);
        m_driveRight.set(right);
    }

    public double getHeading() 
    {
        return m_gyro.getAngle();
    }

    public double getLeftDistance() 
    {
        return m_leftEncoder.getDistance();
    }

    public double getRightDistance() 
    {
        return m_rightEncoder.getDistance();
    }

    public void resetEncoders() 
    {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    public void resetGyro() 
    {
        m_gyro.reset();
    }

}
