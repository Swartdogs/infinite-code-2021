package frc.robot;

import PIDControl.PIDControl;
import frc.robot.abstraction.Hardware;
import frc.robot.abstraction.Joystick;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;
import frc.robot.subsystems.drive.SwerveModule;

public class RobotHardware implements RobotMap
{
    private  Hardware               _hardware;

    private  Joystick               _driveJoy;
    private  Joystick               _coDriveJoy;
    private  Joystick               _buttonBox;

    private  PositionSensor         _driveGyro;
    private  PIDControl             _driveDrivePID;
    private  PIDControl             _driveRotatePID;
    private  SwerveModule           _driveFLModule;
    private  SwerveModule           _driveFRModule;
    private  SwerveModule           _driveBLModule;
    private  SwerveModule           _driveBRModule;

    private  Motor                  _ballPathTrackMotor;
    private  Solenoid               _ballPathUpperTrackSolenoid;
    private  Switch                 _ballPathPosition1Sensor;
    private  Switch                 _ballPathPosition2Sensor;
    private  Switch                 _ballPathShooterSensor;

    private  Motor                  _hangerHangerMotor;
    private  Solenoid               _hangerReleaseSolenoid;
    private  Solenoid               _hangerRatchetSolenoid;
    private  PositionSensor         _hangerHangerPositionSensor;

    private  Motor                  _pickupPrimaryMotor;
    private  Motor                  _pickupLeftMotor;
    private  Motor                  _pickupRightMotor;
    private  Solenoid               _pickupDeploySolenoid;
    private  Switch                 _pickupLeftLightSensor;
    private  Switch                 _pickupRightLightSensor;

    private  Motor                  _shooterShooterMotor;
    private  Motor                  _shooterHoodMotor;
    private  PositionSensor         _shooterHoodSensor;
    private  PIDControl             _shooterHoodPID;

    private  NetworkTableDouble     _visionXPosition;
    private  NetworkTableDouble     _visionYPosition;
    private  NetworkTableBoolean    _visionTargetFound;
    private  NetworkTableDouble     _visionLEDMode;
    private  PIDControl             _visionRotatePID;

    private  Motor                  _spinnerSpinnerMotor;
    private  PositionSensor         _spinnerPositionSensor;
    private  PIDControl             _spinnerSpinnerPID;

    public RobotHardware()
    {
        Motor          driveFLDrive    = null;
        Motor          driveFLRotate   = null;
        PositionSensor driveFLPosition = null;
        PIDControl     driveFLPID      = new PIDControl();

        Motor          driveFRDrive    = null;
        Motor          driveFRRotate   = null;
        PositionSensor driveFRPosition = null;
        PIDControl     driveFRPID      = new PIDControl();

        Motor          driveBLDrive    = null;
        Motor          driveBLRotate   = null;
        PositionSensor driveBLPosition = null;
        PIDControl     driveBLPID      = new PIDControl();

        Motor          driveBRDrive    = null;
        Motor          driveBRRotate   = null;
        PositionSensor driveBRPosition = null;
        PIDControl     driveBRPID      = new PIDControl();

        _hardware                   = new Hardware();

        _driveJoy                   = null;
        _coDriveJoy                 = null;
        _buttonBox                  = null;

        _driveGyro                  = null;
        _driveDrivePID              = null;
        _driveRotatePID             = null;
        _driveFLModule              = new SwerveModule(driveFLDrive, driveFLRotate, driveFLPosition, driveFLPID, Constants.FL_MODULE_OFFSET, Constants.FL_MODULE_X, Constants.FL_MODULE_Y);
        _driveFRModule              = new SwerveModule(driveFRDrive, driveFRRotate, driveFRPosition, driveFRPID, Constants.FR_MODULE_OFFSET, Constants.FR_MODULE_X, Constants.FR_MODULE_Y);
        _driveBLModule              = new SwerveModule(driveBLDrive, driveBLRotate, driveBLPosition, driveBLPID, Constants.BL_MODULE_OFFSET, Constants.BL_MODULE_X, Constants.BL_MODULE_Y);
        _driveBRModule              = new SwerveModule(driveBRDrive, driveBRRotate, driveBRPosition, driveBRPID, Constants.BR_MODULE_OFFSET, Constants.BR_MODULE_X, Constants.BR_MODULE_Y);

        _ballPathTrackMotor         = null;
        _ballPathUpperTrackSolenoid = null;
        _ballPathPosition1Sensor    = null;
        _ballPathPosition2Sensor    = null;
        _ballPathShooterSensor      = null;

        _hangerHangerMotor          = null;
        _hangerReleaseSolenoid      = null;
        _hangerRatchetSolenoid      = null;
        _hangerHangerPositionSensor = null;

        _pickupPrimaryMotor         = null;
        _pickupLeftMotor            = null;
        _pickupRightMotor           = null;
        _pickupDeploySolenoid       = null;
        _pickupLeftLightSensor      = null;
        _pickupRightLightSensor     = null;

        _shooterShooterMotor        = null;
        _shooterHoodMotor           = null;
        _shooterHoodSensor          = null;
        _shooterHoodPID             = new PIDControl();

        _visionXPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "tx");
        _visionYPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "ty");
        _visionTargetFound          = Hardware.NetworkTable.networkTableBoolean("limelight", "ta");
        _visionLEDMode              = Hardware.NetworkTable.networkTableDouble("limelight", "ledMode");
        _visionRotatePID            = new PIDControl();

        _spinnerSpinnerMotor        = null;
        _spinnerPositionSensor      = _spinnerSpinnerMotor.getPositionSensor();
        _spinnerSpinnerPID          = new PIDControl();

        _hardware.addHardware
        (
            _driveJoy,
            _coDriveJoy,
            _buttonBox,

            _driveGyro,
            driveFLDrive,
            driveFLRotate,
            driveFLPosition,
            driveFRDrive,
            driveFRRotate,
            driveFRPosition,
            driveBLDrive,
            driveBLRotate,
            driveBLPosition,
            driveBRDrive,
            driveBRRotate,
            driveBRPosition,

            _ballPathTrackMotor,
            _ballPathUpperTrackSolenoid,
            _ballPathPosition1Sensor,
            _ballPathPosition2Sensor,
            _ballPathShooterSensor,

            _hangerHangerMotor,
            _hangerReleaseSolenoid,
            _hangerRatchetSolenoid,
            _hangerHangerPositionSensor,

            _pickupPrimaryMotor,
            _pickupLeftMotor,
            _pickupRightMotor,
            _pickupDeploySolenoid,
            _pickupLeftLightSensor,
            _pickupRightLightSensor,

            _shooterShooterMotor,
            _shooterHoodMotor,
            _shooterHoodSensor,

            _visionXPosition,
            _visionYPosition,
            _visionTargetFound,
            _visionLEDMode,

            _spinnerSpinnerMotor,
            _spinnerPositionSensor
        );
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
    public Joystick getButtonBox()
    {
        return _buttonBox;
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
