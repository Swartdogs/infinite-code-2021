package frc.robot.groups;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.SwartdogSequentialCommandGroup;
import frc.robot.commands.CmdDriveDistance;
import frc.robot.commands.CmdDriveRotate;
import frc.robot.commands.CmdPickupDeploy;
import frc.robot.commands.CmdPickupStow;
import frc.robot.commands.CmdShooterFire;
import frc.robot.commands.CmdShooterStart;
import frc.robot.commands.CmdWait;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.Preset;
import frc.robot.subsystems.drive.Drive;

public class GrpAuto6BallFriendlyTrench extends SwartdogSequentialCommandGroup 
{
    public GrpAuto6BallFriendlyTrench(Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Drive driveSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem) 
    {
        super
        (
            SwartdogCommand.run(() -> driveSubsystem.setGyro(-20)),
            SwartdogCommand.run(() -> shooterSubsystem.setPreset(Preset.Far)),
            new CmdShooterStart(ballPathSubsystem, driveSubsystem, pickupSubsystem, shooterSubsystem),
            new CmdWait(1.0),
            new CmdShooterFire(dashboardSubsystem, driveSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
            new CmdPickupDeploy(dashboardSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
            new CmdDriveDistance(driveSubsystem, 76, 0, 0.5, 0, true, true),
            new CmdDriveDistance(driveSubsystem, 170, 0, 0.2, 0, false, true),
            new CmdPickupStow(ballPathSubsystem, pickupSubsystem),
            SwartdogCommand.run(() -> shooterSubsystem.setPreset(Preset.Far)),
            new CmdDriveDistance(driveSubsystem, 45, 0, 0.5, 0, false, true),
            new CmdDriveRotate(driveSubsystem, -17, 0.7, true),
            new CmdShooterStart(ballPathSubsystem, driveSubsystem, pickupSubsystem, shooterSubsystem),
            new CmdWait(1.0),
            new CmdShooterFire(dashboardSubsystem, driveSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
            SwartdogCommand.run(() -> driveSubsystem.setGyro(driveSubsystem.getHeading() + 180))
        );
    }
}
