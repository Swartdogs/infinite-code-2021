
package frc.robot.subsystems;

import PIDControl.PIDControl;
import frc.robot.Constants;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class Shooter extends SwartdogSubsystem 
{
    private Motor           _shooterMotor;
    private Motor           _hoodMotor;

    private PositionSensor  _hoodSensor;
    private PIDControl      _hoodPID;

    private boolean         _shooterOn;
    private double          _targetDistance;
    private double          _hoodSetpoint;

    public Shooter(Motor shooterMotor, Motor hoodMotor, PositionSensor hoodSensor, PIDControl hoodPID) 
    {
        _shooterMotor   = shooterMotor;
        _hoodMotor      = hoodMotor;

        _hoodSensor     = hoodSensor;
        _hoodPID        = hoodPID;

        _targetDistance = Constants.SHOOTER_FAR_DISTANCE;
        _hoodSetpoint   = 0;
    }

    public boolean isShooterOn()
    {
        return _shooterOn;
    }
   
    public void startShooter()
    {
        _shooterOn = true;
    }

    public void stopShooter()
    {
        _shooterOn = false;
    }

    public double getTargetDistance()
    {
        return _targetDistance;
    }

    public void setTargetDistance(double distance)
    {
        _targetDistance = distance;
    }

    public void setShooterMotor(double speed)
    {
        _shooterMotor.set(speed);
    }

    public double getShooterMotorSetpoint()
    {
        return _shooterMotor.get();
    }

    public double getShooterMotor()
    {
        return _shooterMotor.getVelocitySensor().get();
    }

    public void setHoodMotor(double speed)
    {
        _hoodMotor.set(speed);
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
        return _hoodPID.calculate(_hoodSensor.get());
    }

    public void setHoodSetpoint(double setpoint)
    {
        _hoodSetpoint = setpoint;
        _hoodPID.setSetpoint(_hoodSetpoint, _hoodSensor.get());
    }

    public double getHoodSetpoint()
    {
        return _hoodSetpoint;
    }
}
