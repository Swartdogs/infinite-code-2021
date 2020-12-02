
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase 
{
    private CANSparkMax m_primaryMotor;
    private CANSparkMax m_secondaryMotor;
    
    private Solenoid m_hoodSolenoid;

    public Shooter(CANSparkMax primaryMotor, CANSparkMax secondaryMotor, Solenoid hoodSolenoid) 
    {
        m_primaryMotor = primaryMotor;
        m_secondaryMotor = secondaryMotor;

        m_hoodSolenoid = hoodSolenoid;
    }
}
