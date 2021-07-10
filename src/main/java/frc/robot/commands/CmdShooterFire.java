package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.State;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;

public class CmdShooterFire extends SwartdogCommand 
{
    private BallPath _ballPathSubsystem;
    private Pickup   _pickupSubsystem;
    private Shooter  _shooterSubsystem;

    public CmdShooterFire(BallPath ballPathSubsystem, 
                          Pickup   pickupSubsystem, 
                          Shooter  shooterSubsystem) 
    {
        _ballPathSubsystem = ballPathSubsystem;
        _pickupSubsystem   = pickupSubsystem;
        _shooterSubsystem  = shooterSubsystem;

        addRequirements(_ballPathSubsystem, _pickupSubsystem, _shooterSubsystem);
    }

    @Override
    public void execute() 
    {
        if (_shooterSubsystem.isShooterOn()) 
        {
            _ballPathSubsystem.setTrackMotor(Constants.BALLPATH_SPEED);
            _pickupSubsystem.setPrimaryMotor(Constants.PICKUP_SPEED);

            if (_ballPathSubsystem.shooterSensorTransitionedTo(State.Off)) {
                _ballPathSubsystem.decrementBallCount();
            }
        }
    }

    @Override
    public void end(boolean interrupted) 
    {
        _ballPathSubsystem.setTrackMotor(0);
        
        if (_shooterSubsystem.isShooterOn()) _pickupSubsystem.setPrimaryMotor(0);

        if (!interrupted) _shooterSubsystem.stopShooter();
    }

    @Override
    public boolean isFinished() 
    {
        return (_ballPathSubsystem.getBallCount() <= 0) || 
               (!_shooterSubsystem.isShooterOn());
    }
}
