
package frc.robot.subsystems;

import java.util.function.DoubleUnaryOperator;

import PIDControl.PIDControl;
import frc.robot.Constants;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class Shooter extends SwartdogSubsystem 
{
    public enum Preset 
    {
        Near,
        Far
    }

    private Motor               _shooterMotor;
    private Motor               _hoodMotor;

    private PositionSensor      _hoodSensor;
    private PIDControl          _hoodPID;

    private DoubleUnaryOperator _calculateHoodAngle;
    private DoubleUnaryOperator _calculateShooterRPM;

    private boolean             _shooterOn;
    private double              _targetDistance;
    private double              _hoodSetpoint;

    private Preset              _preset;
    private boolean             _visionInUse;


    public Shooter(Motor shooterMotor, Motor hoodMotor, PositionSensor hoodSensor, PIDControl hoodPID, DoubleUnaryOperator calculateHoodAngle, DoubleUnaryOperator calculateShooterRPM) 
    {
        _shooterMotor           = shooterMotor;
        _hoodMotor              = hoodMotor;

        _hoodSensor             = hoodSensor;
        _hoodPID                = hoodPID;

        _calculateHoodAngle     = calculateHoodAngle;
        _calculateShooterRPM    = calculateShooterRPM;

        _targetDistance         = Constants.SHOOTER_NEAR_DISTANCE;

        _preset                 = Preset.Near;
        _visionInUse            = false;

        setHoodSetpoint(_calculateHoodAngle.applyAsDouble(_targetDistance));
    }

    public boolean isShooterOn()
    {
        return _shooterOn;
    }
   
    public void startShooter()
    {
        _shooterOn = true;
        _shooterMotor.set(_calculateShooterRPM.applyAsDouble(_targetDistance));
    }

    public void stopShooter()
    {
        _shooterOn = false;
        _shooterMotor.set(0);
    }

    public double getTargetDistance()
    {
        return _targetDistance;
    }

    public void setTargetDistance(double distance)
    {
        _targetDistance = distance;
        setHoodSetpoint(_calculateHoodAngle.applyAsDouble(_targetDistance));

        if (isShooterOn()) 
        {
            _shooterMotor.set(_calculateShooterRPM.applyAsDouble(_targetDistance));
        }
    }

    public double getShooterMotorSetpoint()
    {
        return _shooterMotor.get();
    }

    public double getShooterMotor()
    {
        return _shooterMotor.getVelocitySensor().get();
    }

    public double getHoodPosition()
    {
        return _hoodSensor.get();
    }

    public boolean hoodAtSetpoint()
    {
        return _hoodPID.atSetpoint();
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

    public void setPreset(Preset preset)
    {
        _preset = preset;
    }

    public void setVisionInUse(boolean visionInUse)
    {
        _visionInUse = visionInUse;
    }

    @Override
    public void periodic()
    {
        _hoodMotor.set(_hoodPID.calculate(getHoodPosition()));
    }
}
