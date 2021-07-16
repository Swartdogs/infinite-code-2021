package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Pickup;

public class CmdBallPathRaise extends SwartdogCommand 
{
    private Dashboard _dashboardSubsystem;
    private BallPath  _ballPathSubsystem;
    private Pickup    _pickupSubsystem;

    private int       _pickupStowTimer;
    private boolean   _stowPickup;

    public CmdBallPathRaise(Dashboard dashboardSubsystem, BallPath ballPathSubsystem, Pickup pickupSubsystem)
    {
        _dashboardSubsystem = dashboardSubsystem;
        _ballPathSubsystem  = ballPathSubsystem;
        _pickupSubsystem    = pickupSubsystem;

        _pickupStowTimer    = 0;
        _stowPickup         = false;

        addRequirements(_ballPathSubsystem, _pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        _ballPathSubsystem.raiseUpperTrack();

        if (Math.abs(_pickupSubsystem.getPrimaryMotor()) < Constants.MOTOR_MOTION_THRESHOLD)
        {
            _pickupStowTimer = Math.max(0, (int)(50 * _dashboardSubsystem.getPickupStowDelay()));
            _stowPickup      = true;
        }

        else
        {
            _pickupStowTimer = 0;
            _stowPickup      = false;
        }
    }

    @Override
    public void execute() 
    {
        _pickupStowTimer--;
    }

    @Override
    public void end(boolean interrupted) 
    {
        if (_stowPickup)
        {
            _pickupSubsystem.stowPickup();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return _pickupStowTimer <= 0;
    }
}
