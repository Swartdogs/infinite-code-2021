package frc.robot.subsystems;

import frc.robot.abstraction.Motor;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.Enumerations.State;

public class BallPath extends SwartdogSubsystem
{   
    private Motor     _trackMotor;

    private Switch    _position1Sensor;
    private Switch    _position2Sensor;
    private Switch    _shooterSensor;

    public BallPath(Motor  trackMotor,  
                    Switch position1Sensor, 
                    Switch position2Sensor, 
                    Switch shooterSensor) 
    {
        _trackMotor            = trackMotor;

        _position1Sensor       = position1Sensor;
        _position2Sensor       = position2Sensor;
        _shooterSensor         = shooterSensor;
    }

    public State getPosition1Sensor() 
    {
        return _position1Sensor.getState();
    }

    public State getPosition2Sensor() 
    {
        return _position2Sensor.getState();
    }

    public State getShooterSensor() 
    {
        return _shooterSensor.getState();
    }

    public void setTrackMotorSpeed(double speed) 
    {
        _trackMotor.set(speed);
    }
}
