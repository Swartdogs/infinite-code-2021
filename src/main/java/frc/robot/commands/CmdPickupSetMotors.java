package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;

public class CmdPickupSetMotors extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;
    private State    _state;

    public CmdPickupSetMotors(BallPath ballPathSubsystem, Pickup pickupSubsystem, State state) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;
        _state             = state;
    }

    @Override
    public void initialize() 
    {
        double speed = 0;

        if (_ballPathSubsystem.getBallCount() < Constants.MAX_BALL_COUNT)
        {
            switch (_state)
            {
                case On:
                    speed = Constants.PICKUP_SPEED;
                    break;

                case Reverse:
                    speed = -Constants.PICKUP_SPEED;
                    break;

                case Off:
                default:
                    speed = 0;
                    break;
            }
        }

        _pickupSubsystem.getLeftMotor().set(speed);
        _pickupSubsystem.getPrimaryMotor().set(speed);
        _pickupSubsystem.getRightMotor().set(speed);
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
