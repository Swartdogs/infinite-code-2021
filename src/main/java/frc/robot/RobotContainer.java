package frc.robot;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.commands.CmdBallPathDecrementBallCount;
import frc.robot.commands.CmdBallPathIncrementBallCount;
import frc.robot.commands.CmdBallPathLoad;
import frc.robot.commands.CmdBallPathLower;
import frc.robot.commands.CmdBallPathRaise;
import frc.robot.commands.CmdDriveWithJoystick;
import frc.robot.commands.CmdHangerManual;
import frc.robot.commands.CmdHangerRelease;
import frc.robot.commands.CmdPickupDefault;
import frc.robot.commands.CmdPickupDeploy;
import frc.robot.commands.CmdPickupStow;
import frc.robot.commands.CmdShooterFire;
import frc.robot.commands.CmdShooterStart;
import frc.robot.commands.CmdShooterStop;
import frc.robot.commands.CmdSpinnerManual;
import frc.robot.groups.GrpAuto6BallFriendlyTrench;
import frc.robot.groups.GrpAutoShoot3AndMove;
import frc.robot.groups.GrpShootWithVision;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.ControlPanelSpinner;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.SwerveModule;

public class RobotContainer 
{
    private RobotMap            _robotMap;

    private Dashboard           _dashboardSubsystem;
    private Drive               _driveSubsystem;
    private BallPath            _ballPathSubsystem;
    private Hanger              _hangerSubsystem;
    private Pickup              _pickupSubsystem;
    private Shooter             _shooterSubsystem;
    private ControlPanelSpinner _spinnerSubsystem;
    private Vision              _visionSubsystem;

    private Switch              _hangerReleaseMultiButton;
    
    private DoubleSupplier      _drive;
    private DoubleSupplier      _strafe;
    private DoubleSupplier      _rotate;

    public RobotContainer(RobotMap robotMap) 
    {
        _robotMap = robotMap;

        _hangerReleaseMultiButton = new Switch()
        {
            private SwartdogCommand _command;

            @Override
            protected State getRaw() 
            {
                return _robotMap.getDriveJoy().getButton(11).get() == State.On && _robotMap.getCoDriveJoy().getButton(12).get() == State.On ? State.On : State.Off;
            }

            @Override
            public void whenActivated(SwartdogCommand command, boolean interruptible) 
            {
                _command = command;
            }

            @Override
            public void whileActive(SwartdogCommand command, boolean interruptible) 
            {
                return;
            }

            @Override
            public void cancelWhenActivated(SwartdogCommand command) 
            {
                return;
            }

            @Override
            public void cache()
            {
                super.cache();

                if (transitionedTo(State.On))
                {
                    schedule(_command);
                }                
            }
        };

        _drive  = () -> _robotMap.getDriveJoy().getY();
        _strafe = () -> _robotMap.getDriveJoy().getX();
        _rotate = () -> _robotMap.getDriveJoy().getZ();

        createSubsystems();
        configureDefaultCommands();
        configureButtonBindings();
        configureAutonomousModes();

        _visionSubsystem.disableVisionProcessing();
    }

