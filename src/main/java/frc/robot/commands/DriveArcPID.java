package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Drive.UseEncoder;

public class DriveArcPID extends CommandBase {

  double	m_angle;
	double 	m_distance;
	double	m_finalHeading;
	double	m_maxSpeed;
	boolean	m_resetCancel;
	boolean	m_resetGyro;
  double	m_startAngle;
  Drive   m_drive;
  boolean m_resetEncoders;
  
  public DriveArcPID(Drive drive, double distance, double finalHeading, double maxSpeed, boolean resetGyro, boolean resetEncoders) 
  {
    m_angle = 0;
    m_distance = distance;
    m_finalHeading = finalHeading;
    m_maxSpeed = fabs(maxSpeed);
    m_resetGyro = resetGyro;
    m_runStatus = 0;
    m_startAngle = 0;
    m_timeout = timeout;
    m_drive = drive;
    m_resetEncoders = resetEncoders;
  }

  @Override
  public void initialize() 
  {
		if (!m_resetGyro) m_startAngle = m_drive.GetAngle();	//Get current angle if gyro will not be reset
    m_angle = m_finalHeading - m_startAngle;			//Determine the angle of the arc
    
    UseEncoder useEncoder = UseEncoder.RightEncoder;	//Determine encoder to use based on direction of rotation
    if (m_angle < 0) useEncoder = UseEncoder.LeftEncoder;	//(Use encoder on the outside of the arc)
    
    m_drive.driveInit(m_distance, m_maxSpeed, 0, m_resetGyro, m_resetEncoders);
    m_drive.rotateInit(m_finalHeading, m_maxSpeed, m_resetGyro, false /*usingVision, haven't implemented it in */);
  }

  @Override
  public void execute() 
  {
    m_drive.rotatePIDSetpoint(m_angle * (Math.abs(m_drive.GetDistance()) / Math.abs(m_distance)) + m_startAngle);
    m_drive.arcadeDrive(m_drive.driveExec(), m_drive.rotateExec(false));
  }

  @Override
  public boolean isFinished()
  {
    return m_drive.drivePIDFinished();
  }
}
