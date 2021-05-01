package frc.robot.subsystems;

import frc.robot.abstraction.Motor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Switch;

public class Pickup extends SwartdogSubsystem 
{
    private Motor     _primaryMotor;

    private Motor     _leftMotor;
    private Motor     _rightMotor;

    private Solenoid  _deploySolenoid;

    private Switch    _leftLightSensor;
    private Switch    _rightLightSensor;

    public Pickup(Motor    primaryMotor, 
                  Motor    leftMotor, 
                  Motor    rightMotor, 
                  Solenoid deploySolenoid,
                  Switch   leftLightSensor, 
                  Switch   rightLightSensor)
    {
        _primaryMotor     = primaryMotor;

        _leftMotor        = leftMotor;
        _rightMotor       = rightMotor;

        _deploySolenoid   = deploySolenoid;

        _leftLightSensor  = leftLightSensor;
        _rightLightSensor = rightLightSensor;
    }

    public Motor getPrimaryMotor()
    {
        return _primaryMotor;
    }

    public Motor getLeftMotor()
    {
        return _leftMotor;
    }

    public Motor getRightMotor()
    {
        return _rightMotor;
    }

    public Solenoid getDeploySolenoid()
    {
        return _deploySolenoid;
    }

    public Switch getLeftLightSensor()
    {
        return _leftLightSensor;
    }

    public Switch getRightLightSensor()
    {
        return _rightLightSensor;
    }
}