    private void createSubsystems()
    {
        SwerveModule driveFLModule = new SwerveModule(_robotMap.getDriveFLModuleDriveMotor(), _robotMap.getDriveFLModuleRotateMotor(), _robotMap.getDriveFLModulePositionSensor(), _robotMap.getDriveFLModulePIDControl(), Constants.FL_MODULE_X, Constants.FL_MODULE_Y, () -> _dashboardSubsystem.getDriveFLModuleOffset());
        SwerveModule driveFRModule = new SwerveModule(_robotMap.getDriveFRModuleDriveMotor(), _robotMap.getDriveFRModuleRotateMotor(), _robotMap.getDriveFRModulePositionSensor(), _robotMap.getDriveFRModulePIDControl(), Constants.FR_MODULE_X, Constants.FR_MODULE_Y, () -> _dashboardSubsystem.getDriveFRModuleOffset());
        SwerveModule driveBLModule = new SwerveModule(_robotMap.getDriveBLModuleDriveMotor(), _robotMap.getDriveBLModuleRotateMotor(), _robotMap.getDriveBLModulePositionSensor(), _robotMap.getDriveBLModulePIDControl(), Constants.BL_MODULE_X, Constants.BL_MODULE_Y, () -> _dashboardSubsystem.getDriveBLModuleOffset());
        SwerveModule driveBRModule = new SwerveModule(_robotMap.getDriveBRModuleDriveMotor(), _robotMap.getDriveBRModuleRotateMotor(), _robotMap.getDriveBRModulePositionSensor(), _robotMap.getDriveBRModulePIDControl(), Constants.BR_MODULE_X, Constants.BR_MODULE_Y, () -> _dashboardSubsystem.getDriveBRModuleOffset());

        _driveSubsystem = new Drive
        (
            _robotMap.getDriveGyro(),
            _robotMap.getDriveDrivePID(),
            _robotMap.getDriveRotatePID(),
            driveFLModule,
            driveFRModule,
            driveBLModule,
            driveBRModule
        );

        _ballPathSubsystem = new BallPath
        (
            _robotMap.getBallPathTrackMotor(), 
            _robotMap.getBallPathUpperTrackSolenoid(), 
            _robotMap.getBallPathPosition1Sensor(), 
            _robotMap.getBallPathPosition2Sensor(), 
            _robotMap.getBallPathShooterSensor()
        );

        _hangerSubsystem = new Hanger
        (
            _robotMap.getHangerHangerMotor(), 
            _robotMap.getHangerReleaseSolenoid(), 
            _robotMap.getHangerRatchetSolenoid(), 
            _robotMap.getHangerHangerPositionSensor()
        );

        _pickupSubsystem = new Pickup
        (
            _robotMap.getPickupPrimaryMotor(),
            _robotMap.getPickupLeftMotor(), 
            _robotMap.getPickupRightMotor(), 
            _robotMap.getPickupDeploySolenoid(), 
            _robotMap.getPickupLeftLightSensor(), 
            _robotMap.getPickupRightLightSensor()
        );

        _shooterSubsystem = new Shooter
        (
            _robotMap.getShooterShooterMotor(),
            _robotMap.getShooterHoodMotor(),
            _robotMap.getShooterHoodSensor(),
            _robotMap.getShooterHoodPID(),
            calculateHoodAngle,
            calculateShooterRPM
        );

        _spinnerSubsystem = new ControlPanelSpinner
        (
            _robotMap.getSpinnerSpinnerMotor(),
            _robotMap.getSpinnerPositionSensor(),
            _robotMap.getSpinnerSpinnerPID()
        );

        _dashboardSubsystem = new Dashboard
        (
            _driveSubsystem,
            _ballPathSubsystem,
            _hangerSubsystem,
            _pickupSubsystem,
            _shooterSubsystem,
            _robotMap.getDashboardTab(), 
            _robotMap.getSettingsTab()
        );

        _visionSubsystem = new Vision
        (
            _robotMap.getVisionXPosition(), 
            _robotMap.getVisionYPosition(), 
            _robotMap.getVisionTargetFound(), 
            _robotMap.getVisionLEDMode(), 
            _robotMap.getVisionPipeline(),
            _robotMap.getVisionRotatePID()
        );
    }

    private void configureDefaultCommands()
    {
        _driveSubsystem.setDefaultCommand
        (
            new CmdDriveWithJoystick
            (
                _driveSubsystem, 
                _drive, 
                _strafe, 
                _rotate
            )
        );

        _hangerSubsystem.setDefaultCommand
        (
            new CmdHangerManual
            (
                _dashboardSubsystem,
                _hangerSubsystem, 
                () ->
                {
                    double manual = 0;

                    if (_hangerSubsystem.isHangerReleased() &&
                        _robotMap.getCoDriveJoy().getButton(2).get() == State.On)
                    {
                        manual = _robotMap.getCoDriveJoy().getY();
                    }

                    return manual;
                }
            )
        );

        _pickupSubsystem.setDefaultCommand
        (
            new CmdPickupDefault(_dashboardSubsystem, _pickupSubsystem)
        );

        _spinnerSubsystem.setDefaultCommand
        (
            new CmdSpinnerManual
            (
                _spinnerSubsystem, 
                () ->
                {
                    double manual = 0;

                    if (_robotMap.getCoDriveJoy().getButton(2).get() == State.On && !_hangerSubsystem.isHangerReleased())
                    {
                        manual = _robotMap.getCoDriveJoy().getX();
                    }

                    return manual;
                }
            )
        );
    }

