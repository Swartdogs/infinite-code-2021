
package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallPath extends SubsystemBase {

    private CANSparkMax     m_upperTrackMotor;
    private CANSparkMax     m_lowerTrackMotor;

    private DigitalInput    m_position1Sensor;
    private DigitalInput    m_position2Sensor;
    private DigitalInput    m_shooterSensor;

    private Solenoid        m_upperTrackSolenoid;

    public BallPath() {

        m_upperTrackMotor       = new CANSparkMax(Constants.CAN.UPPER_TRACK_MOTOR_ID, MotorType.kBrushless);
        m_lowerTrackMotor       = new CANSparkMax(Constants.CAN.LOWER_TRACK_MOTOR_ID, MotorType.kBrushless);

        m_position1Sensor       = new DigitalInput(Constants.DIO.POSITION_1_SENSOR_PORT);
        m_position2Sensor       = new DigitalInput(Constants.DIO.POSITION_2_SENSOR_PORT);
        m_shooterSensor         = new DigitalInput(Constants.DIO.SHOOTER_SENSOR_PORT);

        m_upperTrackSolenoid    = new Solenoid(Constants.PCM.UPPER_TRACK_SOLENOID_PORT);

    }

    @Override
    public void periodic() {

    }
}
