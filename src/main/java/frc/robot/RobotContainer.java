package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.abstraction.Switch;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.commands.CmdBallPathLoad;
import frc.robot.commands.CmdBallPathLower;
import frc.robot.commands.CmdBallPathRaise;
import frc.robot.commands.CmdDriveWithJoystick;
import frc.robot.commands.CmdHangerManual;
import frc.robot.commands.CmdHangerRelease;
import frc.robot.commands.CmdPickupDefault;
import frc.robot.commands.CmdPickupDeploy;
import frc.robot.commands.CmdPickupStow;
import frc.robot.commands.CmdSpinnerManual;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.ControlPanelSpinner;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;

public class RobotContainer 
{
    private RobotMap            _robotMap;

    private Drive               _driveSubsystem;
    private BallPath            _ballPathSubsystem;
    private Hanger              _hangerSubsystem;
    private Pickup              _pickupSubsystem;
    private Shooter             _shooterSubsystem;
    private Vision              _visionSubsystem;
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
        _driveSubsystem = new Drive
        (
            _robotMap.getDriveGyro(),
            _robotMap.getDriveDrivePID(),
            _robotMap.getDriveRotatePID(),
            _robotMap.getDriveFLModule(),
            _robotMap.getDriveFRModule(),
            _robotMap.getDriveBLModule(),
            _robotMap.getDriveBRModule()
        );

        _ballPathSubsystem = new BallPath
        (
            _robotMap.getBallPathTrackMotor(), 
            _robotMap.getBallPathUpperTrackSolenoid(), 
            _robotMap.getBallPathPosition1Sensor(), 
            _robotMap.getBallPathPosition2Sensor(), 
            _robotMap.getBallPathShooterSensor()
        );

        // _hangerSubsystem = new Hanger
        // (
        //     _robotMap.getHangerHangerMotor(), 
        //     _robotMap.getHangerReleaseSolenoid(), 
        //     _robotMap.getHangerRatchetSolenoid(), 
        //     _robotMap.getHangerHangerPositionSensor()
        // );

        _pickupSubsystem = new Pickup
        (
            _robotMap.getPickupPrimaryMotor(),
            _robotMap.getPickupLeftMotor(), 
            _robotMap.getPickupRightMotor(), 
            _robotMap.getPickupDeploySolenoid(), 
            _robotMap.getPickupLeftLightSensor(), 
            _robotMap.getPickupRightLightSensor()
        );

        // _shooterSubsystem = new Shooter
        // (
        //     _robotMap.getShooterShooterMotor(),
        //     _robotMap.getShooterHoodMotor(),
        //     _robotMap.getShooterHoodSensor(),
        //     _robotMap.getShooterHoodPID()
        // );

        // _visionSubsystem = new Vision
        // (
        //     _robotMap.getVisionXPosition(),
        //     _robotMap.getVisionYPosition(),
        //     _robotMap.getVisionTargetFound(),
        //     _robotMap.getVisionLEDMode(),
        //     _robotMap.getVisionRotatePID()
        // );

        // _spinnerSubsystem = new ControlPanelSpinner
        // (
        //     _robotMap.getSpinnerSpinnerMotor(),
        //     _robotMap.getSpinnerPositionSensor(),
        //     _robotMap.getSpinnerSpinnerPID()
        // );
    }

    private void configureDefaultCommands()
    {
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

        // _hangerSubsystem.setDefaultCommand
        // (
        //     new CmdHangerManual
        //     (
        //         _hangerSubsystem, 
        //         () ->
        //         {
        //             double manual = 0;

        //             if (_hangerSubsystem.isHangerReleased() &&
        //                 _robotMap.getDriveJoy().getButton(2).get() == State.On)
        //             {
        //                 manual = _robotMap.getDriveJoy().getY();
        //             }

        //             return manual;
        //         }
        //     )
        // );
        
        // _pickupSubsystem.setDefaultCommand
        // (
        //     new CmdPickupDefault
        //     (
        //         _pickupSubsystem
        //     )
        // );

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

        _robotMap.getDriveJoy().getButton(3).whenActivated(new CmdBallPathLower(_ballPathSubsystem, _hangerSubsystem, _pickupSubsystem));
        _robotMap.getDriveJoy().getButton(4).whenActivated(new CmdBallPathRaise(_ballPathSubsystem, _pickupSubsystem));

        _robotMap.getDriveJoy().getButton(5).whenActivated(new CmdPickupDeploy(_ballPathSubsystem, _pickupSubsystem));
        _robotMap.getDriveJoy().getButton(6).whenActivated(new CmdPickupStow(_ballPathSubsystem, _pickupSubsystem));

        _robotMap.getDriveJoy().getButton(2).whenActivated(SwartdogCommand.run(() -> _ballPathSubsystem.setJammed(false)));
        // _robotMap.getCoDriveJoy().getButton(4).whenActivated(SwartdogCommand.run(() -> _ballPathSubsystem.decrementBallCount()));
        // _robotMap.getCoDriveJoy().getButton(5).whenActivated(SwartdogCommand.run(() -> _ballPathSubsystem.incrementBallCount()));
        // _robotMap.getCoDriveJoy().getButton(6).whenActivated(new CmdPickupDeploy(_ballPathSubsystem, _pickupSubsystem));
        // _robotMap.getCoDriveJoy().getButton(7).whenActivated(new CmdPickupStow(_ballPathSubsystem, _pickupSubsystem));

        _robotMap.getBallPathPosition1Sensor().whenActivated(new CmdBallPathLoad(_ballPathSubsystem, _pickupSubsystem));

        // _hangerReleaseMultiButton.whenActivated(new CmdHangerRelease(_ballPathSubsystem, _hangerSubsystem));
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}