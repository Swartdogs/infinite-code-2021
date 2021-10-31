package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.abstraction.Enumerations.State;

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

        _ballCount             = Constants.DEFAULT_BALLPATH_STARTING_BALL_COUNT;
        _isJammed              = false;
    }

    public double getTrackMotor()
    {
        return _trackMotor.get();
    }

    public void setTrackMotor(double speed)
    {
        _trackMotor.set(speed);
    }

    public void raiseUpperTrack()
    {
        _upperTrackSolenoid.retract();
    }

    public void lowerUpperTrack()
    {
        _upperTrackSolenoid.extend();
    }

    public boolean isUpperTrackRaised()
    {
        return _upperTrackSolenoid.get() == ExtendState.Retracted;
    }

    public boolean position1SensorTransitionedTo(State state)
    {
        return _position1Sensor.transitionedTo(state);
    }

    public boolean position2SensorTransitionedTo(State state)
    {
        return _position2Sensor.transitionedTo(state);
    }

    public boolean shooterSensorTransitionedTo(State state)
    {
        return _shooterSensor.transitionedTo(state);
    }

    public int getBallCount()
    {
        return _ballCount;
    }

    public void incrementBallCount()
    {
        modifyBallCount(1);
    }

    public void decrementBallCount()
    {
        modifyBallCount(-1);
    }

    private void modifyBallCount(int amount)
    {
        _ballCount += amount;
        
        if (_ballCount > Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT)
        {
            _ballCount = Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT;
        }

        if (_ballCount < 0) 
        {
            _ballCount = 0;
        }
    }

    public boolean isJammed()
    {
        return _isJammed;
    }

    public void setJammed(boolean jammed)
    {
        _isJammed = jammed;
    }

    @Override
    public void setGameMode(GameMode mode) {
        switch (mode)
        {
            case Disabled:
                setTrackMotor(0);
                raiseUpperTrack();    
                break;

            default:
                break;
        }
    }
}
