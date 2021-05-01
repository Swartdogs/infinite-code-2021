package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.ControlPanelSpinner;

public class CmdSpinnerToPosition extends SwartdogCommand 
{
    private ControlPanelSpinner _spinnerSubsystem;
    private double              _position;

    public CmdSpinnerToPosition(ControlPanelSpinner spinnerSubsystem, double position)
    {
        _spinnerSubsystem = spinnerSubsystem;
        _position         = position;

        addRequirements(_spinnerSubsystem);
    }

    @Override
    public void initialize() 
    {
        _spinnerSubsystem.spinnerInit(_position);
    }

    @Override
    public void execute() 
    {
        _spinnerSubsystem.getSpinnerMotor().set(_spinnerSubsystem.spinnerExec());
    }

    @Override
    public void end(boolean interrupted) 
    {
        _spinnerSubsystem.getSpinnerMotor().set(0);
    }

    @Override
    public boolean isFinished() 
    {
        return _spinnerSubsystem.spinnerIsFinished();
    }
}
