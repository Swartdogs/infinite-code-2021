package frc.robot.subsystems;

import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.ShuffleboardTab;
import frc.robot.abstraction.SwartdogSubsystem;

public class Dashboard extends SwartdogSubsystem 
{
    private NetworkTableDouble  _flModuleRotation;
    private NetworkTableDouble  _flModuleDistance;
    private NetworkTableDouble  _frModuleRotation;
    private NetworkTableDouble  _frModuleDistance;
    private NetworkTableDouble  _blModuleRotation;
    private NetworkTableDouble  _blModuleDistance;
    private NetworkTableDouble  _brModuleRotation;
    private NetworkTableDouble  _brModuleDistance;
    private NetworkTableDouble  _robotRotation;
    private NetworkTableDouble  _ballCount;
    private NetworkTableBoolean _pickupDeployed;
    private NetworkTableBoolean _pickupActive;
    private NetworkTableBoolean _ballPathRaised;
    private NetworkTableBoolean _ballPathJammed;
    private NetworkTableBoolean _ballPathActive;
    private NetworkTableBoolean _hangerReleased;
    private NetworkTableBoolean _hangerLatched;
    private NetworkTableDouble  _hangerPosition;
    private NetworkTableDouble  _shooterHoodPosition;
    private NetworkTableDouble  _shooterRPM;

    private NetworkTableDouble  _driveFLModuleOffset;
    private NetworkTableDouble  _driveFRModuleOffset;
    private NetworkTableDouble  _driveBLModuleOffset;
    private NetworkTableDouble  _driveBRModuleOffset;
    private NetworkTableDouble  _hangerMinPosition;
    private NetworkTableDouble  _hangerMaxPosition;
    private NetworkTableDouble  _maxBallCount;
    private NetworkTableDouble  _pickupSpeed;
    private NetworkTableDouble  _pickupClogTime;
    private NetworkTableDouble  _pickupStowDelay;
    private NetworkTableDouble  _ballPathJamTime;
    private NetworkTableDouble  _ballPathRampMin;
    private NetworkTableDouble  _ballPathRampStep;
    private NetworkTableDouble  _ballPathSpeed;
    private NetworkTableDouble  _shooterRPMDeadband;
    private NetworkTableDouble  _shooterNearDistance;
    private NetworkTableDouble  _shooterFarDistance;
    private NetworkTableDouble  _hoodMinPosition;
    private NetworkTableDouble  _hoodNearPosition;
    private NetworkTableDouble  _hoodFarPosition;
    private NetworkTableDouble  _hoodMaxPosition;
    private NetworkTableDouble  _shooterNearSpeed;
    private NetworkTableDouble  _shooterFarSpeed;

    public Dashboard(ShuffleboardTab dashboardTab, ShuffleboardTab settingsTab) 
    {

    }

    public void setFrontLeftModuleRotation(double rotation)
    {
        _flModuleRotation.set(rotation);
    }

    public void setFrontLeftModuleDistance(double distance)
    {
        _flModuleDistance.set(distance);
    }

    public void setFrontRightModuleRotation(double rotation)
    {
        _frModuleRotation.set(rotation);
    }

    public void setFrontRightModuleDistance(double distance)
    {
        _frModuleDistance.set(distance);
    }

    public void setBackLeftModuleRotation(double rotation)
    {
        _blModuleRotation.set(rotation);
    }

    public void setBackLeftModuleDistance(double distance)
    {
        _blModuleDistance.set(distance);
    }

    public void setBackRightModuleRotation(double rotation)
    {
        _brModuleRotation.set(rotation);
    }

    public void setBackRightModuleDistance(double distance)
    {
        _brModuleDistance.set(distance);
    }

    public void setRobotRotation(double rotation)
    {
        _robotRotation.set(rotation);
    }

    public void setBallCount(int ballCount)
    {
        _ballCount.set(ballCount);
    }

    public void setPickupDeployed(boolean deployed)
    {
        _pickupDeployed.set(deployed);
    }

    public void setPickupActive(boolean active)
    {
        _pickupActive.set(active);
    }

    public void setBallPathRaised(boolean raised)
    {
        _ballPathRaised.set(raised);
    }

    public void setBallPathJammed(boolean jammed)
    {
        _ballPathJammed.set(jammed);
    }

    public void setBallPathActive(boolean active)
    {
        _ballPathActive.set(active);
    }

    public void setHangerReleased(boolean released)
    {
        _hangerReleased.set(released);
    }

    public void setHangerLatched(boolean latched)
    {
        _hangerLatched.set(latched);
    }

    public void setHangerPosition(double position)
    {
        _hangerPosition.set(position);
    }

    public void setShooterHoodPosition(double position)
    {
        _shooterHoodPosition.set(position);
    }

    public void setShooterRPM(double RPM)
    {
        _shooterRPM.set(RPM);
    }

    public double getDriveFLModuleOffset()
    {
        return _driveFLModuleOffset.get();
    }

    public double getDriveFRModuleOffset()
    {
        return _driveFRModuleOffset.get();
    }

    public double getDriveBLModuleOffset()
    {
        return _driveBLModuleOffset.get();
    }

    public double getDriveBRModuleOffset()
    {
        return _driveBRModuleOffset.get();
    }

    public double getHangerMinPosition()
    {
        return _hangerMinPosition.get();
    }

    public double getHangerMaxPosition()
    {
        return _hangerMaxPosition.get();
    }

    public int getMaxBallCount()
    {
        return (int)_maxBallCount.get();
    }

    public double getPickupSpeed()
    {
        return _pickupSpeed.get();
    }

    public double getPickupClogTime()
    {
        return _pickupClogTime.get();
    }

    public double getPickupStowDelay()
    {
        return _pickupStowDelay.get();
    }

    public double getBallPathJamTime()
    {
        return _ballPathJamTime.get();
    }

    public double getBallPathRampMin()
    {
        return _ballPathRampMin.get();
    }

    public double getBallPathRampStep()
    {
        return _ballPathRampStep.get();
    }

    public double getBallPathSpeed()
    {
        return _ballPathSpeed.get();
    }

    public double getShooterRPMDeadband()
    {
        return _shooterRPMDeadband.get();
    }

    public double getShooterNearDistance()
    {
        return _shooterNearDistance.get();
    }

    public double getShooterFarDistance()
    {
        return _shooterFarDistance.get();
    }

    public double getHoodMinPosition()
    {
        return _hoodMinPosition.get();
    }

    public double getHoodNearPosition()
    {
        return _hoodNearPosition.get();
    }

    public double getHoodFarPosition()
    {
        return _hoodFarPosition.get();
    }

    public double getHoodMaxPosition()
    {
        return _hoodMaxPosition.get();
    }

    public double getShooterNearSpeed()
    {
        return _shooterNearSpeed.get();
    }

    public double getShooterFarSpeed()
    {
        return _shooterFarSpeed.get();
    }
}
