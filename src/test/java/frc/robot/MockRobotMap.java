package frc.robot;

import PIDControl.PIDControl;
import frc.robot.abstraction.MockMotor;
import frc.robot.abstraction.MockNetworkTableBoolean;
import frc.robot.abstraction.MockNetworkTableDouble;
import frc.robot.abstraction.MockPositionSensor;
import frc.robot.abstraction.MockSolenoid;
import frc.robot.abstraction.MockSwitch;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;
import frc.robot.subsystems.drive.SwerveModule;

public class MockRobotMap implements RobotMap 
{
    private PositionSensor      _driveGyro;
    private PIDControl          _driveDrivePID;
    private PIDControl          _driveRotatePID;
    private SwerveModule        _driveFLModule;
    private SwerveModule        _driveFRModule;
    private SwerveModule        _driveBLModule;
    private SwerveModule        _driveBRModule;

    private Motor               _ballPathTrackMotor;
    private Solenoid            _ballPathUpperTrackSolenoid;
    private Switch              _ballPathPosition1Sensor;
    private Switch              _ballPathPosition2Sensor;
    private Switch              _ballPathShooterSensor;

    private Motor               _hangerHangerMotor;
    private Solenoid            _hangerReleaseSolenoid;
    private Solenoid            _hangerRatchetSolenoid;
    private PositionSensor      _hangerHangerPositionSensor;

    private Motor               _pickupPrimaryMotor;
    private Motor               _pickupLeftMotor;
    private Motor               _pickupRightMotor;
    private Solenoid            _pickupDeploySolenoid;
    private Switch              _pickupLeftLightSensor;
    private Switch              _pickupRightLightSensor;

    private Motor               _shooterShooterMotor;
    private Motor               _shooterHoodMotor;
    private PositionSensor      _shooterHoodSensor;
    private PIDControl          _shooterHoodPID;

    private NetworkTableDouble  _visionXPosition;
    private NetworkTableDouble  _visionYPosition;
    private NetworkTableBoolean _visionTargetFound;
    private NetworkTableDouble  _visionLEDMode;
    private PIDControl          _visionRotatePID;

    private Motor               _spinnerSpinnerMotor;
    private PositionSensor      _spinnerPositionSensor;
    private PIDControl          _spinnerSpinnerPID;

    public MockRobotMap()
    {
        _driveGyro                  = new MockPositionSensor();        
        _driveDrivePID              = new PIDControl();
        _driveRotatePID             = new PIDControl();
        _driveFLModule              = new SwerveModule
        (
            new MockMotor(), 
            new MockMotor(), 
            new MockPositionSensor(), 
            new PIDControl(), 
            Constants.FL_MODULE_OFFSET, 
            Constants.FL_MODULE_X, 
            Constants.FL_MODULE_Y
        );
        _driveFRModule              = new SwerveModule
        (
            new MockMotor(), 
            new MockMotor(), 
            new MockPositionSensor(), 
            new PIDControl(), 
            Constants.FR_MODULE_OFFSET, 
            Constants.FR_MODULE_X, 
            Constants.FR_MODULE_Y
        );
        _driveBLModule              = new SwerveModule
        (
            new MockMotor(), 
            new MockMotor(), 
            new MockPositionSensor(), 
            new PIDControl(), 
            Constants.BL_MODULE_OFFSET, 
            Constants.BL_MODULE_X, 
            Constants.BL_MODULE_Y
        );
        _driveBRModule              = new SwerveModule
        (
            new MockMotor(), 
            new MockMotor(), 
            new MockPositionSensor(), 
            new PIDControl(), 
            Constants.FL_MODULE_OFFSET, 
            Constants.FL_MODULE_X, 
            Constants.FL_MODULE_Y
        );

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
    public SwerveModule getDriveFLModule() 
    {
        return _driveFLModule;
    }

    @Override
    public SwerveModule getDriveFRModule() 
    {
        return _driveFRModule;
    }

    @Override
    public SwerveModule getDriveBLModule() 
    {
        return _driveBLModule;
    }

    @Override
    public SwerveModule getDriveBRModule() 
    {
        return _driveBRModule;
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
}
