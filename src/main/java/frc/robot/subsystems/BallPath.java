package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Switch;

public class BallPath extends SwartdogSubsystem
{   
    private Motor     _trackMotor;
    private Solenoid  _upperTrackSolenoid;

    private Switch    _position1Sensor;
    private Switch    _position2Sensor;
    private Switch    _shooterSensor;

    private int       _ballCount;
    private boolean   _isJammed;

    public BallPath(Motor    trackMotor,  
                    Solenoid upperTrackSolenoid,
                    Switch   position1Sensor, 
                    Switch   position2Sensor, 
                    Switch   shooterSensor) 
    {
        _trackMotor            = trackMotor;
        _upperTrackSolenoid    = upperTrackSolenoid;

        _position1Sensor       = position1Sensor;
        _position2Sensor       = position2Sensor;
        _shooterSensor         = shooterSensor;

        _isJammed              = false;
    }

    public Motor getTrackMotor()
    {
        return _trackMotor;
    }

    public Solenoid getUpperTrackSolenoid()
    {
        return _upperTrackSolenoid;
    }

    public Switch getPosition1Sensor()
    {
        return _position1Sensor;
    }

    public Switch getPosition2Sensor()
    {
        return _position2Sensor;
    }

    public Switch getShooterSensor()
    {
        return _shooterSensor;
    }

    public int getBallCount()
    {
        return _ballCount;
    }

    public void incrementBallCount(int amount)
    {
        _ballCount += amount;
        
        if (_ballCount > Constants.MAX_BALL_COUNT)
        {
            _ballCount = Constants.MAX_BALL_COUNT;
        }

        if (_ballCount < 0) 
        {
            _ballCount = 0;
        }
    }

    public void incrementBallCount()
    {
        incrementBallCount(1);
    }

    public void decrementBallCount()
    {
        incrementBallCount(-1);
    }

    public boolean isJammed()
    {
        return _isJammed;
    }

    public void setJammed(boolean jammed)
    {
        _isJammed = jammed;
    }
}
