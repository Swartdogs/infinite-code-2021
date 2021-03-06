package frc.robot.subsystems;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;
import frc.robot.Constants;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Enumerations.State;

public class Vision extends SwartdogSubsystem
{
    NetworkTableDouble  _xPosition;
    NetworkTableDouble  _yPosition;
    NetworkTableDouble  _targetFound;
    NetworkTableDouble  _ledMode;
    NetworkTableDouble  _pipeline;
    PIDControl          _rotatePID;

    public Vision(NetworkTableDouble  xPosition,
                  NetworkTableDouble  yPosition,
                  NetworkTableDouble  targetFound,
                  NetworkTableDouble  ledMode,
                  NetworkTableDouble  pipeline,
                  PIDControl          rotatePID)
    {
        _xPosition   = xPosition;
        _yPosition   = yPosition;
        _targetFound = targetFound;
        _ledMode     = ledMode;
        _pipeline    = pipeline;
        _rotatePID   = rotatePID;

        _ledMode.set(Constants.LIMELIGHT_LED_OFF);
    }

    public void enableVisionProcessing()
    {
        setLEDs(State.On);
        _pipeline.set(Constants.LIMELIGHT_VISION_PROCESSING);
    }

    public void disableVisionProcessing()
    {
        setLEDs(State.Off);
        _pipeline.set(Constants.LIMELIGHT_DRIVER_CAMERA);
    }

    public void setLEDs(State state)
    {
        switch (state)
        {
            case On:
                _ledMode.set(Constants.LIMELIGHT_LED_ON);
                break;

            case Off:
                _ledMode.set(Constants.LIMELIGHT_LED_OFF);
                break;

            default:
                break;
        }
    }

    public boolean targetFound()
    {
        return _targetFound.get() > Constants.LIMELIGHT_TARGET_NOT_FOUND;
    }

    public double getTargetAngle()
    {
        return _xPosition.get();
    }

    public double getTargetDistance()
    {
        return Constants.TARGET_HEIGHT_DELTA / 
               Math.tan(Math.toRadians(Constants.LIMELIGHT_ANGLE + _yPosition.get()));
    }

    public void rotateInit()
    {
        _rotatePID.setSetpoint(Constants.VISION_OFFSET, -getTargetAngle());
    }

    public double rotateExec()
    {
        return _rotatePID.calculate(-getTargetAngle());
    }

    public boolean rotateIsFinished()
    {
        return _rotatePID.atSetpoint();
    }

    @Override
    public void setGameMode(GameMode mode) {
        switch (mode)
        {
            case Disabled:
                disableVisionProcessing();

                _rotatePID.setCoefficient(Coefficient.P, 0,  0.115, 0);
                _rotatePID.setCoefficient(Coefficient.I, 10, 0,     0.001);
                _rotatePID.setCoefficient(Coefficient.D, 0,  0,     0);    
                break;

            case Autonomous:
                _rotatePID.setCoefficient(Coefficient.P, 0,  0.015, 0);
                _rotatePID.setCoefficient(Coefficient.I, 20, 0,     0.001);
                _rotatePID.setCoefficient(Coefficient.D, 0,  0,     0);        
                break;

            default:
                return;
        }
    }
}
