package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Hanger;

public class CmdHangerManual extends SwartdogCommand 
{
    private Dashboard      _dashboardSubsystem;
    private Hanger         _hangerSubsystem;
    private DoubleSupplier _manual;

    public CmdHangerManual(Dashboard dashboardSubsystem, Hanger hangerSubsystem, DoubleSupplier manual) 
    {
        _dashboardSubsystem = dashboardSubsystem;
        _hangerSubsystem    = hangerSubsystem;
        _manual             = manual;

        addRequirements(_hangerSubsystem);
    }

    @Override
    public void execute()
    {
        double speed    = _manual.getAsDouble();
        double position = _hangerSubsystem.getPosition();

        if ((Math.abs(speed) < Constants.MOTOR_MOTION_THRESHOLD) || 
            (speed < 0 && position < _dashboardSubsystem.getHangerMinPosition()) ||
            (speed > 0 && position > _dashboardSubsystem.getHangerMaxPosition()))
        {
            _hangerSubsystem.engageRatchet();
            _hangerSubsystem.setHangerMotor(0);
        }

        else 
        {
            _hangerSubsystem.disengageRatchet();
            _hangerSubsystem.setHangerMotor(speed);
        }
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
