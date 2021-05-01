package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.Pickup;

public class CmdPickupDefault extends SwartdogCommand 
{
    private Pickup _pickupSubsystem;

    private int    _clogTimer;

    public CmdPickupDefault(Pickup pickupSubsystem) 
    {
        _pickupSubsystem = pickupSubsystem;

        _clogTimer       = 0;

        addRequirements(_pickupSubsystem);
    }

    @Override
    public void execute() 
    {
        if (_pickupSubsystem.getDeploySolenoid().get() == ExtendState.Retracted)
        {
            State leftState  = _pickupSubsystem.getLeftLightSensor().get();
            State rightState = _pickupSubsystem.getRightLightSensor().get();

            if (leftState == State.On && rightState == State.On)
            {
                _clogTimer = Math.max(0, (int)(50 * Constants.PICKUP_CLOG_TIME));
            }

            if (_clogTimer > 0)
            {
                double speed = 0;
                
                _clogTimer--;

                if (_clogTimer == 0)
                {
                    speed = _pickupSubsystem.getRightMotor().get();
                }

                _pickupSubsystem.getLeftMotor().set(speed);   
            }
        }

        else
        {
            _clogTimer = 0;
        }
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
