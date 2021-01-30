package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SolenoidState;
import frc.robot.subsystems.Pickup;

public class DeployPickup extends CommandBase 
{
    private Pickup m_pickupSubsystem;

    public DeployPickup(Pickup pickupSubsystem) 
    {
        m_pickupSubsystem = pickupSubsystem;
    }

    @Override
    public void initialize() 
    {
        m_pickupSubsystem.setDeploySolenoid(SolenoidState.Extended);
    }

    @Override
    public void execute() 
    {
        m_pickupSubsystem.setLeftMotor(1);
        m_pickupSubsystem.setPrimaryMotor(1);
        m_pickupSubsystem.setRightMotor(1);
    }

    @Override
    public void end(boolean interrupted) 
    {
        m_pickupSubsystem.setDeploySolenoid(SolenoidState.Retracted);
        
        m_pickupSubsystem.setLeftMotor(0);
        m_pickupSubsystem.setPrimaryMotor(0);
        m_pickupSubsystem.setRightMotor(0);
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }
}