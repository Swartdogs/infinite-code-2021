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
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.VelocitySensor;
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
        DoubleMotor rightAndPrimaryPickupMotor = new DoubleMotor(new TalonSRX(15));
        DoubleMotor leftPickupAndControlPanel  = new DoubleMotor(new VictorSPX(12));

        Motor          driveFLDrive    = Hardware.Actuators.falcon(1);
        Motor          driveFLRotate   = Hardware.Actuators.neo(2);
        PositionSensor driveFLPosition = Hardware.Sensors.potentiometer(0, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        PIDControl     driveFLPID      = new PIDControl();

        Motor          driveFRDrive    = Hardware.Actuators.falcon(5);
        Motor          driveFRRotate   = Hardware.Actuators.neo(6);
        PositionSensor driveFRPosition = Hardware.Sensors.potentiometer(3, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        PIDControl     driveFRPID      = new PIDControl();

        Motor          driveBLDrive    = Hardware.Actuators.falcon(3);
        Motor          driveBLRotate   = Hardware.Actuators.neo(4);
        PositionSensor driveBLPosition = Hardware.Sensors.potentiometer(1, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        PIDControl     driveBLPID      = new PIDControl();

        Motor          driveBRDrive    = Hardware.Actuators.falcon(7);
        Motor          driveBRRotate   = Hardware.Actuators.neo(8);
        PositionSensor driveBRPosition = Hardware.Sensors.potentiometer(2, Constants.SWERVE_MODULE_SCALE, Constants.SWERVE_MODULE_OFFSET);
        PIDControl     driveBRPID      = new PIDControl();

        driveBLDrive                   = Motor.invert(driveBLDrive);
        driveBRDrive                   = Motor.invert(driveBRDrive);

        _hardware                   = new Hardware();

        _driveJoy                   = Hardware.Controls.joystick(0);
        _coDriveJoy                 = Hardware.Controls.joystick(1);
        _buttonBox                  = Hardware.Controls.joystick(2);

        _driveGyro                  = Hardware.Sensors.imu();
        _driveDrivePID              = null;
        _driveRotatePID             = null;
        _driveFLModule              = new SwerveModule(driveFLDrive, driveFLRotate, driveFLPosition, driveFLPID, Constants.FL_MODULE_OFFSET, Constants.FL_MODULE_X, Constants.FL_MODULE_Y);
        _driveFRModule              = new SwerveModule(driveFRDrive, driveFRRotate, driveFRPosition, driveFRPID, Constants.FR_MODULE_OFFSET, Constants.FR_MODULE_X, Constants.FR_MODULE_Y);
        _driveBLModule              = new SwerveModule(driveBLDrive, driveBLRotate, driveBLPosition, driveBLPID, Constants.BL_MODULE_OFFSET, Constants.BL_MODULE_X, Constants.BL_MODULE_Y);
        _driveBRModule              = new SwerveModule(driveBRDrive, driveBRRotate, driveBRPosition, driveBRPID, Constants.BR_MODULE_OFFSET, Constants.BR_MODULE_X, Constants.BR_MODULE_Y);

        _ballPathTrackMotor         = Motor.invert(Hardware.Actuators.victorSPX(13));
        _ballPathUpperTrackSolenoid = Hardware.Actuators.solenoid(7);
        _ballPathPosition1Sensor    = Hardware.Sensors.lightSensor(0);
        _ballPathPosition2Sensor    = Hardware.Sensors.lightSensor(1);
        _ballPathShooterSensor      = Hardware.Sensors.lightSensor(2);

        _hangerHangerMotor          = null;
        _hangerReleaseSolenoid      = null;
        _hangerRatchetSolenoid      = null;
        _hangerHangerPositionSensor = null;

        _pickupPrimaryMotor         = Motor.invert(rightAndPrimaryPickupMotor.getMotor1());
        _pickupLeftMotor            = leftPickupAndControlPanel.getMotor1();
        _pickupRightMotor           = Motor.invert(rightAndPrimaryPickupMotor.getMotor2());
        _pickupDeploySolenoid       = Solenoid.invert(Hardware.Actuators.solenoid(4));
        _pickupLeftLightSensor      = null;
        _pickupRightLightSensor     = null;

        _shooterShooterMotor        = Motor.compose(Hardware.Actuators.falconFlywheel(9, 6300), Hardware.Actuators.falconFlywheel(10, 6300));
        _shooterHoodMotor           = Hardware.Actuators.victorSPX(14);
        _shooterHoodSensor          = Hardware.Sensors.analogInput(5);
        _shooterHoodPID             = new PIDControl();

        _visionXPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "tx");
        _visionYPosition            = Hardware.NetworkTable.networkTableDouble("limelight", "ty");
        _visionTargetFound          = Hardware.NetworkTable.networkTableBoolean("limelight", "ta");
        _visionLEDMode              = Hardware.NetworkTable.networkTableDouble("limelight", "ledMode");
        _visionRotatePID            = new PIDControl();

        _spinnerSpinnerMotor        = leftPickupAndControlPanel.getMotor2();
        _spinnerPositionSensor      = _spinnerSpinnerMotor.getPositionSensor();
        _spinnerSpinnerPID          = new PIDControl();

        _driveJoy.setXDeadband(0.05);
        _driveJoy.setYDeadband(0.05);
        _driveJoy.setZDeadband(0.10);

        PIDControl[] rotatePIDs = new PIDControl[] { driveFLPID, driveFRPID, driveBLPID, driveBRPID };

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

            // _hangerHangerMotor,
            // _hangerReleaseSolenoid,
            // _hangerRatchetSolenoid,
            // _hangerHangerPositionSensor,

            _pickupPrimaryMotor,
            _pickupLeftMotor,
            _pickupRightMotor,
            _pickupDeploySolenoid,
            // _pickupLeftLightSensor,
            // _pickupRightLightSensor,

            _shooterShooterMotor,
            _shooterHoodMotor,
            _shooterHoodSensor,

            _visionXPosition,
            _visionYPosition,
            _visionTargetFound,
            _visionLEDMode,

            _spinnerSpinnerMotor,
            // _spinnerPositionSensor

            rightAndPrimaryPickupMotor,
            leftPickupAndControlPanel
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
