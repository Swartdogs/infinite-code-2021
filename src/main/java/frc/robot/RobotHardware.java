package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;
import frc.robot.abstraction.Abstraction;
import frc.robot.abstraction.Hardware;
import frc.robot.abstraction.Joystick;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.ShuffleboardTab;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.VelocitySensor;

public class RobotHardware implements RobotMap
{
    private  Hardware               _hardware;

    private  Joystick               _driveJoy;
    private  Joystick               _coDriveJoy;

    private  PositionSensor         _driveGyro;
    private  PIDControl             _driveDrivePID;
    private  PIDControl             _driveRotatePID;
    private  Motor                  _driveFLDriveMotor;
    private  Motor                  _driveFLRotateMotor;
    private  PositionSensor         _driveFLPositionSensor;
    private  PIDControl             _driveFLPIDControl;
    private  Motor                  _driveFRDriveMotor;
    private  Motor                  _driveFRRotateMotor;
    private  PositionSensor         _driveFRPositionSensor;
    private  PIDControl             _driveFRPIDControl;
    private  Motor                  _driveBLDriveMotor;
    private  Motor                  _driveBLRotateMotor;
    private  PositionSensor         _driveBLPositionSensor;
    private  PIDControl             _driveBLPIDControl;
    private  Motor                  _driveBRDriveMotor;
    private  Motor                  _driveBRRotateMotor;
    private  PositionSensor         _driveBRPositionSensor;
    private  PIDControl             _driveBRPIDControl;

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

    private ShuffleboardTab         _dashboardTab;
    private ShuffleboardTab         _settingsTab;

    public RobotHardware()
    {
        DoubleMotor rightAndPrimaryPickupMotor = new DoubleMotor(new TalonSRX(15));
        DoubleMotor leftPickupAndControlPanel  = new DoubleMotor(new VictorSPX(12));

        _hardware = new Hardware();

        createAndConfigureControlsHardware();
        createAndConfigureDriveSubsystemHardware();
        createAndConfigureBallPathSubsystemHardware();
        createAndConfigureHangerSubsystemHardware();
        createAndConfigurePickupSubsystemHardware(rightAndPrimaryPickupMotor, leftPickupAndControlPanel);
        createAndConfigureShooterSubsystemHardware();
        createAndConfigureVisionSubsystemHardware();
        createAndConfigureSpinnerSubsystemHardware(leftPickupAndControlPanel);
        createAndConfigureDashboardSubsystemHardware();

        _hardware.addHardware
        (
            rightAndPrimaryPickupMotor,
            leftPickupAndControlPanel
        );
    }

    private void createAndConfigureControlsHardware()
    {
        // Create
        _driveJoy   = Hardware.Controls.joystick(0);
        _coDriveJoy = Hardware.Controls.joystick(1);

        // Configure
        _driveJoy.setXDeadband(0.05);
        _driveJoy.setYDeadband(0.05);
        _driveJoy.setZDeadband(0.10);

        _hardware.addHardware
        (
            _driveJoy,
            _coDriveJoy
        );
    }

