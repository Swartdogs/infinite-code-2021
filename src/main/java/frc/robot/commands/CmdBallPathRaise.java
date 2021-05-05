package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;

public class CmdBallPathRaise extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;

    private int      _pickupStowTimer;
    private boolean  _stowPickup;

    public CmdBallPathRaise(BallPath ballPathSubsystem, Pickup pickupSubsystem)
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;

        _pickupStowTimer   = 0;
        _stowPickup        = false;

        addRequirements(_ballPathSubsystem, _pickupSubsystem);
    }

    @Override
    public void initialize() 
    {
        _ballPathSubsystem.getUpperTrackSolenoid().retract();
        if (Math.abs(_pickupSubsystem.getPrimaryMotor().get()) < Constants.MOTOR_MOTION_THRESHOLD)
        {
            _pickupStowTimer = Math.max(0, (int)(50 * Constants.PICKUP_STOW_DELAY));
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
            _pickupSubsystem.getDeploySolenoid().extend();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return _pickupStowTimer <= 0;
    }
}
