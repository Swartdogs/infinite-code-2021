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

    public Motor getSpinnerMotor()
    {
        return _spinnerMotor;
    }

    public PositionSensor getPositionSensor()
    {
        return _positionSensor;
    }

    public void spinnerInit(double setpoint)
    {
        _positionSensor.reset();
        _spinnerPID.setSetpoint(setpoint, _positionSensor.get());
    }

    public double spinnerExec()
    {
        return _spinnerPID.calculate(_positionSensor.get());
    }

    public boolean spinnerIsFinished()
    {
        return _spinnerPID.atSetpoint();
    }
}