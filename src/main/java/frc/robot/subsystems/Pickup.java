package frc.robot.subsystems;

import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Switch;

public class Pickup extends SwartdogSubsystem 
{
    private Motor     _primaryMotor;

    private Motor     _leftMotor;
    private Motor     _rightMotor;

    private Switch    _leftLightSensor;
    private Switch    _rightLightSensor;

    private Solenoid  _deploySolenoid;

    public Pickup(Motor    primaryMotor, 
                  Motor    leftMotor, 
                  Motor    rightMotor, 
                  Switch   leftLightSensor, 
                  Switch   rightLightSensor, 
                  Solenoid deploySolenoid) 
    {
        _primaryMotor     = primaryMotor;

        _leftMotor        = leftMotor;
        _rightMotor       = rightMotor;

        _leftLightSensor  = leftLightSensor;
        _rightLightSensor = rightLightSensor;

        _deploySolenoid   = deploySolenoid;
    }

    public void setPrimaryMotor(double speed)
    {
        _primaryMotor.set(speed);
    }

    public void setLeftMotor(double speed)
    {
        _leftMotor.set(speed);
    }

    public void setRightMotor(double speed)
    {
        _rightMotor.set(speed);
    }

    public State getLeftLightSensor()
    {
        return _leftLightSensor.getState();
    }

    public State getRightLightSensor()
    {
        return _rightLightSensor.getState();
    }

    public void setDeploySolenoid(ExtendState desiredState)
    {
        _deploySolenoid.setExtendState(desiredState);
    }
}
