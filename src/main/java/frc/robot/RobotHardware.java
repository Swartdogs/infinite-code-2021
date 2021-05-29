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
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.drive.SwerveModule;

public class RobotHardware implements RobotMap 
{
    private Hardware _hardware;

    private  Joystick             _driveJoy;
    private  Joystick             _coDriveJoy;
    private  Joystick             _buttonBox;
    private  Switch               _hangerReleaseMultiButton;

    private  PositionSensor       _driveGyro;
    private  PIDControl           _driveDrivePID;
    private  PIDControl           _driveRotatePID;
    private  SwerveModule         _driveFLModule;
    private  SwerveModule         _driveFRModule;
    private  SwerveModule         _driveBLModule;
    private  SwerveModule         _driveBRModule;

    private  Motor                _ballPathTrackMotor;
    private  Solenoid             _ballPathUpperTrackSolenoid;
    private  Switch               _ballPathPosition1Sensor;
    private  Switch               _ballPathPosition2Sensor;
    private  Switch               _ballPathShooterSensor;

    private  Motor                _hangerHangerMotor;
    private  Solenoid             _hangerReleaseSolenoid;
    private  Solenoid             _hangerRatchetSolenoid;
    private  PositionSensor       _hangerHangerPositionSensor;

    private  Motor                _pickupPrimaryMotor;
    private  Motor                _pickupLeftMotor;
    private  Motor                _pickupRightMotor;
    private  Solenoid             _pickupDeploySolenoid;
    private  Switch               _pickupLeftLightSensor;
    private  Switch               _pickupRightLightSensor;

    private  Motor                _shooterShooterMotor;
    private  Motor                _shooterHoodMotor;
    private  PositionSensor       _shooterHoodSensor;
    private  PIDControl           _shooterHoodPID;

    private  NetworkTableDouble   _visionXPosition;
    private  NetworkTableDouble   _visionYPosition;
    private  NetworkTableBoolean  _visionTargetFound;
    private  NetworkTableDouble   _visionLEDMode;
    private  PIDControl           _visionRotatePID;

    private  Motor                _spinnerSpinnerMotor;
    private  PositionSensor       _spinnerPositionSensor;
    private  PIDControl           _spinnerSpinnerPID;