    private void createAndConfigureDriveSubsystemHardware()
    {
        // Create
        _driveGyro             = Hardware.Sensors.imu();
        _driveDrivePID         = new PIDControl();
        _driveRotatePID        = new PIDControl();
        _driveFLDriveMotor     = Hardware.Actuators.falcon(1);
        _driveFLRotateMotor    = Hardware.Actuators.neo(2);
        _driveFLPositionSensor = Hardware.Sensors.potentiometer(0, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        _driveFLPIDControl     = new PIDControl();
        _driveFRDriveMotor     = Hardware.Actuators.falcon(5);
        _driveFRRotateMotor    = Hardware.Actuators.neo(6);
        _driveFRPositionSensor = Hardware.Sensors.potentiometer(3, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        _driveFRPIDControl     = new PIDControl();
        _driveBLDriveMotor     = Hardware.Actuators.falcon(3);
        _driveBLRotateMotor    = Hardware.Actuators.neo(4);
        _driveBLPositionSensor = Hardware.Sensors.potentiometer(1, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        _driveBLPIDControl     = new PIDControl();
        _driveBRDriveMotor     = Hardware.Actuators.falcon(7);
        _driveBRRotateMotor    = Hardware.Actuators.neo(8);
        _driveBRPositionSensor = Hardware.Sensors.potentiometer(2, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        _driveBRPIDControl     = new PIDControl();

        // Configure
        _driveBLDriveMotor     = Motor.invert(_driveBLDriveMotor);
        _driveBRDriveMotor     = Motor.invert(_driveBRDriveMotor);

        _driveFLDriveMotor.getPositionSensor().setScalingFunction(raw -> raw * Constants.DRIVE_ENCODER_SCALE);
        _driveFRDriveMotor.getPositionSensor().setScalingFunction(raw -> raw * Constants.DRIVE_ENCODER_SCALE);
        _driveBLDriveMotor.getPositionSensor().setScalingFunction(raw -> raw * Constants.DRIVE_ENCODER_SCALE);
        _driveBRDriveMotor.getPositionSensor().setScalingFunction(raw -> raw * Constants.DRIVE_ENCODER_SCALE);

        _driveRotatePID.setCoefficient(Coefficient.P, 0, 0.010, 0);
        _driveRotatePID.setCoefficient(Coefficient.I, 0, 0,    0);
        _driveRotatePID.setCoefficient(Coefficient.D, 0, 0,    0);
        _driveRotatePID.setInputRange(-360, 360);
        _driveRotatePID.setOutputRamp(0.1, 0.05);
        _driveRotatePID.setSetpointDeadband(2.0);

        _driveDrivePID.setCoefficient(Coefficient.P, 0, 0.015, 0);
        _driveDrivePID.setCoefficient(Coefficient.I, 0, 0,     0);
        _driveDrivePID.setCoefficient(Coefficient.D, 0, 0,     0);
        _driveDrivePID.setInputRange(0, 500);
        _driveDrivePID.setOutputRamp(0.1, 0.05);
        _driveDrivePID.setSetpointDeadband(2.0);

        PIDControl[] rotatePIDs = new PIDControl[] { _driveFLPIDControl, _driveFRPIDControl, _driveBLPIDControl, _driveBRPIDControl };

        for (PIDControl rotatePID : rotatePIDs)
        {
            rotatePID.setCoefficient(Coefficient.P, 0, 0.01, 0);
            rotatePID.setCoefficient(Coefficient.I, 0, 0,    0);
            rotatePID.setCoefficient(Coefficient.D, 0, 0,    0);
            rotatePID.setInputRange(0, 360);
            rotatePID.setOutputRange(-1, 1);
            rotatePID.setSetpointDeadband(0.25);
        }

        _hardware.addHardware
        (
            _driveGyro,
            _driveFLDriveMotor,
            _driveFLRotateMotor,
            _driveFLPositionSensor,
            _driveFRDriveMotor,
            _driveFRRotateMotor,
            _driveFRPositionSensor,
            _driveBLDriveMotor,
            _driveBLRotateMotor,
            _driveBLPositionSensor,
            _driveBRDriveMotor,
            _driveBRRotateMotor,
            _driveBRPositionSensor
        );
    }

    private void createAndConfigureBallPathSubsystemHardware()
    {
        // Create
        _ballPathTrackMotor         = Hardware.Actuators.victorSPX(13);
        _ballPathUpperTrackSolenoid = Hardware.Actuators.solenoid(7);
        _ballPathPosition1Sensor    = Hardware.Sensors.lightSensor(0);
        _ballPathPosition2Sensor    = Hardware.Sensors.lightSensor(1);
        _ballPathShooterSensor      = Hardware.Sensors.lightSensor(2);

        // Configure
        _ballPathTrackMotor = Motor.invert(_ballPathTrackMotor);

        _hardware.addHardware
        (
            _ballPathTrackMotor,
            _ballPathUpperTrackSolenoid,
            _ballPathPosition1Sensor,
            _ballPathPosition2Sensor,
            _ballPathShooterSensor
        );
    }

    private void createAndConfigureHangerSubsystemHardware()
    {
        // Create
        _hangerHangerMotor          = Hardware.Actuators.victorSPX(11);
        _hangerReleaseSolenoid      = Hardware.Actuators.solenoid(5);
        _hangerRatchetSolenoid      = Hardware.Actuators.solenoid(6);
        _hangerHangerPositionSensor = Hardware.Sensors.analogInput(7);

        // Configure
        _hangerHangerMotor = Motor.invert(_hangerHangerMotor);
        _hangerReleaseSolenoid = Solenoid.invert(_hangerReleaseSolenoid);
        _hangerHangerPositionSensor.setScalingFunction((position) -> -position);

        _hardware.addHardware
        (
            _hangerHangerMotor,
            _hangerReleaseSolenoid,
            _hangerRatchetSolenoid,
            _hangerHangerPositionSensor
        );
    }

    private void createAndConfigurePickupSubsystemHardware(DoubleMotor rightAndMidPickup, DoubleMotor leftPickupAndControlPanel)
    {
        // Create
        _pickupPrimaryMotor         = rightAndMidPickup.getMotor1();
        _pickupLeftMotor            = leftPickupAndControlPanel.getMotor1();
        _pickupRightMotor           = rightAndMidPickup.getMotor2();
        _pickupDeploySolenoid       = Hardware.Actuators.solenoid(4);
        _pickupLeftLightSensor      = null;
        _pickupRightLightSensor     = null;

        // Configure
        _pickupPrimaryMotor   = Motor.invert(_pickupPrimaryMotor);
        _pickupRightMotor     = Motor.invert(_pickupRightMotor);
        _pickupDeploySolenoid = Solenoid.invert(_pickupDeploySolenoid);

        _hardware.addHardware
        (
            _pickupDeploySolenoid
            // _pickupLeftLightSensor,
            // _pickupRightLightSensor,
        );
    }

    private void createAndConfigureShooterSubsystemHardware()
    {
        // Create
        Motor primaryShooterMotor   = Hardware.Actuators.falconFlywheel(9,  6300);
        Motor secondaryShooterMotor = Hardware.Actuators.falconFlywheel(10, 6300);

        _shooterShooterMotor        = Motor.compose(primaryShooterMotor, secondaryShooterMotor);
        _shooterHoodMotor           = Hardware.Actuators.victorSPX(14);
        _shooterHoodSensor          = Hardware.Sensors.analogInput(5);
        _shooterHoodPID             = new PIDControl();

        // Configure
        _shooterHoodMotor           = Motor.invert(_shooterHoodMotor);

        _shooterHoodPID.setCoefficient(Coefficient.P, 0, 0.005, 0);
        _shooterHoodPID.setCoefficient(Coefficient.I, 0, 0, 0);
        _shooterHoodPID.setCoefficient(Coefficient.D, 400, 0, 0.001);
        _shooterHoodPID.setInputRange(Constants.DEFAULT_HOOD_MAX_POSITION, Constants.DEFAULT_HOOD_MIN_POSITION);
        _shooterHoodPID.setOutputRange(-1, 1);
        _shooterHoodPID.setSetpointDeadband(50);

        _hardware.addHardware
        (
            _shooterShooterMotor,
            _shooterHoodMotor,
            _shooterHoodSensor
        );
    }

    private void createAndConfigureSpinnerSubsystemHardware(DoubleMotor leftPickupAndControlPanel)
    {
        // Create
        _spinnerSpinnerMotor   = leftPickupAndControlPanel.getMotor2();
        _spinnerPositionSensor = _spinnerSpinnerMotor.getPositionSensor();
        _spinnerSpinnerPID     = new PIDControl();

        // Configure

    }

    private void createAndConfigureVisionSubsystemHardware()
    {
        // Create
        _visionXPosition   = Hardware.NetworkTable.networkTableDouble("limelight", "tx");
        _visionYPosition   = Hardware.NetworkTable.networkTableDouble("limelight", "ty");
        _visionTargetFound = Hardware.NetworkTable.networkTableBoolean("limelight", "ta");
        _visionLEDMode     = Hardware.NetworkTable.networkTableDouble("limelight", "ledMode");
        _visionRotatePID   = new PIDControl();

        // Configure
        _hardware.addHardware
        (
            _visionXPosition,
            _visionYPosition,
            _visionTargetFound,
            _visionLEDMode
        );
    }

    private void createAndConfigureDashboardSubsystemHardware()
    {
        // Create
        _dashboardTab = Hardware.NetworkTable.shuffleboardTab("Dashboard");
        _settingsTab  = Hardware.NetworkTable.shuffleboardTab("Settings");

        // Configure

        _hardware.addHardware
        (
            _dashboardTab,
            _settingsTab
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

    private class DoubleMotor implements Abstraction
    {
        private Motor  _motor1;
        private Motor  _motor2;

        private double _speed1;
        private double _speed2;
        private double _finalSpeed;

        private BaseMotorController _motor;

        public DoubleMotor(BaseMotorController motor)
        {
            _speed1     = 0;
            _speed2     = 0;
            _finalSpeed = 0;

            _motor = motor;

            _motor1 = new Motor()
            {
                @Override
                protected double getRaw() 
                {
                    return _finalSpeed;
                }

                @Override
                public PositionSensor getPositionSensor() 
                {
                    return null;
                }

                @Override
                public VelocitySensor getVelocitySensor() 
                {
                    return null;
                }

                @Override
                public void set(double speed) 
                {
                    _speed1 = speed;
                }
            };

            _motor2 = new Motor()
            {
                @Override
                protected double getRaw() 
                {
                    return _finalSpeed;
                }

                @Override
                public PositionSensor getPositionSensor() 
                {
                    return null;
                }

                @Override
                public VelocitySensor getVelocitySensor() 
                {
                    return null;
                }

                @Override
                public void set(double speed) 
                {
                    _speed2 = speed;
                }
            };
        }

        public Motor getMotor1()
        {
            return _motor1;
        }

        public Motor getMotor2()
        {
            return _motor2;
        }

        @Override
        public void cache()
        {
            _finalSpeed = Math.abs(_speed1) > Math.abs(_speed2) ? _speed1 : _speed2;

            _motor.set(ControlMode.PercentOutput, _finalSpeed);

            _motor1.cache();
            _motor2.cache();
        }
    }
}
