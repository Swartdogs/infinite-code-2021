package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;

public class CmdPickupSetDeploySolenoid extends SwartdogCommand 
{
    private Pickup      _pickupSubsystem;
    private BallPath    _ballPathSubsystem;
    private ExtendState _state;

    public CmdPickupSetDeploySolenoid(Pickup      pickupSubsystem, 
                                      BallPath    ballPathSubsystem, 
                                      ExtendState state)
    {
        _pickupSubsystem   = pickupSubsystem;
        _ballPathSubsystem = ballPathSubsystem;
        _state             = state;
    }

    @Override
    public void initialize() 
    {
        if (_ballPathSubsystem.getUpperTrackSolenoid().get() == ExtendState.Extended)
        {
            _pickupSubsystem.getDeploySolenoid().set(ExtendState.Retracted);
        }

        else if (_ballPathSubsystem.getBallCount() >= Constants.MAX_BALL_COUNT)
        {
            _pickupSubsystem.getDeploySolenoid().set(ExtendState.Extended);
        }

        else 
        {
            _pickupSubsystem.getDeploySolenoid().set(_state);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
