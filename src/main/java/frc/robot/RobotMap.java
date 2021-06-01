package frc.robot;

import PIDControl.PIDControl;
import frc.robot.abstraction.Joystick;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.NetworkTableBoolean;
import frc.robot.abstraction.NetworkTableDouble;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.Solenoid;
import frc.robot.abstraction.Switch;

public interface RobotMap
{
    public Joystick             getDriveJoy();
    public Joystick             getCoDriveJoy();
    public Joystick             getButtonBox();

    public Motor                getDriveLeftMotor();
    public Motor                getDriveRightMotor();
    public PositionSensor       getDriveLeftEncoder();
    public PositionSensor       getDriveRightEncoder();
    public PositionSensor       getDriveGyro();
    public PIDControl           getDriveDrivePID();
    public PIDControl           getDriveRotatePID();

    public Motor                getBallPathTrackMotor();
    public Solenoid             getBallPathUpperTrackSolenoid();
    public Switch               getBallPathPosition1Sensor();
    public Switch               getBallPathPosition2Sensor();
    public Switch               getBallPathShooterSensor();

    public Motor                getHangerHangerMotor();
    public Solenoid             getHangerReleaseSolenoid();
    public Solenoid             getHangerRatchetSolenoid();
    public PositionSensor       getHangerHangerPositionSensor();

    public Motor                getPickupPrimaryMotor();
    public Motor                getPickupLeftMotor();
    public Motor                getPickupRightMotor();
    public Solenoid             getPickupDeploySolenoid();
    public Switch               getPickupLeftLightSensor();
    public Switch               getPickupRightLightSensor();

    public Motor                getShooterShooterMotor();
    public Motor                getShooterHoodMotor();
    public PositionSensor       getShooterHoodSensor();
    public PIDControl           getShooterHoodPID();

    public NetworkTableDouble   getVisionXPosition();
    public NetworkTableDouble   getVisionYPosition();
    public NetworkTableBoolean  getVisionTargetFound();
    public NetworkTableDouble   getVisionLEDMode();
    public PIDControl           getVisionRotatePID();

    public Motor                getSpinnerSpinnerMotor();
    public PositionSensor       getSpinnerPositionSensor();
    public PIDControl           getSpinnerSpinnerPID();
}