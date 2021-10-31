
package frc.robot.subsystems;

import java.util.function.Function;

import PIDControl.PIDControl;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class Shooter extends SwartdogSubsystem 
{
    public enum Preset 
    {
        Near,
        Far,
        Vision
    }

    private Motor                    _shooterMotor;
    private Motor                    _hoodMotor;

    private PositionSensor           _hoodSensor;
    private PIDControl               _hoodPID;

    private Function<Preset, Double> _calculateHoodAngle;
    private Function<Preset, Double> _calculateShooterRPM;

    private boolean                  _shooterOn;
    private double                   _hoodSetpoint;

    private Preset                   _preset;

    public Shooter(Motor                    shooterMotor, 
                   Motor                    hoodMotor, 
                   PositionSensor           hoodSensor, 
                   PIDControl               hoodPID, 
                   Function<Preset, Double> calculateHoodAngle, 
                   Function<Preset, Double> calculateShooterRPM) 
    {
        _shooterMotor           = shooterMotor;
        _hoodMotor              = hoodMotor;

        _hoodSensor             = hoodSensor;
        _hoodPID                = hoodPID;

        _calculateHoodAngle     = calculateHoodAngle;
        _calculateShooterRPM    = calculateShooterRPM;

        setPreset(Preset.Far);
    }

    public boolean isShooterOn()
    {
        return _shooterOn;
    }
   
    public void startShooter()
    {
        _shooterOn = true;
        _shooterMotor.set(_calculateShooterRPM.apply(_preset));
    }

    public void stopShooter()
    {
        _shooterOn = false;
        _shooterMotor.set(0);
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

    public double getHoodSetpoint()
    {
        return _hoodSetpoint;
    }

    public void setPreset(Preset preset)
    {
        if (preset != _preset)
        {
            _preset       = preset;
            _hoodSetpoint = _calculateHoodAngle.apply(_preset);

            _hoodPID.setSetpoint(_hoodSetpoint, getHoodPosition());

            if (isShooterOn())
            {
                _shooterMotor.set(_calculateShooterRPM.apply(_preset));
            }
        }
    }

    public Preset getPreset()
    {
        return _preset;
    }

    @Override
    public void periodic()
    {
        _hoodMotor.set(_hoodPID.calculate(getHoodPosition()));
    }

    @Override
    public void setGameMode(GameMode mode) {
        switch (mode)
        {
            case Disabled:
                stopShooter();
                break;

            default:
                return;
        }
    }
}
