
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SolenoidState;

public class Shooter extends SubsystemBase 
{
    private CANSparkMax m_shooterMotor;
    
    private Solenoid m_hoodSolenoid;

    public Shooter(CANSparkMax shooterMotor, Solenoid hoodSolenoid) 
    {
        m_shooterMotor = shooterMotor;

        m_hoodSolenoid = hoodSolenoid;
    }

    public void setShooter(double speed)
    {
        m_shooterMotor.set(speed);
    }

    public void setHood(SolenoidState desiredState)
    {
        m_hoodSolenoid.set(desiredState == SolenoidState.Retracted);
    }
}
