package frc.robot;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;
import frc.robot.abstraction.Hardware;
import frc.robot.abstraction.Joystick;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;

public class RobotHardware implements RobotMap
{
    private  Hardware               _hardware;

    private  Joystick               _driveJoy;
    private  Joystick               _coDriveJoy;
    private  Joystick               _buttonBox;

    private  Motor                  _driveLeftMotor;
    private  Motor                  _driveRightMotor;
    private  PositionSensor         _driveLeftEncoder;
    private  PositionSensor         _driveRightEncoder;
    private  PositionSensor         _driveGyro;
    private  PIDControl             _driveDrivePID;
    private  PIDControl             _driveRotatePID;

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
        _hardware                   = new Hardware();

        _driveJoy                   = Hardware.Controls.joystick(0);
        _coDriveJoy                 = Hardware.Controls.joystick(1);
        _buttonBox                  = Hardware.Controls.joystick(2);

        _driveLeftMotor             = Motor.compose(Hardware.Actuators.neo(2), Hardware.Actuators.neo(1));
        _driveRightMotor            = Motor.compose(Hardware.Actuators.neo(4), Hardware.Actuators.neo(3));
        _driveLeftEncoder           = Hardware.Sensors.dutyCycleEncoder(0);
        _driveRightEncoder          = Hardware.Sensors.dutyCycleEncoder(1);
        _driveGyro                  = Hardware.Sensors.gyro();
        _driveDrivePID              = new PIDControl();
        _driveRotatePID             = new PIDControl();

        _ballPathTrackMotor         = Motor.compose(Motor.invert(Hardware.Actuators.neo(5)), Hardware.Actuators.neo(6));
        _ballPathUpperTrackSolenoid = Hardware.Actuators.solenoid(1);
        _ballPathPosition1Sensor    = Hardware.Sensors.lightSensor(4);
        _ballPathPosition2Sensor    = Hardware.Sensors.lightSensor(2);
        _ballPathShooterSensor      = Hardware.Sensors.lightSensor(3);

        _hangerHangerMotor          = Hardware.Actuators.victorSPX(11);
        _hangerReleaseSolenoid      = Solenoid.invert(Hardware.Actuators.solenoid((3)));
        _hangerRatchetSolenoid      = Hardware.Actuators.solenoid(2);
        _hangerHangerPositionSensor = Hardware.Sensors.analogInput(0);

        _pickupPrimaryMotor         = Hardware.Actuators.neo(10);
        _pickupLeftMotor            = Hardware.Actuators.victorSP(8);
        _pickupRightMotor           = Hardware.Actuators.victorSP(9);
        _pickupDeploySolenoid       = Solenoid.invert(Hardware.Actuators.solenoid((0)));
        _pickupLeftLightSensor      = Hardware.Sensors.lightSensor(6);
        _pickupRightLightSensor     = Hardware.Sensors.lightSensor(5);

        _shooterShooterMotor        = Motor.compose(Hardware.Actuators.neoFlywheel(7, 5500), Hardware.Actuators.neoFlywheel(8, 5500));
        _shooterHoodMotor           = Hardware.Actuators.victorSPX(9);
        _shooterHoodSensor          = Hardware.Sensors.analogInput(1);
        _shooterHoodPID             = new PIDControl();

        _visionXPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "tx");
        _visionYPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "ty");
        _visionTargetFound          = Hardware.NetworkTable.networkTableBoolean("limelight", "ta");
        _visionLEDMode              = Hardware.NetworkTable.networkTableDouble("limelight", "ledMode");
        _visionRotatePID            = new PIDControl();

        _spinnerSpinnerMotor        = Hardware.Actuators.neo(12);
        _spinnerPositionSensor      = _spinnerSpinnerMotor.getPositionSensor();
        _spinnerSpinnerPID          = new PIDControl();

        _driveDrivePID.setCoefficient(Coefficient.P, 0,  0.0275, 0);
        _driveDrivePID.setCoefficient(Coefficient.I, 0,  0,      0);
        _driveDrivePID.setCoefficient(Coefficient.D, 0,  0.225,  0);
        _driveDrivePID.setInputRange(-500, 500);
        _driveDrivePID.setOutputRamp(0.05, 0.05);
        _driveDrivePID.setSetpointDeadband(0.75);

        _driveRotatePID.setCoefficient(Coefficient.P, 0, 0.01, 0);
        _driveRotatePID.setCoefficient(Coefficient.I, 8, 0,    0.005);
        _driveRotatePID.setCoefficient(Coefficient.D, 0, 0.04, 0);
        _driveRotatePID.setInputRange(-360, 360);
        _driveRotatePID.setOutputRamp(0.1, 0.1);
        _driveRotatePID.setSetpointDeadband(2.0);

        _hangerHangerPositionSensor.setScalingFunction(pos -> -pos);

        _shooterHoodPID.setCoefficient(Coefficient.P, 0,   0.005, 0);
        _shooterHoodPID.setCoefficient(Coefficient.I, 0,   0,     0);
        _shooterHoodPID.setCoefficient(Coefficient.D, 400, 0,     0.001);
        _shooterHoodPID.setInputRange(600, 3800);
        _shooterHoodPID.setOutputRange(-1, 1);
        _shooterHoodPID.setSetpointDeadband(50);

        _visionRotatePID.setCoefficient(Coefficient.P, 0, 0.01, 0);
        _visionRotatePID.setCoefficient(Coefficient.I, 8, 0,    0.005);
        _visionRotatePID.setCoefficient(Coefficient.D, 0, 0.04, 0);
        _visionRotatePID.setInputRange(-360, 360);
        _visionRotatePID.setOutputRamp(0.1, 0.1);
        _visionRotatePID.setSetpointDeadband(1.0);

        _spinnerSpinnerPID.setCoefficient(Coefficient.P, 0, 0.02, 0);
        _spinnerSpinnerPID.setCoefficient(Coefficient.I, 0, 0,    0);
        _spinnerSpinnerPID.setCoefficient(Coefficient.D, 0, 0,    0);
        _spinnerSpinnerPID.setInputRange(-1000, 1000);
        _spinnerSpinnerPID.setOutputRange(-0.8, 0.8);
        _spinnerSpinnerPID.setSetpointDeadband(10);

        _hardware.addHardware
        (
            _driveJoy,
            _coDriveJoy,
            _buttonBox,

            _driveLeftMotor,
            _driveRightMotor,
            _driveLeftEncoder,
            _driveRightEncoder,
            _driveGyro,

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
    public Motor getDriveLeftMotor()
    {
        return _driveLeftMotor;
    }

    @Override
    public Motor getDriveRightMotor()
    {
        return _driveRightMotor;
    }

    @Override
    public PositionSensor getDriveLeftEncoder()
    {
        return _driveLeftEncoder;
    }

    @Override
    public PositionSensor getDriveRightEncoder()
    {
        return _driveRightEncoder;
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
