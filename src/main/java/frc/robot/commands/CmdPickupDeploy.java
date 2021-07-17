package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;

public class CmdPickupDeploy extends SwartdogCommand 
{
    private Dashboard _dashboardSubsystem;
    private BallPath  _ballPathSubsystem;
    private Pickup    _pickupSubsystem;
    private Shooter   _shooterSubsystem;

    public CmdPickupDeploy(Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Pickup pickupSubsystem, Shooter shooterSubsystem) 
    {
        _dashboardSubsystem = dashboardSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;
        _pickupSubsystem    = pickupSubsystem;
        _shooterSubsystem   = shooterSubsystem;

        addRequirements(_pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        if (!_ballPathSubsystem.isJammed() &&
             _ballPathSubsystem.getBallCount() < Constants.DEFAULT_BALLPATH_MAX_BALL_COUNT &&
             !_shooterSubsystem.isShooterOn())
        {
            _pickupSubsystem.setLeftMotor(_dashboardSubsystem.getPickupSpeed());
            _pickupSubsystem.setPrimaryMotor(_dashboardSubsystem.getPickupSpeed());
            _pickupSubsystem.setRightMotor(_dashboardSubsystem.getPickupSpeed());

            _pickupSubsystem.deployPickup();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
