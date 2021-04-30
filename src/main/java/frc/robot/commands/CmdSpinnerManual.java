package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.ControlPanelSpinner;

public class CmdSpinnerManual extends SwartdogCommand 
{
    private ControlPanelSpinner _spinnerSubsystem;
    private DoubleSupplier      _manual;

    public CmdSpinnerManual(ControlPanelSpinner spinnerSubsystem, DoubleSupplier manual) 
    {
        _spinnerSubsystem = spinnerSubsystem;
        _manual           = manual;

        addRequirements(_spinnerSubsystem);
    }

    @Override
    public void execute() 
    {
        _spinnerSubsystem.setSpinnerMotor(_manual.getAsDouble());
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
