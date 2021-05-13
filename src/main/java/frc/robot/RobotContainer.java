package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.ControlPanelSpinner;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;

public class RobotContainer 
{
    private RobotMap _robotMap;

    private Drive                _driveSubsystem;
    private BallPath             _ballPathSubsystem;
    private Hanger               _hangerSubsystem;
    private Pickup               _pickupSubsystem;
    private Shooter              _shooterSubsystem;
    private Vision               _visionSubsystem;
    private ControlPanelSpinner  _spinnerSubsystem;

    public RobotContainer(RobotMap robotMap) 
    {
        _robotMap = robotMap;

        createSubsystems();
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

        _visionSubsystem = new Vision
        (
            _robotMap.getVisionXPosition(),
            _robotMap.getVisionYPosition(),
            _robotMap.getVisionTargetFound(),
            _robotMap.getVisionLEDMode(),
            _robotMap.getVisionRotatePID()
        );

        _spinnerSubsystem = new ControlPanelSpinner
        (
            _robotMap.getSpinnerSpinnerMotor(),
            _robotMap.getSpinnerPositionSensor(),
            _robotMap.getSpinnerSpinnerPID()
        );
    }

    private void configureButtonBindings() 
    {
        
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
