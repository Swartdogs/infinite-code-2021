package frc.robot.groups;

import java.util.function.DoubleSupplier;

import frc.robot.abstraction.SwartdogSequentialCommandGroup;
import frc.robot.commands.CmdDriveWithShooter;
import frc.robot.commands.CmdShooterStart;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.drive.Drive;

public class GrpShootWithVision extends SwartdogSequentialCommandGroup 
{
    public GrpShootWithVision(BallPath       ballPathSubsystem,
                              Drive          driveSubsystem, 
                              Pickup         pickupSubsystem, 
                              Shooter        shooterSubsystem, 
                              Vision         visionSubsystem, 
                              DoubleSupplier drive, 
                              DoubleSupplier strafe, 
                              DoubleSupplier rotate
    ) 
    {

        super
        (
            new CmdShooterStart(ballPathSubsystem, driveSubsystem, pickupSubsystem, shooterSubsystem),
            new CmdDriveWithShooter(driveSubsystem, shooterSubsystem, visionSubsystem, drive, strafe, rotate)
        );
    }
}