    public RobotHardware()
    {
        // Motor           driveFL             = Hardware.Actuators.falcon(0);
        // Motor           rotateFL            = Hardware.Actuators.neo(0);
        // PositionSensor  positionSensorFL    = Hardware.Sensors.potentiometer(0, 0, 0);
        // PIDControl      rotatePIDFL         = new PIDControl();

        // Motor           driveFR             = Hardware.Actuators.falcon(0);
        // Motor           rotateFR            = Hardware.Actuators.neo(0);
        // PositionSensor  positionSensorFR    = Hardware.Sensors.potentiometer(0, 0, 0);
        // PIDControl      rotatePIDFR         = new PIDControl();

        // Motor           driveBL             = Hardware.Actuators.falcon(0);
        // Motor           rotateBL            = Hardware.Actuators.neo(0);
        // PositionSensor  positionSensorBL    = Hardware.Sensors.potentiometer(0, 0, 0);
        // PIDControl      rotatePIDBL         = new PIDControl();

        // Motor           driveBR             = Hardware.Actuators.falcon(0);
        // Motor           rotateBR            = Hardware.Actuators.neo(0);
        // PositionSensor  positionSensorBR    = Hardware.Sensors.potentiometer(0, 0, 0);
        // PIDControl      rotatePIDBR         = new PIDControl();

        _hardware                   = new Hardware();

        _driveJoy                   = Hardware.Controls.joystick(0);
        _coDriveJoy                 = Hardware.Controls.joystick(0);
        _buttonBox                  = Hardware.Controls.joystick(0);
        _hangerReleaseMultiButton   = new Hardware.HardwareSwitch()
        {
            @Override
            protected State getRaw()
            {
                return _driveJoy.getButton(9).get() == State.On && _coDriveJoy.getButton(9).get() == State.On ? State.On : State.Off;
            }
        };

        // _driveGyro                  = Hardware.Sensors.imu();
        // _driveDrivePID              = new PIDControl();
        // _driveRotatePID             = new PIDControl();
        // _driveFLModule              = new SwerveModule(driveFL, rotateFL, positionSensorFL, rotatePIDFL, Constants.FL_MODULE_OFFSET, Constants.FL_MODULE_X, Constants.FL_MODULE_Y);
        // _driveFRModule              = new SwerveModule(driveFR, rotateFR, positionSensorFR, rotatePIDFR, Constants.FR_MODULE_OFFSET, Constants.FR_MODULE_X, Constants.FR_MODULE_Y);
        // _driveBLModule              = new SwerveModule(driveBL, rotateBL, positionSensorBL, rotatePIDBL, Constants.BL_MODULE_OFFSET, Constants.BL_MODULE_X, Constants.BL_MODULE_Y);
        // _driveBRModule              = new SwerveModule(driveBR, rotateBR, positionSensorBR, rotatePIDBR, Constants.BR_MODULE_OFFSET, Constants.BR_MODULE_X, Constants.BR_MODULE_Y);

        _ballPathTrackMotor         = Motor.compose(Motor.invert(Hardware.Actuators.neo(5)), Hardware.Actuators.neo(6));
        _ballPathUpperTrackSolenoid = Hardware.Actuators.solenoid(1);
        _ballPathPosition1Sensor    = Hardware.Sensors.lightSensor(4);
        _ballPathPosition2Sensor    = Hardware.Sensors.lightSensor(2);
        _ballPathShooterSensor      = Hardware.Sensors.lightSensor(3);

        _hangerHangerMotor          = Hardware.Actuators.victorSPX(11);
        _hangerReleaseSolenoid      = Solenoid.invert(Hardware.Actuators.solenoid(3));
        _hangerRatchetSolenoid      = Hardware.Actuators.solenoid(2);
        _hangerHangerPositionSensor = Hardware.Sensors.analogInput(0);

        _hangerHangerPositionSensor.setScalingFunction(pos -> -pos);

        _pickupPrimaryMotor         = Hardware.Actuators.neo(10);
        _pickupLeftMotor            = Hardware.Actuators.victorSP(8);
        _pickupRightMotor           = Hardware.Actuators.victorSP(9);
        _pickupDeploySolenoid       = Solenoid.invert(Hardware.Actuators.solenoid(0));
        _pickupLeftLightSensor      = Hardware.Sensors.lightSensor(6);
        _pickupRightLightSensor     = Hardware.Sensors.lightSensor(5);

        // _shooterShooterMotor        = null;
        // _shooterHoodMotor           = null;
        // _shooterHoodSensor          = Hardware.Sensors.analogInput(0);
        // _shooterHoodPID             = new PIDControl();

        // _visionXPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "tx");
        // _visionYPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "ty");
        // _visionTargetFound          = Hardware.NetworkTable.networkTableBoolean("limelight", "ta");
        // _visionLEDMode              = Hardware.NetworkTable.networkTableDouble("limelight", "ledMode");
        // _visionRotatePID            = new PIDControl();

        _spinnerSpinnerMotor        = Hardware.Actuators.neo(12);
        _spinnerPositionSensor      = _spinnerSpinnerMotor.getPositionSensor();
        _spinnerSpinnerPID          = new PIDControl();
        _spinnerSpinnerPID.setCoefficient(Coefficient.P, 0, 0.02, 0);
        _spinnerSpinnerPID.setCoefficient(Coefficient.I, 0, 0, 0);
        _spinnerSpinnerPID.setCoefficient(Coefficient.D, 0, 0, 0);
        _spinnerSpinnerPID.setInputRange(-1000, 1000);
        _spinnerSpinnerPID.setOutputRange(-0.8, 0.8);
        _spinnerSpinnerPID.setSetpointDeadband(10);

        _hardware.addHardware
        (
            _driveJoy,
            _coDriveJoy,
            _buttonBox,
            _hangerReleaseMultiButton,

            // _driveGyro,

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
            
            // _shooterShooterMotor,
            // _shooterHoodMotor,
            // _shooterHoodSensor,
            
            // _visionXPosition,
            // _visionYPosition,
            // _visionTargetFound,
            // _visionLEDMode,
            
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
    public Switch getHangerReleaseMultiButton() 
    {
        return _hangerReleaseMultiButton;
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
