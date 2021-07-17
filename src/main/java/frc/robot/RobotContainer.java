package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.commands.CmdBallPathLoad;
import frc.robot.commands.CmdBallPathLower;
import frc.robot.commands.CmdBallPathRaise;
import frc.robot.commands.CmdDashboardUpdate;
import frc.robot.commands.CmdDriveWithJoystick;
import frc.robot.commands.CmdHangerManual;
import frc.robot.commands.CmdHangerRelease;
import frc.robot.commands.CmdPickupDefault;
import frc.robot.commands.CmdPickupDeploy;
import frc.robot.commands.CmdPickupStow;
import frc.robot.commands.CmdShooterDefault;
import frc.robot.commands.CmdShooterFire;
import frc.robot.commands.CmdShooterStart;
import frc.robot.commands.CmdSpinnerManual;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.ControlPanelSpinner;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
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

    private Switch              _hangerReleaseMultiButton;

    public RobotContainer(RobotMap robotMap) 
    {
        _robotMap = robotMap;

        // _hangerReleaseMultiButton = new Switch()
        // {
        //     @Override
        //     protected State getRaw() 
        //     {
        //         return _robotMap.getDriveJoy().getButton(9).get() == State.On && _robotMap.getCoDriveJoy().getButton(9).get() == State.On ? State.On : State.Off;
        //     }

        //     @Override
        //     public void whenActivated(SwartdogCommand command, boolean interruptible) 
        //     {
        //         if (transitionedTo(State.On))
        //         {
        //             schedule(command);
        //         }                
        //     }

        //     @Override
        //     public void whileActive(SwartdogCommand command, boolean interruptible) 
        //     {
        //         return;
        //     }

        //     @Override
        //     public void cancelWhenActivated(SwartdogCommand command) 
        //     {
        //         return;
        //     }
        // };

        createSubsystems();
        configureDefaultCommands();
        configureButtonBindings();
    }

    private void createSubsystems()
    {
        _dashboardSubsystem = new Dashboard
        (
            _robotMap.getDashboardTab(), 
            _robotMap.getSettingsTab()
        );

        SwerveModule driveFLModule = new SwerveModule(_robotMap.getDriveFLModuleDriveMotor(), _robotMap.getDriveFLModuleRotateMotor(), _robotMap.getDriveFLModulePositionSensor(), _robotMap.getDriveFLModulePIDControl(), Constants.FL_MODULE_X, Constants.FL_MODULE_Y, _dashboardSubsystem::getDriveFLModuleOffset);
        SwerveModule driveFRModule = new SwerveModule(_robotMap.getDriveFRModuleDriveMotor(), _robotMap.getDriveFRModuleRotateMotor(), _robotMap.getDriveFRModulePositionSensor(), _robotMap.getDriveFRModulePIDControl(), Constants.FR_MODULE_X, Constants.FR_MODULE_Y, _dashboardSubsystem::getDriveFRModuleOffset);
        SwerveModule driveBLModule = new SwerveModule(_robotMap.getDriveBLModuleDriveMotor(), _robotMap.getDriveBLModuleRotateMotor(), _robotMap.getDriveBLModulePositionSensor(), _robotMap.getDriveBLModulePIDControl(), Constants.BL_MODULE_X, Constants.BL_MODULE_Y, _dashboardSubsystem::getDriveBLModuleOffset);
        SwerveModule driveBRModule = new SwerveModule(_robotMap.getDriveBRModuleDriveMotor(), _robotMap.getDriveBRModuleRotateMotor(), _robotMap.getDriveBRModulePositionSensor(), _robotMap.getDriveBRModulePIDControl(), Constants.BR_MODULE_X, Constants.BR_MODULE_Y, _dashboardSubsystem::getDriveBRModuleOffset);

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
            _robotMap.getShooterHoodPID()
        );

        _spinnerSubsystem = new ControlPanelSpinner
        (
            _robotMap.getSpinnerSpinnerMotor(),
            _robotMap.getSpinnerPositionSensor(),
            _robotMap.getSpinnerSpinnerPID()
        );
    }

    private void configureDefaultCommands()
    {
        _dashboardSubsystem.setDefaultCommand
        (
            new CmdDashboardUpdate
            (
                _dashboardSubsystem, 
                _driveSubsystem, 
                _ballPathSubsystem, 
                _hangerSubsystem, 
                _pickupSubsystem, 
                _shooterSubsystem
            )
        );

        _driveSubsystem.setDefaultCommand
        (
            new CmdDriveWithJoystick
            (
                _driveSubsystem, 
                () -> _robotMap.getDriveJoy().getY(), 
                () -> _robotMap.getDriveJoy().getX(), 
                () -> _robotMap.getDriveJoy().getZ()
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
                        _robotMap.getDriveJoy().getButton(2).get() == State.On)
                    {
                        manual = _robotMap.getDriveJoy().getY();
                    }

                    return manual;
                }
            )
        );
        
        // _pickupSubsystem.setDefaultCommand
        // (
        //     new CmdPickupDefault
        //     (
        //         _pickupSubsystem
        //     )
        // );

        _shooterSubsystem.setDefaultCommand
        (
            new CmdShooterDefault
            (
                _dashboardSubsystem,
                _shooterSubsystem, 
                _ballPathSubsystem
            )
        );

        // _spinnerSubsystem.setDefaultCommand
        // (
        //     new CmdSpinnerManual
        //     (
        //         _spinnerSubsystem, 
        //         () ->
        //         {
        //             double manual = 0;

        //             if (_robotMap.getDriveJoy().getButton(2).get() == State.On)
        //             {
        //                 manual = _robotMap.getDriveJoy().getX();
        //             }

        //             return manual;
        //         }
        //     )
        // );
    }

    private void configureButtonBindings() 
    {
        _robotMap.getDriveJoy().getButton(1).whenActivated(SwartdogCommand.run(() -> _driveSubsystem.resetGyro()));

        _robotMap.getDriveJoy().getButton(3).whenActivated(new CmdBallPathLower(_ballPathSubsystem, _hangerSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(4).whenActivated(new CmdBallPathRaise(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem));

        _robotMap.getDriveJoy().getButton(5).whenActivated(new CmdPickupDeploy(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(6).whenActivated(new CmdPickupStow(_ballPathSubsystem, _pickupSubsystem));

        // _robotMap.getDriveJoy().getButton(2).whenActivated(SwartdogCommand.run(() -> _ballPathSubsystem.setJammed(false)));
        _robotMap.getDriveJoy().getButton(2).whenActivated(SwartdogCommand.run(() -> _driveSubsystem.resetEncoders()));
        _robotMap.getCoDriveJoy().getButton(4).whenActivated(SwartdogCommand.run(() -> {_ballPathSubsystem.decrementBallCount(); System.out.println("Ball Count: " + _ballPathSubsystem.getBallCount());}));
        _robotMap.getCoDriveJoy().getButton(5).whenActivated(SwartdogCommand.run(() -> {_ballPathSubsystem.incrementBallCount(Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT); System.out.println("Ball Count: " + _ballPathSubsystem.getBallCount());}));
        // _robotMap.getCoDriveJoy().getButton(6).whenActivated(new CmdPickupDeploy(_ballPathSubsystem, _pickupSubsystem));
        // _robotMap.getCoDriveJoy().getButton(7).whenActivated(new CmdPickupStow(_ballPathSubsystem, _pickupSubsystem));

        _robotMap.getDriveJoy().getButton(7).whenActivated(SwartdogCommand.run(() -> _shooterSubsystem.setTargetDistance(_dashboardSubsystem.getShooterNearDistance())));
        _robotMap.getDriveJoy().getButton(8).whenActivated(new CmdShooterStart(_ballPathSubsystem, _pickupSubsystem, _shooterSubsystem));
        _robotMap.getDriveJoy().getButton(9).whenActivated(SwartdogCommand.run(() -> _shooterSubsystem.setTargetDistance(_dashboardSubsystem.getShooterFarDistance())));
        _robotMap.getDriveJoy().getButton(10).whenActivated(SwartdogCommand.run(() -> _shooterSubsystem.stopShooter()));

        _robotMap.getCoDriveJoy().getButton(1).whileActive(new CmdShooterFire(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem, _shooterSubsystem));;

        _robotMap.getBallPathPosition1Sensor().whenActivated(new CmdBallPathLoad(_dashboardSubsystem, _ballPathSubsystem, _pickupSubsystem));

        // _hangerReleaseMultiButton.whenActivated(new CmdHangerRelease(_ballPathSubsystem, _hangerSubsystem));
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}