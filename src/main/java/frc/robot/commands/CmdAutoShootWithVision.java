package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Shooter.Preset;
import frc.robot.subsystems.drive.Drive;

public class CmdAutoShootWithVision extends SwartdogCommand {
    
    private Drive       _driveSubsystem;
    private Pickup      _pickupSubsystem;
    private BallPath    _ballPathSubsystem;
    private Shooter     _shooterSubsystem;
    private Vision      _visionSubsystem;
    private Dashboard   _dashboardSubsystem;

    public CmdAutoShootWithVision(Drive driveSubsystem, Pickup pickupSubsystem, BallPath ballPathSubsystem, Shooter shooterSubsystem, Vision visionSubsystem, Dashboard dashboardSubsystem) {
    
        _driveSubsystem     = driveSubsystem;
        _pickupSubsystem    = pickupSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;
        _shooterSubsystem   = shooterSubsystem;
        _visionSubsystem    = visionSubsystem;
        _dashboardSubsystem = dashboardSubsystem;
    }

    @Override
    public void initialize() {
        _visionSubsystem.enableVisionProcessing();
        _visionSubsystem.rotateInit();
        _shooterSubsystem.startShooter();
        _shooterSubsystem.setPreset(Preset.Vision);
    }

    @Override
    public void execute() {
        double rotate = 0;
        
        if (_visionSubsystem.targetFound())
        {
            rotate = _visionSubsystem.rotateExec();
        }
        
        _driveSubsystem.drive(0, 0, rotate);
        if (_visionSubsystem.targetFound() && _visionSubsystem.rotateIsFinished()) {
            _ballPathSubsystem.setTrackMotor(_dashboardSubsystem.getBallPathSpeed());
            _pickupSubsystem.setPrimaryMotor(_dashboardSubsystem.getPickupSpeed());
        } else {
            _ballPathSubsystem.setTrackMotor(0);
            _pickupSubsystem.setPrimaryMotor(0);
        }
        if (_ballPathSubsystem.shooterSensorTransitionedTo(State.Off)) {
            _ballPathSubsystem.decrementBallCount();
        }
    }

    @Override
    public void end(boolean interrupted) {
        _visionSubsystem.disableVisionProcessing();
        _shooterSubsystem.stopShooter();
        _ballPathSubsystem.setTrackMotor(0);
        _pickupSubsystem.setPrimaryMotor(0);
        _driveSubsystem.drive(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return _ballPathSubsystem.getBallCount() <= 0;
    }
}
