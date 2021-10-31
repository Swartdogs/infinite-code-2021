package frc.robot.groups;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.SwartdogSequentialCommandGroup;
import frc.robot.commands.CmdAutoShootWithVision;
import frc.robot.commands.CmdDriveDistance;
import frc.robot.commands.CmdDriveRotate;
import frc.robot.commands.CmdPickupDeploy;
import frc.robot.commands.CmdPickupStow;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;

public class GrpAuto6BallFriendlyTrenchVision extends SwartdogSequentialCommandGroup 
{
    public GrpAuto6BallFriendlyTrenchVision(Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Drive driveSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem, Vision visionSubsystem) 
    {
        super
        (
            SwartdogCommand.run(() -> driveSubsystem.setGyro(0)),
            new CmdAutoShootWithVision(driveSubsystem, pickupSubsystem, ballPathSubsystem, shooterSubsystem, visionSubsystem, dashboardSubsystem),
            new CmdDriveRotate(driveSubsystem, 0, 0.7, true),
            new CmdPickupDeploy(dashboardSubsystem, ballPathSubsystem, pickupSubsystem, shooterSubsystem),
            new CmdDriveDistance(driveSubsystem, 76, 0, 0.5, 0, true, true),
            new CmdDriveDistance(driveSubsystem, 170, 0, 0.2, 0, false, true),
            new CmdPickupStow(ballPathSubsystem, pickupSubsystem),
            new CmdDriveDistance(driveSubsystem, -125, 0, 0.5, 0, true, true),
            new CmdAutoShootWithVision(driveSubsystem, pickupSubsystem, ballPathSubsystem, shooterSubsystem, visionSubsystem, dashboardSubsystem),
            SwartdogCommand.run(() -> driveSubsystem.setGyro(driveSubsystem.getHeading() + 180))
        );
    }
}