    private void configureButtonBindings() 
    {
        _robotMap.getDriveJoy().getButton(5).whenActivated(new CmdBallPathLower(_ballPathSubsystem, _hangerSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(6).whenActivated(new CmdBallPathRaise(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem));

        _robotMap.getDriveJoy().getButton(7).whenActivated(new CmdShooterStart(_ballPathSubsystem, _driveSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(8).whileActive(new CmdShooterFire(_dashboardSubsystem, _driveSubsystem, _ballPathSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(9).whenActivated(new CmdShooterStop(_driveSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(10).whenActivated(new GrpShootWithVision(_ballPathSubsystem, _driveSubsystem, _pickupSubsystem, _shooterSubsystem, _visionSubsystem, _drive, _strafe, _rotate));
        _robotMap.getDriveJoy().getButton(12).whenActivated(SwartdogCommand.run(() -> _driveSubsystem.setGyro(180)));

        _robotMap.getCoDriveJoy().getButton(1).whenActivated(SwartdogCommand.run(() -> _ballPathSubsystem.setJammed(false)));
        _robotMap.getCoDriveJoy().getButton(3).whenActivated(new CmdPickupStow(_ballPathSubsystem, _pickupSubsystem));
        _robotMap.getCoDriveJoy().getButton(4).whenActivated(new CmdBallPathDecrementBallCount(_ballPathSubsystem));
        _robotMap.getCoDriveJoy().getButton(5).whenActivated(new CmdPickupDeploy(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getCoDriveJoy().getButton(6).whenActivated(new CmdBallPathIncrementBallCount(_ballPathSubsystem));
        _robotMap.getCoDriveJoy().getButton(7).whenActivated(SwartdogCommand.run(() -> _shooterSubsystem.setTargetDistance(Constants.SHOOTER_NEAR_DISTANCE)));
        _robotMap.getCoDriveJoy().getButton(9).whenActivated(SwartdogCommand.run(() -> _shooterSubsystem.setTargetDistance(Constants.SHOOTER_FAR_DISTANCE)));

        _robotMap.getCoDriveJoy().getButton(8).whenActivated(SwartdogCommand.run(() -> _visionSubsystem.enableVisionProcessing()));
        _robotMap.getCoDriveJoy().getButton(10).whenActivated(SwartdogCommand.run(() -> _visionSubsystem.disableVisionProcessing()));

        _robotMap.getBallPathPosition1Sensor().whenActivated(new CmdBallPathLoad(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem));

        _hangerReleaseMultiButton.whenActivated(new CmdHangerRelease(_ballPathSubsystem, _hangerSubsystem, _shooterSubsystem));
    }

    private void configureAutonomousModes()
    {
        _robotMap.getDashboardTab().addDefaultAutonomous("None", null);

        _robotMap.getDashboardTab().addAutonomous("6 Ball, Friendly Trench", new GrpAuto6BallFriendlyTrench(_dashboardSubsystem, _ballPathSubsystem, _driveSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDashboardTab().addAutonomous("Shoot 3 and Drive", new GrpAutoShoot3AndMove(_dashboardSubsystem, _ballPathSubsystem, _driveSubsystem, _pickupSubsystem, _shooterSubsystem));
    }

    public Command getAutonomousCommand() 
    {
        return _robotMap.getDashboardTab().getSelectedAutonomous();
    }

    public void periodic()
    {
        _hangerReleaseMultiButton.cache();
    }

    private DoubleUnaryOperator calculateHoodAngle = (targetDistance) ->
    { 
        double target       = Constants.DEFAULT_HOOD_MIN_POSITION;
        double nearPosition = Constants.DEFAULT_HOOD_NEAR_TARGET;
        double farPosition  = Constants.DEFAULT_HOOD_FAR_TARGET;

        if (_dashboardSubsystem != null)
        {
            target       = _dashboardSubsystem.getHoodMinPosition();
            nearPosition = _dashboardSubsystem.getHoodNearPosition();
            farPosition  = _dashboardSubsystem.getHoodFarPosition();
        }

        if (targetDistance == Constants.SHOOTER_NEAR_DISTANCE)
        {
            target = nearPosition;
        }
        else if (targetDistance == Constants.SHOOTER_FAR_DISTANCE)
        {
            target = farPosition;
        }

        return target;
    };

    private DoubleUnaryOperator calculateShooterRPM = (targetDistance) ->
    {
        double target    = 0;
        double nearSpeed = Constants.DEFAULT_SHOOTER_NEAR_SPEED;
        double farSpeed  = Constants.DEFAULT_SHOOTER_FAR_SPEED;

        if (_dashboardSubsystem != null)
        {
            nearSpeed = _dashboardSubsystem.getShooterNearSpeed();
            farSpeed  = _dashboardSubsystem.getShooterFarSpeed();
        }

        if (targetDistance == Constants.SHOOTER_NEAR_DISTANCE)
        {
            target = nearSpeed;
        }

        else if (targetDistance == Constants.SHOOTER_FAR_DISTANCE)
        {
            target = farSpeed;
        }

        return target;
    };
}