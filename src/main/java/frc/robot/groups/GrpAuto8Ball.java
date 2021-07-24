// package frc.robot.groups;

// import frc.robot.Constants;
// import frc.robot.abstraction.SwartdogCommand;
// import frc.robot.abstraction.SwartdogSequentialCommandGroup;
// import frc.robot.commands.CmdDriveDistance;
// import frc.robot.commands.CmdDriveRotate;
// import frc.robot.commands.CmdPickupDeploy;
// import frc.robot.commands.CmdPickupStow;
// import frc.robot.commands.CmdShooterFire;
// import frc.robot.commands.CmdShooterStart;
// import frc.robot.commands.CmdWait;
// import frc.robot.subsystems.BallPath;
// import frc.robot.subsystems.Dashboard;
// import frc.robot.subsystems.Pickup;
// import frc.robot.subsystems.Shooter;
// import frc.robot.subsystems.drive.Drive;

// public class GrpAuto8Ball extends SwartdogSequentialCommandGroup {
 
//   public GrpAuto8Ball((Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Drive driveSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem) {

//     super
//     (
//         SwartdogCommand.run(() -> shooterSubsystem.setTargetDistance(Constants.SHOOTER_FAR_DISTANCE)),
//         new CmdShooterStart(ballPathSubsystem, driveSubsystem, pickupSubsystem, shooterSubsystem),
//         new CmdWait(1.0),
//         new CmdShooterFire(dashboardSubsystem, driveSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
//         new CmdPickupDeploy(dashboardSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
//         new CmdDriveDistance(driveSubsystem, 60, 0, 0.5, 0, true, true),
//         new CmdDriveRotate(driveSubsystem, heading, maxSpeed, absolute)
//     );
//   }
// }
