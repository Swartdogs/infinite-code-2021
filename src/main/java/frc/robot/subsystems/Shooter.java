
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

    public double getHoodPosition()
    {
        return _hoodSensor.get();
    }

    public boolean hoodAtSetpoint()
    {
        return _hoodPID.atSetpoint();
    }

    public double hoodExec()
    {
        return _hoodPID.calculate(getHoodPosition());
    }

    public void setHood(double speed)
    {
        _hoodMotor.set(speed);
    }

    public void setHoodSetpoint(double setpoint)
    {
        _hoodPID.setSetpoint(setpoint, getHoodPosition());
    }

    public void setShooter(double speed)
    {
        _shooterMotor.set(speed);
    }

}
