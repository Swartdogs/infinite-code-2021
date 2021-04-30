package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.Enumerations.ExtendState;
import frc.robot.subsystems.Hanger;

public class CmdHangerManual extends SwartdogCommand 
{
    private Hanger         _hangerSubsystem;
    private DoubleSupplier _manual;

    public CmdHangerManual(Hanger hangerSubsystem, DoubleSupplier manual) 
    {
        _hangerSubsystem = hangerSubsystem;
        _manual          = manual;

        addRequirements(_hangerSubsystem);
    }

    @Override
    public void execute()
    {
        double speed    = _manual.getAsDouble();
        double position = _hangerSubsystem.getHangerPosition();

        if ((Math.abs(speed) < Constants.HANGER_MOTOR_THRESHOLD) || 
            (speed < 0 && position < Constants.HANGER_MIN_POSITION) ||
            (speed > 0 && position > Constants.HANGER_MAX_POSITION))
        {
            _hangerSubsystem.setRatchetState(ExtendState.Retracted);
            _hangerSubsystem.setHangerMotor(0);
        }

        else 
        {
            _hangerSubsystem.setRatchetState(ExtendState.Extended);
            _hangerSubsystem.setHangerMotor(speed);
        }
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
