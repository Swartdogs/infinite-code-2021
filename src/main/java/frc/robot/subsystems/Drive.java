package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;


public class Drive extends SubsystemBase 
{
    public enum UseEncoder {
        LeftEncoder,
        RightEncoder,
        BothEncoders,
        CurrentEncoder
    }

    // motors
    private CANSparkMax         m_driveLeft;
    private CANSparkMax         m_driveRight;

    // encoder
    private DutyCycleEncoder    m_leftEncoder;
    private DutyCycleEncoder    m_rightEncoder;
    private UseEncoder          m_useEncoder;

    // gyro
    private ADXRS450_Gyro       m_gyro;

    //PID Controllers
    private PIDControl          m_drivePID;
    private PIDControl          m_rotatePID;

    public Drive(CANSparkMax driveLeft, CANSparkMax driveRight,
                 DutyCycleEncoder leftEncoder, DutyCycleEncoder rightEncoder, ADXRS450_Gyro gyro,
                 PIDControl drivePID, PIDControl rotatePID)
    {
        m_driveLeft     = driveLeft;
        m_driveRight    = driveRight;

        m_leftEncoder   = leftEncoder;
        m_rightEncoder  = rightEncoder;
        m_useEncoder    = UseEncoder.LeftEncoder;

        m_gyro          = gyro;
        
        m_drivePID      = drivePID;
        m_rotatePID     = rotatePID;

        //Coefficients are from last year!!! 
        m_rotatePID.setCoefficient(Coefficient.P, 0, 0.007, 0);
        m_rotatePID.setCoefficient(Coefficient.I, 4, 0, 0.001);
        m_rotatePID.setCoefficient(Coefficient.D, 0, 0.036, 0);
        m_rotatePID.setInputRange(-360, 360);
        m_rotatePID.setOutputRamp(0.1, 0.1);
        //setpoint set in rotate init

        m_drivePID.setCoefficient(Coefficient.P, 0, 0.025, 0);
        m_drivePID.setCoefficient(Coefficient.I, 0, 0, 0);
        m_drivePID.setCoefficient(Coefficient.D, 0, 0.5, 0);
        m_drivePID.setInputRange(-500, 500);
        m_drivePID.setOutputRamp(0.05, 0.05);
        m_drivePID.setSetpointDeadband(1.0);

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

    public void driveInit(double distance, double heading, double maxSpeed, double minSpeed, boolean resetGyro, boolean resetEncoders) 
    {
        maxSpeed = Math.abs(maxSpeed);
        minSpeed = Math.abs(minSpeed);

        if (resetGyro)
        {
            resetGyro();
        }

        if (resetEncoders) 
        {
            resetEncoders();
        }

        m_drivePID.setSetpoint(distance, getDistance(m_useEncoder));
        m_drivePID.setOutputRange(-maxSpeed, maxSpeed, minSpeed);
    }

    public double driveExec() 
    {
        return m_drivePID.calculate(getDistance(m_rightEncoder));
    }

    public double getHeading() 
    {
        return m_gyro.getAngle();
    }

    public double getDistance(UseEncoder encoder) 
    {
        if (encoder == UseEncoder.CurrentEncoder) encoder = m_useEncoder;
        double distance = 0;
        double left = getLeftDistance();
        double right = getRightDistance();

        switch(encoder)
        {
            case LeftEncoder: distance = left;
            break;
            case RightEncoder: distance = right;
            break;
            default: distance = (getLeftDistance() + getRightDistance()) / 2;
        }

        return distance;
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
