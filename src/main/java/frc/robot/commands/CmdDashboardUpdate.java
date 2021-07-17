package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.Vector;

public class CmdDashboardUpdate extends SwartdogCommand 
{
    private Dashboard _dashboardSubsystem;
    private Drive     _driveSubsystem;
    private BallPath  _ballPathSubsystem;
    private Hanger    _hangerSubsystem;
    private Pickup    _pickupSubsystem;
    private Shooter   _shooterSubsystem;

    public CmdDashboardUpdate(Dashboard dashboardSubsystem,
                              Drive     driveSubsystem,
                              BallPath  ballPathSubsystem,
                              Hanger    hangerSubsystem,
                              Pickup    pickupSubsystem,
                              Shooter   shooterSubsystem) 
    {
        _dashboardSubsystem = dashboardSubsystem;
        _driveSubsystem     = driveSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;
        _hangerSubsystem    = hangerSubsystem;
        _pickupSubsystem    = pickupSubsystem;
        _shooterSubsystem   = shooterSubsystem;
    }

    @Override
    public void execute() 
    {
        // _dashboardSubsystem.setFrontLeftModuleRotation(Vector.normalizeAngle(_driveSubsystem.getSwerveModule(Constants.FL_INDEX).getPosition()));
        // _dashboardSubsystem.setFrontLeftModuleDistance(_driveSubsystem.getSwerveModule(Constants.FL_INDEX).getDriveMotor().getPositionSensor().get());
        // _dashboardSubsystem.setFrontRightModuleRotation(Vector.normalizeAngle(_driveSubsystem.getSwerveModule(Constants.FR_INDEX).getPosition()));
        // _dashboardSubsystem.setFrontRightModuleDistance(_driveSubsystem.getSwerveModule(Constants.FR_INDEX).getDriveMotor().getPositionSensor().get());
        // _dashboardSubsystem.setBackLeftModuleRotation(Vector.normalizeAngle(_driveSubsystem.getSwerveModule(Constants.BL_INDEX).getPosition()));
        // _dashboardSubsystem.setBackLeftModuleDistance(_driveSubsystem.getSwerveModule(Constants.BL_INDEX).getDriveMotor().getPositionSensor().get());
        // _dashboardSubsystem.setBackRightModuleRotation(Vector.normalizeAngle(_driveSubsystem.getSwerveModule(Constants.BR_INDEX).getPosition()));
        // _dashboardSubsystem.setBackRightModuleDistance(_driveSubsystem.getSwerveModule(Constants.BR_INDEX).getDriveMotor().getPositionSensor().get());
        _dashboardSubsystem.setRobotRotation(_driveSubsystem.getHeading());

        _dashboardSubsystem.setPickupDeployed(_pickupSubsystem.isPickupDeployed());
        _dashboardSubsystem.setPickupActive(Math.abs(_pickupSubsystem.getPrimaryMotor()) > Constants.MOTOR_MOTION_THRESHOLD);

        _dashboardSubsystem.setBallCount(_ballPathSubsystem.getBallCount());
        _dashboardSubsystem.setBallPathRaised(_ballPathSubsystem.isUpperTrackRaised());
        _dashboardSubsystem.setBallPathJammed(_ballPathSubsystem.isJammed());
        _dashboardSubsystem.setBallPathActive(Math.abs(_ballPathSubsystem.getTrackMotor()) > Constants.MOTOR_MOTION_THRESHOLD);

        _dashboardSubsystem.setHangerReleased(_hangerSubsystem.isHangerReleased());
        _dashboardSubsystem.setHangerLatched(_hangerSubsystem.isHangerLatched());
        _dashboardSubsystem.setHangerPosition(_hangerSubsystem.getPosition());

        _dashboardSubsystem.setShooterHoodPosition(_shooterSubsystem.getHoodPosition());
        _dashboardSubsystem.setShooterHoodTarget(_shooterSubsystem.getHoodSetpoint());
        _dashboardSubsystem.setShooterRPM(_shooterSubsystem.getShooterMotor());
        _dashboardSubsystem.setShooterOn(_shooterSubsystem.isShooterOn());
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
