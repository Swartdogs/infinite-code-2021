package frc.robot.subsystems;

import PIDControl.PIDControl;
import frc.robot.Constants;
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
        double left  = drive + rotate;
        double right = -drive + rotate;
        double max   = Math.max(Math.abs(left), Math.abs(right));

        if (max > 1.0)
        {
            left  /= max;
            right /= max;
        }

        _leftMotor.set(left);
        _rightMotor.set(right);
    }

    public void setLeftMotor(double speed)
    {
        _leftMotor.set(speed);
    }

    public void setRightMotor(double speed)
    {
        _rightMotor.set(speed);
    }

    public double getLeftEncoderPosition()
    {
        return _leftEncoder.get();
    }

    public double getRightEncoderPosition()
    {
        return _rightEncoder.get();
    }

    public double getHeading()
    {
        return _gyro.get();
    }

    public void driveInit(double distance, double heading, double maxSpeed, double minSpeed, boolean resetEncoder)
    {
        maxSpeed = Math.abs(maxSpeed);
        minSpeed = Math.abs(minSpeed);
        
        maxSpeed = Math.max(maxSpeed, minSpeed);
        minSpeed = Math.min(maxSpeed, minSpeed);

        _drivePID.setSetpoint(distance, getLeftEncoderPosition());// needs work (using only left encoder rn)
        _drivePID.setOutputRange(-maxSpeed, maxSpeed, minSpeed);
    
        rotateInit(getHeading(), 0.75);
    }

    public double driveExec()
    {
        return _drivePID.calculate(getLeftEncoderPosition());
    }

    public boolean driveIsFinished()
    {
        return _drivePID.atSetpoint();
    }

    public void rotateInit(double heading, double maxSpeed/*, boolean usingVision*/) // vision PID in vision subsystem?
    {
        maxSpeed = Math.abs(maxSpeed);

        _rotatePID.setSetpoint(heading, getHeading());

        _rotatePID.setSetpointDeadband(Constants.ROTATE_DEADBAND);
        _rotatePID.setOutputRange(-maxSpeed, maxSpeed);
    }

    public double rotateExec()
    {
        return _rotatePID.calculate(getHeading());
    }

    public boolean rotateIsFinished()
    {
        return _rotatePID.atSetpoint();
    }

    public void resetEncoders()
    {
        _leftEncoder.reset();
        _rightEncoder.reset();
    }

    public void resetGyro()
    {
        _gyro.reset();
    }
}
