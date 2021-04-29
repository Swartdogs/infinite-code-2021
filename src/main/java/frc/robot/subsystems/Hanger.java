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

    public ExtendState getHangerReleaseState()
    {
        return _releaseSolenoid.getExtendState();
    }

    public void setRatchetState(ExtendState state)
    {
        _ratchetSolenoid.setExtendState(state);
    }

    public double getHangerPosition()
    {
        return _hangerPositionSensor.get();
    }
}
