package frc.robot.subsystems;

import PIDControl.PIDControl;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class ControlPanelSpinner extends SwartdogSubsystem 
{
    Motor          _spinnerMotor;
    PositionSensor _positionSensor;
    PIDControl     _spinnerPID;

    public ControlPanelSpinner(Motor          spinnerMotor, 
                               PositionSensor positionSensor, 
                               PIDControl     spinnerPID) 
    {
        _spinnerMotor   = spinnerMotor;
        _positionSensor = positionSensor;
        _spinnerPID     = spinnerPID;
    }

    public void setSpinnerMotor(double speed)
    {
        _spinnerMotor.set(speed);
    }

    public double getSpinnerPosition()
    {
        return _positionSensor.get();
    }

    public void spinnerInit(double setpoint)
    {
        _positionSensor.reset();
        _spinnerPID.setSetpoint(setpoint, getSpinnerPosition());
    }

    public double spinnerExec()
    {
        return _spinnerPID.calculate(getSpinnerPosition());
    }

    public boolean spinnerIsFinished()
    {
        return _spinnerPID.atSetpoint();
    }
}