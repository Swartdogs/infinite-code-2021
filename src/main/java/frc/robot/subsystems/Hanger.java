package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hanger extends SubsystemBase 
{
    private WPI_VictorSPX m_hoist;

    private AnalogInput m_potentiometer;

    private Solenoid m_release;
    private Solenoid m_ratchet;

    public Hanger(WPI_VictorSPX hoist, AnalogInput potentiometer, Solenoid release, Solenoid ratchet) 
    {
        m_hoist = hoist;
        m_potentiometer = potentiometer;
        m_release = release;
        m_ratchet = ratchet;
    }
}
