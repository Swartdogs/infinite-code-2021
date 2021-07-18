package frc.robot;

import PIDControl.PIDControl;
import frc.robot.abstraction.Joystick;
import frc.robot.abstraction.MockJoystick;
import frc.robot.abstraction.MockMotor;
import frc.robot.abstraction.MockNetworkTableBoolean;
import frc.robot.abstraction.MockNetworkTableDouble;
import frc.robot.abstraction.MockPositionSensor;
import frc.robot.abstraction.MockShuffleboardTab;
import frc.robot.abstraction.MockSolenoid;
import frc.robot.abstraction.MockSwitch;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.ShuffleboardTab;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;

public class MockRobotMap implements RobotMap 
{
    private MockJoystick            _driveJoy;
    private MockJoystick            _coDriveJoy;

    private MockPositionSensor      _driveGyro;
    private PIDControl              _driveDrivePID;
    private PIDControl              _driveRotatePID;
    private MockMotor               _driveFLDriveMotor;
    private MockMotor               _driveFLRotateMotor;
    private MockPositionSensor      _driveFLPositionSensor;
    private PIDControl              _driveFLPIDControl;
    private MockMotor               _driveFRDriveMotor;
    private MockMotor               _driveFRRotateMotor;
    private MockPositionSensor      _driveFRPositionSensor;
    private PIDControl              _driveFRPIDControl;
    private MockMotor               _driveBLDriveMotor;
    private MockMotor               _driveBLRotateMotor;
    private MockPositionSensor      _driveBLPositionSensor;
    private PIDControl              _driveBLPIDControl;
    private MockMotor               _driveBRDriveMotor;
    private MockMotor               _driveBRRotateMotor;
    private MockPositionSensor      _driveBRPositionSensor;
    private PIDControl              _driveBRPIDControl;

    private MockMotor               _ballPathTrackMotor;
    private MockSolenoid            _ballPathUpperTrackSolenoid;
    private MockSwitch              _ballPathPosition1Sensor;
    private MockSwitch              _ballPathPosition2Sensor;
    private MockSwitch              _ballPathShooterSensor;

    private MockMotor               _hangerHangerMotor;
    private MockSolenoid            _hangerReleaseSolenoid;
    private MockSolenoid            _hangerRatchetSolenoid;
    private MockPositionSensor      _hangerHangerPositionSensor;

    private MockMotor               _pickupPrimaryMotor;
    private MockMotor               _pickupLeftMotor;
    private MockMotor               _pickupRightMotor;
    private MockSolenoid            _pickupDeploySolenoid;
    private MockSwitch              _pickupLeftLightSensor;
    private MockSwitch              _pickupRightLightSensor;

    private MockMotor               _shooterShooterMotor;
    private MockMotor               _shooterHoodMotor;
    private MockPositionSensor      _shooterHoodSensor;
    private PIDControl              _shooterHoodPID;

    private MockNetworkTableDouble  _visionXPosition;
    private MockNetworkTableDouble  _visionYPosition;
    private MockNetworkTableBoolean _visionTargetFound;
    private MockNetworkTableDouble  _visionLEDMode;
    private PIDControl              _visionRotatePID;

    private MockMotor               _spinnerSpinnerMotor;
    private MockPositionSensor      _spinnerPositionSensor;
    private PIDControl              _spinnerSpinnerPID;

    private MockShuffleboardTab     _dashboardTab;
    private MockShuffleboardTab     _settingsTab;

