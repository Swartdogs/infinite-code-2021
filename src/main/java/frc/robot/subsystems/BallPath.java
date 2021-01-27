package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LightSensorState;

public class BallPath extends SubsystemBase 
{   
    private static double   TRACK_SPEED_FORWARD     = 1.0;
    private static double   TRACK_SPEED_BACKWARD    = -1.0;
    private static double   TRACK_SPEED_STOP        = 0;

    public enum TrackMotorState {
        Forward,
        Backward,
        Stop
    }

    private CANSparkMax     m_upperTrackMotor;
    private CANSparkMax     m_lowerTrackMotor;

    private DigitalInput    m_position1Sensor;
    private DigitalInput    m_position2Sensor;
    private DigitalInput    m_shooterSensor;

    public BallPath(CANSparkMax upperTrackMotor, CANSparkMax lowerTrackMotor, DigitalInput position1Sensor, 
                    DigitalInput position2Sensor, DigitalInput shooterSensor) 
    {
        m_upperTrackMotor       = upperTrackMotor;
        m_lowerTrackMotor       = lowerTrackMotor;

        m_position1Sensor       = position1Sensor;
        m_position2Sensor       = position2Sensor;
        m_shooterSensor         = shooterSensor;

        m_upperTrackMotor.setIdleMode(IdleMode.kBrake);
        m_lowerTrackMotor.setIdleMode(IdleMode.kBrake);

        m_lowerTrackMotor.setInverted(true);
    }

    public LightSensorState getPosition1Sensor() 
    {
        return m_position1Sensor.get() ? LightSensorState.NotDetected : LightSensorState.Detected;
    }

    public LightSensorState getPosition2Sensor() 
    {
        return m_position2Sensor.get() ? LightSensorState.NotDetected : LightSensorState.Detected;
    }

    public LightSensorState getShooterSensor() 
    {
        return m_shooterSensor.get() ? LightSensorState.NotDetected : LightSensorState.Detected;
    }

    public void setUpperTrackMotorSpeed(double speed) 
    {
        m_upperTrackMotor.set(speed);
    }

    public void setLowerTrackMotorSpeed(double speed) 
    {
        m_lowerTrackMotor.set(speed);
    }
}
