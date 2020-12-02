package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase 
{
    private CANSparkMax m_spinnerMotor;

    public Spinner(CANSparkMax spinnerMotor) 
    {
        m_spinnerMotor = spinnerMotor;
    }
}
