package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pickup extends SubsystemBase 
{
    private CANSparkMax  m_primaryMotor;

    private VictorSP     m_leftMotor;
    private VictorSP     m_rightMotor;

    private DigitalInput m_leftLightSensor;
    private DigitalInput m_rightLightSensor;

    private Solenoid     m_deploySolenoid;

    public Pickup(CANSparkMax primaryMotor, VictorSP leftMotor, VictorSP rightMotor, DigitalInput leftLightSensor, DigitalInput rightLightSensor, Solenoid deploySolenoid) 
    {
        m_primaryMotor     = primaryMotor;

        m_leftMotor        = leftMotor;
        m_rightMotor       = rightMotor;

        m_leftLightSensor  = leftLightSensor;
        m_rightLightSensor = rightLightSensor;

        m_deploySolenoid   = deploySolenoid;
    }

    public void setPrimaryMotor(double speed)
    {
        m_primaryMotor.set(speed);
    }

    public void setLeftMotor(double speed)
    {
        m_leftMotor.set(speed);
    }

    public void setRightMotor(double speed)
    {
        m_rightMotor.set(speed);
    }

    public boolean getLeftLightSensor()
    {
        return m_leftLightSensor.get();
    }

    public boolean getRightLightSensor()
    {
        return m_rightLightSensor.get();
    }

    public void setDeploySolenoid(boolean on)
    {
        m_deploySolenoid.set(on);
    }
}
