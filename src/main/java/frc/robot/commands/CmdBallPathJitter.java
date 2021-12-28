package frc.robot.commands;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.subsystems.BallPath;
import frc.robot.subsystems.Dashboard;

public class CmdBallPathJitter extends SwartdogCommand 
{
  private BallPath  _ballPathSubsytem;
  private Dashboard _dashboardSubsytem;
  
  public CmdBallPathJitter(BallPath ballPathSubsystem, Dashboard dashboardSubsystem) 
  {
    _ballPathSubsytem  = ballPathSubsystem;
    _dashboardSubsytem = dashboardSubsystem;
  }

  @Override
  public void initialize() 
  {
    if (!_ballPathSubsytem.isUpperTrackRaised())
    {
      _ballPathSubsytem.setTrackMotor(_dashboardSubsytem.getBallPathSpeed() * 0.33);
    }
  }

  @Override
  public void end(boolean interrupted) 
  {
    _ballPathSubsytem.setTrackMotor(0);
  }

  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
