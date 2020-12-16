package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallPath extends SubsystemBase 
{
    public enum UpperTrackState {
        Raised,
        Lowered
    }

    public enum TrackMotorState {
        Forwards,
        Backwards,
        Stopped
    }

    private UpperTrackState     m_upperTrackState;
    private TrackMotorState     m_trackMotorState;

    private CANSparkMax     m_upperTrackMotor;
    private CANSparkMax     m_lowerTrackMotor;

    private DigitalInput    m_position1Sensor;
    private DigitalInput    m_position2Sensor;
    private DigitalInput    m_shooterSensor;

    private Solenoid        m_upperTrackSolenoid;

    public BallPath(CANSparkMax upperTrackMotor, CANSparkMax lowerTrackMotor, DigitalInput position1Sensor, 
                    DigitalInput position2Sensor, DigitalInput shooterSensor, Solenoid upperTrackSolenoid) 
    {
        m_upperTrackState       = UpperTrackState.Raised;
        m_trackMotorState       = TrackMotorState.Stopped;

        m_upperTrackMotor       = upperTrackMotor;
        m_lowerTrackMotor       = lowerTrackMotor;

        m_position1Sensor       = position1Sensor;
        m_position2Sensor       = position2Sensor;
        m_shooterSensor         = shooterSensor;

        m_upperTrackSolenoid    = upperTrackSolenoid;

        m_upperTrackMotor.setIdleMode(IdleMode.kBrake);
        m_lowerTrackMotor.setIdleMode(IdleMode.kBrake);

        m_lowerTrackMotor.setInverted(true);


    }

    public boolean getPosition1Sensor() {
        return m_position1Sensor.get();
    }

    public boolean getPosition2Sensor() {
        return m_position2Sensor.get();
    }

    public boolean getShooterSensor() {
        return m_shooterSensor.get();
    }

    public void setUpperTrackMotorSpeed(double speed) {
        m_upperTrackMotor.set(speed);
    }

    public void setLowerTrackMotorSpeed(double speed) {
        m_lowerTrackMotor.set(speed);
    }

    public void setUpperTrackSolenoid(UpperTrackState state) {
        m_upperTrackSolenoid.set(state == UpperTrackState.Raised);
    }
}
