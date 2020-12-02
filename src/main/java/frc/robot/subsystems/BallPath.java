
package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallPath extends SubsystemBase {

    private CANSparkMax     m_upperTrackMotor;
    private CANSparkMax     m_lowerTrackMotor;

    private DigitalInput    m_position1Sensor;
    private DigitalInput    m_position2Sensor;
    private DigitalInput    m_shooterSensor;

    private Solenoid        m_upperTrackSolenoid;

    public BallPath(CANSparkMax upperTrackMotor, CANSparkMax lowerTrackMotor, DigitalInput position1Sensor, 
                    DigitalInput position2Sensor, DigitalInput shooterSensor, Solenoid upperTrackSolenoid) {

        m_upperTrackMotor       = upperTrackMotor;
        m_lowerTrackMotor       = lowerTrackMotor;

        m_position1Sensor       = position1Sensor;
        m_position2Sensor       = position2Sensor;
        m_shooterSensor         = shooterSensor;

        m_upperTrackSolenoid    = upperTrackSolenoid;

    }

}
