package frc.robot.subsystems;

import frc.robot.abstraction.Motor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.abstraction.Enumerations.State;

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

    public void setPrimaryMotor(double speed)
    {
        _primaryMotor.set(speed);
    }

    public double getPrimaryMotor()
    {
        return _primaryMotor.get();
    }

    public void setLeftMotor(double speed)
    {
        _leftMotor.set(speed);
    }

    public void setRightMotor(double speed)
    {
        _rightMotor.set(speed);
    }

    public double getRightMotor()
    {
        return _rightMotor.get();
    }

    public void deployPickup()
    {
        _deploySolenoid.retract();
    }

    public void stowPickup()
    {
        _deploySolenoid.extend();
    }

    public boolean isPickupDeployed()
    {
        return _deploySolenoid.get() == ExtendState.Retracted;
    }

    public State getLeftLightSensor()
    {
        return _leftLightSensor.get();
    }

    public State getRightLightSensor()
    {
        return _rightLightSensor.get();
    }

    @Override
    public void setGameMode(GameMode mode) {
        switch (mode)
        {
            case Disabled:
                stowPickup();
                setPrimaryMotor(0);
                setRightMotor(0);
                setLeftMotor(0);
                break;

            default:
                return;
        }
    }
}
