package frc.robot.subsystems;

import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Enumerations.ExtendState;

public class Hanger extends SwartdogSubsystem
{
    Motor          _hangerMotor;
    Solenoid       _releaseSolenoid;
    Solenoid       _ratchetSolenoid;
    PositionSensor _hangerPositionSensor;

    public Hanger(Motor          hangerMotor, 
                  Solenoid       releaseSolenoid, 
                  Solenoid       ratchetSolenoid, 
                  PositionSensor hangerPositionSensor)
    {
        _hangerMotor          = hangerMotor;
        _releaseSolenoid      = releaseSolenoid;
        _ratchetSolenoid      = ratchetSolenoid;
        _hangerPositionSensor = hangerPositionSensor;
    }

    public void setHangerMotor(double speed)
    {
        _hangerMotor.set(speed);
    }

    public void releaseHanger()
    {
        _releaseSolenoid.retract();
    }

    public boolean isHangerReleased()
    {
        return _releaseSolenoid.get() == ExtendState.Retracted;
    }

    public void engageRatchet()
    {
        _ratchetSolenoid.retract();
    }

    public void disengageRatchet()
    {
        _ratchetSolenoid.extend();
    }

    public double getPosition()
    {
        return _hangerPositionSensor.get();
    }
}
