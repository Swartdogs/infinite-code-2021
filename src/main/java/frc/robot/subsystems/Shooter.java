
package frc.robot.subsystems;

import PIDControl.PIDControl;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class Shooter extends SwartdogSubsystem 
{
    private Motor _shooterMotor;
    private Motor _hoodMotor;

    private PositionSensor _hoodSensor;

    private PIDControl     _hoodPID;

    public Shooter(Motor shooterMotor, Motor hoodMotor, PositionSensor hoodSensor, PIDControl hoodPID) 
    {
        _shooterMotor = shooterMotor;
        _hoodMotor    = hoodMotor;

        _hoodSensor   = hoodSensor;

        _hoodPID      = hoodPID;
    }

    public Motor getShooterMotor()
    {
        return _shooterMotor;
    }

    public Motor getHoodMotor()
    {
        return _hoodMotor;
    }

    public PositionSensor getHoodPositionSensor()
    {
        return _hoodSensor;
    }

    public boolean hoodAtSetpoint()
    {
        return _hoodPID.atSetpoint();
    }

    public double hoodExec()
    {
        return _hoodPID.calculate(_hoodSensor.get());
    }

    public void setHoodSetpoint(double setpoint)
    {
        _hoodPID.setSetpoint(setpoint, _hoodSensor.get());
    }
}