    public MockRobotMap()
    {
        _driveJoy                   = new MockJoystick(12);
        _coDriveJoy                 = new MockJoystick(11);

        _driveGyro                  = new MockPositionSensor();        
        _driveDrivePID              = new PIDControl();
        _driveRotatePID             = new PIDControl();
        _driveFLDriveMotor          = new MockMotor();
        _driveFLRotateMotor         = new MockMotor();
        _driveFLPositionSensor      = new MockPositionSensor();
        _driveFLPIDControl          = new PIDControl();
        _driveFRDriveMotor          = new MockMotor();
        _driveFRRotateMotor         = new MockMotor();
        _driveFRPositionSensor      = new MockPositionSensor();
        _driveFRPIDControl          = new PIDControl();
        _driveBLDriveMotor          = new MockMotor();
        _driveBLRotateMotor         = new MockMotor();
        _driveBLPositionSensor      = new MockPositionSensor();
        _driveBLPIDControl          = new PIDControl();
        _driveBRDriveMotor          = new MockMotor();
        _driveBRRotateMotor         = new MockMotor();
        _driveBRPositionSensor      = new MockPositionSensor();
        _driveBRPIDControl          = new PIDControl();

        _ballPathTrackMotor         = new MockMotor();
        _ballPathUpperTrackSolenoid = new MockSolenoid();
        _ballPathPosition1Sensor    = new MockSwitch();
        _ballPathPosition2Sensor    = new MockSwitch();
        _ballPathShooterSensor      = new MockSwitch();
        
        _hangerHangerMotor          = new MockMotor();
        _hangerReleaseSolenoid      = new MockSolenoid();
        _hangerRatchetSolenoid      = new MockSolenoid();
        _hangerHangerPositionSensor = new MockPositionSensor();
        
        _pickupPrimaryMotor         = new MockMotor();
        _pickupLeftMotor            = new MockMotor();
        _pickupRightMotor           = new MockMotor();
        _pickupDeploySolenoid       = new MockSolenoid();
        _pickupLeftLightSensor      = new MockSwitch();
        _pickupRightLightSensor     = new MockSwitch();
        
        _shooterShooterMotor        = new MockMotor();
        _shooterHoodMotor           = new MockMotor();
        _shooterHoodSensor          = new MockPositionSensor();
        _shooterHoodPID             = new PIDControl();
        
        _visionXPosition            = new MockNetworkTableDouble();
        _visionYPosition            = new MockNetworkTableDouble();
        _visionTargetFound          = new MockNetworkTableBoolean();
        _visionLEDMode              = new MockNetworkTableDouble();
        _visionRotatePID            = new PIDControl();
        
        _spinnerSpinnerMotor        = new MockMotor();
        _spinnerPositionSensor      = new MockPositionSensor();
        _spinnerSpinnerPID          = new PIDControl();

        _dashboardTab               = new MockShuffleboardTab();
        _settingsTab                = new MockShuffleboardTab();
    }

    @Override
    public Joystick getDriveJoy()
    {
        return _driveJoy;
    }

    @Override
    public Joystick getCoDriveJoy()
    {
        return _coDriveJoy;
    }

    @Override
    public PositionSensor getDriveGyro() 
    {
        return _driveGyro;
    }

    @Override
    public PIDControl getDriveDrivePID() 
    {
        return _driveDrivePID;
    }

    @Override
    public PIDControl getDriveRotatePID() 
    {
        return _driveRotatePID;
    }

    @Override
    public Motor getDriveFLModuleDriveMotor()
    {
        return _driveFLDriveMotor;
    }

    @Override
    public Motor getDriveFLModuleRotateMotor()
    {
        return _driveFLRotateMotor;
    }

    @Override
    public PositionSensor getDriveFLModulePositionSensor()
    {
        return _driveFLPositionSensor;
    }

    @Override
    public PIDControl getDriveFLModulePIDControl()
    {
        return _driveFLPIDControl;
    }

    @Override
    public Motor getDriveFRModuleDriveMotor()
    {
        return _driveFRDriveMotor;
    }

    @Override
    public Motor getDriveFRModuleRotateMotor()
    {
        return _driveFRRotateMotor;
    }

    @Override
    public PositionSensor getDriveFRModulePositionSensor()
    {
        return _driveFRPositionSensor;
    }

    @Override
    public PIDControl getDriveFRModulePIDControl()
    {
        return _driveFRPIDControl;
    }

    @Override
    public Motor getDriveBLModuleDriveMotor()
    {
        return _driveBLDriveMotor;
    }

    @Override
    public Motor getDriveBLModuleRotateMotor()
    {
        return _driveBLRotateMotor;
    }

