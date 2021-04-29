package frc.robot.subsystems;

import PIDControl.PIDControl;
import frc.robot.Constants;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Enumerations.State;

public class Vision extends SwartdogSubsystem
{
    NetworkTableDouble  _xPosition;
    NetworkTableDouble  _yPosition;
    NetworkTableBoolean _targetFound;
    NetworkTableDouble  _ledMode;
    PIDControl          _rotatePID;

    public Vision(NetworkTableDouble  xPosition,
                  NetworkTableDouble  yPosition,
                  NetworkTableBoolean targetFound,
                  NetworkTableDouble  ledMode,
                  PIDControl          rotatePID)
    {
        _xPosition   = xPosition;
        _yPosition   = yPosition;
        _targetFound = targetFound;
        _ledMode     = ledMode;
        _rotatePID   = rotatePID;

        _ledMode.set(Constants.LIMELIGHT_LED_OFF);
    }

    // target distance

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
        return _targetFound.get();
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
        _rotatePID.setSetpoint(0, getTargetAngle());
    }

    public double rotateExec()
    {
        return _rotatePID.calculate(getTargetAngle());
    }

    public boolean rotateIsFinished()
    {
        return _rotatePID.atSetpoint();
    }
}
