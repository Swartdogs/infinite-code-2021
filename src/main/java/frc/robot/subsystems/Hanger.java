package frc.robot.subsystems;

import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;

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

    public Motor getHangerMotor()
    {
        return _hangerMotor;
    }

    public Solenoid getReleaseSolenoid()
    {
        return _releaseSolenoid;
    }

    public Solenoid getRatchetSolenoid()
    {
        return _ratchetSolenoid;
    }

    public PositionSensor getHangerPositionSensor()
    {
        return _hangerPositionSensor;
    }
}