    @Override
    public PositionSensor getDriveBLModulePositionSensor()
    {
        return _driveBLPositionSensor;
    }

    @Override
    public PIDControl getDriveBLModulePIDControl()
    {
        return _driveBLPIDControl;
    }

    @Override
    public Motor getDriveBRModuleDriveMotor()
    {
        return _driveBRDriveMotor;
    }

    @Override
    public Motor getDriveBRModuleRotateMotor()
    {
        return _driveBRRotateMotor;
    }

    @Override
    public PositionSensor getDriveBRModulePositionSensor()
    {
        return _driveBRPositionSensor;
    }

    @Override
    public PIDControl getDriveBRModulePIDControl()
    {
        return _driveBRPIDControl;
    }

    @Override
    public Motor getBallPathTrackMotor() 
    {
        return _ballPathTrackMotor;
    }

    @Override
    public Solenoid getBallPathUpperTrackSolenoid() 
    {
        return _ballPathUpperTrackSolenoid;
    }

    @Override
    public Switch getBallPathPosition1Sensor() 
    {
        return _ballPathPosition1Sensor;
    }

    @Override
    public Switch getBallPathPosition2Sensor() 
    {
        return _ballPathPosition2Sensor;
    }

    @Override
    public Switch getBallPathShooterSensor() 
    {
        return _ballPathShooterSensor;
    }

    @Override
    public Motor getHangerHangerMotor() 
    {
        return _hangerHangerMotor;
    }

    @Override
    public Solenoid getHangerReleaseSolenoid() 
    {
        return _hangerReleaseSolenoid;
    }

    @Override
    public Solenoid getHangerRatchetSolenoid() 
    {
        return _hangerRatchetSolenoid;
    }

    @Override
    public PositionSensor getHangerHangerPositionSensor() 
    {
        return _hangerHangerPositionSensor;
    }

    @Override
    public Motor getPickupPrimaryMotor() 
    {
        return _pickupPrimaryMotor;
    }

    @Override
    public Motor getPickupLeftMotor() 
    {
        return _pickupLeftMotor;
    }

    @Override
    public Motor getPickupRightMotor() 
    {
        return _pickupRightMotor;
    }

    @Override
    public Solenoid getPickupDeploySolenoid() 
    {
        return _pickupDeploySolenoid;
    }

    @Override
    public Switch getPickupLeftLightSensor() 
    {
        return _pickupLeftLightSensor;
    }

    @Override
    public Switch getPickupRightLightSensor() 
    {
        return _pickupRightLightSensor;
    }

    @Override
    public Motor getShooterShooterMotor() 
    {
        return _shooterShooterMotor;
    }

    @Override
    public Motor getShooterHoodMotor() 
    {
        return _shooterHoodMotor;
    }

    @Override
    public PositionSensor getShooterHoodSensor() 
    {
        return _shooterHoodSensor;
    }

    @Override
    public PIDControl getShooterHoodPID() 
    {
        return _shooterHoodPID;
    }

    @Override
    public NetworkTableDouble getVisionXPosition() 
    {
        return _visionXPosition;
    }

    @Override
    public NetworkTableDouble getVisionYPosition() 
    {
        return _visionYPosition;
    }

    @Override
    public NetworkTableBoolean getVisionTargetFound() 
    {
        return _visionTargetFound;
    }

    @Override
    public NetworkTableDouble getVisionLEDMode() 
    {
        return _visionLEDMode;
    }

    @Override
    public PIDControl getVisionRotatePID() 
    {
        return _visionRotatePID;
    }

    @Override
    public Motor getSpinnerSpinnerMotor() 
    {
        return _spinnerSpinnerMotor;
    }

    @Override
    public PositionSensor getSpinnerPositionSensor() 
    {
        return _spinnerPositionSensor;
    }

    @Override
    public PIDControl getSpinnerSpinnerPID() 
    {
        return _spinnerSpinnerPID;
    }

    @Override
    public ShuffleboardTab getDashboardTab()
    {
        return _dashboardTab;
    }

    @Override
    public ShuffleboardTab getSettingsTab()
    {
        return _settingsTab;
    }
}
