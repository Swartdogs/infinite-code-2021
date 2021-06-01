package frc.robot.subsystems;

import PIDControl.PIDControl;
import frc.robot.abstraction.Motor;
import frc.robot.abstraction.PositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class Drive extends SwartdogSubsystem 
{
    private Motor          _leftMotor;
    private Motor          _rightMotor;
    private PositionSensor _leftEncoder;
    private PositionSensor _rightEncoder;
    private PositionSensor _gyro;
    private PIDControl     _drivePID;
    private PIDControl     _rotatePID;

    public Drive(Motor          leftMotor,
                 Motor          rightMotor,
                 PositionSensor leftEncoder,
                 PositionSensor rightEncoder,
                 PositionSensor gyro,
                 PIDControl     drivePID,
                 PIDControl     rotatePID) 
    {
        _leftMotor      = leftMotor;
        _rightMotor     = rightMotor;
        _leftEncoder    = leftEncoder;
        _rightEncoder   = rightEncoder;
        _gyro           = gyro;
        _drivePID       = drivePID;
        _rotatePID      = rotatePID;
    }

    public void drive(double drive, double rotate)
    {

    }

    public void setLeftMotor(double speed)
    {

    }

    public void setRightMotor(double speed)
    {

    }

    public double getLeftEncoderPosition()
    {
        return 0;
    }

    public double getRightEncoderPosition()
    {
        return 0;
    }

    public double getHeading()
    {
        return 0;
    }

    public void driveInit()
    {

    }

    public double driveExec()
    {
        return 0;
    }

    public boolean driveIsFinished()
    {
        return false;
    }

    public void rotateInit()
    {
        
    }

    public double rotateExec()
    {
        return 0;
    }

    public boolean rotateIsFinished()
    {
        return false;
    }

    public void resetEncoders()
    {

    }

    public void resetGyro()
    {
        
    }
}
