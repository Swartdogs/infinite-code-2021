
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SolenoidState;

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

    public void setShooter(double speed)
    {
        m_primaryMotor.set(speed);
        m_secondaryMotor.set(speed);
    }

    public void setHood(SolenoidState desiredState)
    {
        if (SolenoidState.Retracted == desiredState)
        {
            m_hoodSolenoid.set(true);
        }
        else
        {
            m_hoodSolenoid.set(false);
        }
    }
}
