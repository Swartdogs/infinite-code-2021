package frc.robot.subsystems;

import frc.robot.Constants;

import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.NetworkTableString;
import frc.robot.abstraction.ShuffleboardTab;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.subsystems.drive.Vector;
import frc.robot.abstraction.ShuffleboardLayout;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;

import java.util.Map;

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
    private NetworkTableString _pickupDeployed;
    private NetworkTableString _pickupActive;
    private NetworkTableString _ballPathRaised;
    private NetworkTableBoolean _ballPathJammed;
    private NetworkTableString _ballPathActive;
    private NetworkTableString _hangerReleased;
    private NetworkTableString _hangerLatched;
    private NetworkTableDouble _hangerPosition;
    private NetworkTableDouble _shooterHoodPosition;
    private NetworkTableDouble _shooterHoodTarget;
    private NetworkTableDouble _shooterRPM;
    private NetworkTableBoolean _shooterOn;

    private NetworkTableDouble  _driveFLModuleOffset;
    private NetworkTableDouble  _driveFRModuleOffset;
    private NetworkTableDouble  _driveBLModuleOffset;
    private NetworkTableDouble  _driveBRModuleOffset;
    private NetworkTableDouble  _hangerMinPosition;
    private NetworkTableDouble  _hangerMaxPosition;
    private NetworkTableDouble  _hangerSpeed;
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
        _ballCount = dashboardTab.addDoubleWidget("Ball Count", 0, 11, 7, 11, 5, BuiltInWidgets.kDial, Map.of("Min", 0, "Max", Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT, "Show value", true));
        dashboardTab.addAutonomousChooser(11, 0, 11, 2, BuiltInWidgets.kComboBoxChooser);

        ShuffleboardLayout ballPath = dashboardTab.addShuffleboardLayout("Ball Path Subsystem", BuiltInLayouts.kList, 23, 7, 10, 5, Map.of("Label position", "LEFT"));
        _ballPathRaised = ballPath.addStringWidget("Upper Track State", "Raised", BuiltInWidgets.kTextView,   null);
        _ballPathActive = ballPath.addStringWidget("Ball Path State",   "Active", BuiltInWidgets.kTextView,   null);
        _ballPathJammed = ballPath.addBooleanWidget("Ball Path Jammed", false,    BuiltInWidgets.kBooleanBox, Map.of("Color when true", "Red", "Color when false", "Lime"));

        ShuffleboardLayout drive = dashboardTab.addShuffleboardLayout("Drive Subsystem", BuiltInLayouts.kGrid, 0, 0, 10, 7, Map.of("Number of columns", 2, "Number of rows", 2, "Label position", "LEFT"));
      
        _robotRotation = dashboardTab.addDoubleWidget("Gyro", 0, 0, 7, 10, 5, BuiltInWidgets.kNumberBar, Map.of("Min", -180, "Max", 180, "Center", 0, "Num tick marks", 7, "Show text", true));

        ShuffleboardLayout hanger = dashboardTab.addShuffleboardLayout("Hanger Subsystem", BuiltInLayouts.kList, 23, 3, 10, 4, Map.of("Label position", "LEFT"));
        _hangerReleased = hanger.addStringWidget("Deploy State",  "Deployed", BuiltInWidgets.kTextView, null);
        _hangerLatched  = hanger.addStringWidget("Ratchet State", "Latched",  BuiltInWidgets.kTextView, null);
        _hangerPosition = hanger.addDoubleWidget("Position",      0,          BuiltInWidgets.kTextView, Map.of("Color when false", "Lime", "Color when true", "Red"));

        ShuffleboardLayout pickup = dashboardTab.addShuffleboardLayout("Pickup Subsystem", BuiltInLayouts.kList, 23, 0, 10, 3, Map.of("Label position", "LEFT"));
        _pickupDeployed = pickup.addStringWidget("Deploy State", "Deployed", BuiltInWidgets.kTextView, null);
        _pickupActive   = pickup.addStringWidget("Motor State",  "Active",   BuiltInWidgets.kTextView, null);

        ShuffleboardLayout shooter = dashboardTab.addShuffleboardLayout("Shooter Subsystem", BuiltInLayouts.kList, 11, 3, 11, 7, Map.of("Label position", "LEFT"));
        _shooterOn           = shooter.addBooleanWidget("Shooter On",   false, BuiltInWidgets.kBooleanBox, Map.of("Color when true", "Lime", "Color when false", "Red"));
        _shooterRPM          = shooter.addDoubleWidget("Shooter RPM",   0,     BuiltInWidgets.kTextView,   null);
        _shooterHoodTarget   = shooter.addDoubleWidget("Hood Target",   0,     BuiltInWidgets.kTextView,   null);
        _shooterHoodPosition = shooter.addDoubleWidget("Hood Position", 0,     BuiltInWidgets.kTextView,   null);

        ShuffleboardLayout shootSetting = settingsTab.addShuffleboardLayout("Shooter", BuiltInLayouts.kList, 8, 1, 11, 5, Map.of("Label position", "LEFT"));
        _hoodMinPosition = shootSetting.addDoubleWidget("Hood Low Limit",  0, BuiltInWidgets.kTextView, null);
        _hoodMaxPosition = shootSetting.addDoubleWidget("Hood High Limit", 0, BuiltInWidgets.kTextView, null);

        ShuffleboardLayout ballPathSetting = settingsTab.addShuffleboardLayout("Ball Path", BuiltInLayouts.kList, 8, 6, 11, 5, Map.of("Label position", "LEFT"));
        _ballPathJamTime  = ballPathSetting.addDoubleWidget("Ball Path Jam Timeout", 0, BuiltInWidgets.kTextView, null);
        _ballPathSpeed    = ballPathSetting.addDoubleWidget("Ball Path Speed",       0, BuiltInWidgets.kTextView, null);
        _ballPathRampMin  = ballPathSetting.addDoubleWidget("Ball Path Ramp Min",    0, BuiltInWidgets.kTextView, null);
        _ballPathRampStep = ballPathSetting.addDoubleWidget("Ball Path Ramp Step",   0, BuiltInWidgets.kTextView, null);

        ShuffleboardLayout hangerSetting = settingsTab.addShuffleboardLayout("Hanger", BuiltInLayouts.kList, 20, 1, 11, 5, Map.of("Label position", "LEFT"));
        _hangerSpeed       = hangerSetting.addDoubleWidget("Hanger Speed",      0, BuiltInWidgets.kTextView, null);
        _hangerMinPosition = hangerSetting.addDoubleWidget("Hanger Low Limit",  0, BuiltInWidgets.kTextView, null);
        _hangerMaxPosition = hangerSetting.addDoubleWidget("Hanger High Limit", 0, BuiltInWidgets.kTextView, null);

        ShuffleboardLayout pickupSetting = settingsTab.addShuffleboardLayout("Pickup", BuiltInLayouts.kList, 20, 6, 11, 5, Map.of("Label position", "LEFT"));
        _pickupSpeed = pickupSetting.addDoubleWidget("Pickup Speed", 0, BuiltInWidgets.kTextView, null);
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
        rotation = Vector.normalizeAngle(rotation);

        if (rotation > 180)
        {
            rotation -= 360;
        }

        _robotRotation.set(rotation);
    }

    public void setBallCount(int ballCount)
    {
        _ballCount.set(ballCount);
    }

    public void setPickupDeployed(boolean deployed)
    {
        _pickupDeployed.set(deployed ? "Deployed" : "Stowed");
    }

    public void setPickupActive(boolean active)
    {
        _pickupActive.set(active ? "Active" : "Disabled");
    }

    public void setBallPathRaised(boolean raised)
    {
        _ballPathRaised.set(raised ? "Raised" : "Lowered");
    }

    public void setBallPathJammed(boolean jammed)
    {
        _ballPathJammed.set(jammed);
    }

    public void setBallPathActive(boolean active)
    {
        _ballPathActive.set(active ? "Active" : "Disabled");
    }

    public void setHangerReleased(boolean released)
    {
        _hangerReleased.set(released ? "Deployed" : "Stowed");
    }

    public void setHangerLatched(boolean latched)
    {
        _hangerLatched.set(latched ? "Latched" : "Released");
    }

    public void setHangerPosition(double position)
    {
        _hangerPosition.set(position);
    }

    public void setShooterHoodPosition(double position)
    {
        _shooterHoodPosition.set(position);
    }

    public void setShooterHoodTarget(double target)
    {
        _shooterHoodTarget.set(target);
    }

    public void setShooterRPM(double RPM)
    {
        _shooterRPM.set(RPM);
    }

    public void setShooterOn(boolean shooterOn)
    {
        _shooterOn.set(shooterOn);
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

    public double getHangerSpeed()
    {
        return _hangerSpeed.get();
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
