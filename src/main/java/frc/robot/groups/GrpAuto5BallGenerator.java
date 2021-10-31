
package frc.robot.groups;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.SwartdogSequentialCommandGroup;
import frc.robot.commands.CmdAutoShootWithVision;
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
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Shooter.Preset;
import frc.robot.subsystems.drive.Drive;

public class GrpAuto5BallGenerator extends SwartdogSequentialCommandGroup {

  public GrpAuto5BallGenerator(Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Drive driveSubsystem,
      Pickup pickupSubsystem, Shooter shooterSubsystem, Vision visionSubsystem) {
    super(
    SwartdogCommand.run(() -> driveSubsystem.setGyro(0)),
    SwartdogCommand.run(() -> shooterSubsystem.setPreset(Preset.Far)),
    new CmdShooterStart(ballPathSubsystem, driveSubsystem, pickupSubsystem, shooterSubsystem),
    new CmdWait(1.0),
    new CmdShooterFire(dashboardSubsystem, driveSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
    new CmdDriveRotate(driveSubsystem, 0, 0.5, true),
    new CmdDriveDistance(driveSubsystem, 78.5, 0, 0.5, 0, true, true),
    new CmdDriveRotate(driveSubsystem, 35, 0.5, true),
    new CmdPickupDeploy(dashboardSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem), 
    new CmdDriveDistance(driveSubsystem, 24, 0, 0.2, 0, true, false),
    new CmdDriveDistance(driveSubsystem, 48, 0, 0.1, 0, false, false),
    new CmdPickupStow(ballPathSubsystem, pickupSubsystem),
    new CmdDriveDistance(driveSubsystem, 36, 0, 0.5, 0, false, false),
    new CmdDriveRotate(driveSubsystem, 5, 0.5, true),
    new CmdAutoShootWithVision(driveSubsystem, pickupSubsystem, ballPathSubsystem, shooterSubsystem, visionSubsystem, dashboardSubsystem)
    );
  }
}
