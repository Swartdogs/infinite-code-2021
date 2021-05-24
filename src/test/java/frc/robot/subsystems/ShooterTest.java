package frc.robot.subsystems;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;
import frc.robot.abstraction.MockMotor;
import frc.robot.abstraction.MockPositionSensor;
import frc.robot.abstraction.MockVelocitySensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class ShooterTest 
{
    private static final double EPSILON = 1e-2;

    private MockMotor           _shooterMotor;
    private MockMotor           _hoodMotor;
    private MockPositionSensor  _hoodSensor;
    private PIDControl          _hoodPID;
    private Shooter             _shooterSubsystem;

    @BeforeClass
    public static void setup()
    {
        SwartdogSubsystem.REGISTER_SUBSYSTEMS = false;
    }

    @Before
    public void initialize()
    {
        _shooterMotor   = new MockMotor();
        _hoodMotor      = new MockMotor();
        _hoodSensor     = new MockPositionSensor(0);
        _hoodPID        = new PIDControl();

        _hoodPID.setCoefficient(Coefficient.P, 0, 1, 0);
        _hoodPID.setCoefficient(Coefficient.I, 0, 0, 0);
        _hoodPID.setCoefficient(Coefficient.D, 0, 0, 0);
        _hoodPID.setInputRange(-1, 1);
        _hoodPID.setOutputRange(-1, 1);
        _hoodPID.setOutputRamp(0, 1);
        _hoodPID.setSetpointDeadband(EPSILON);

        _shooterSubsystem = new Shooter
        (
            _shooterMotor,
            _hoodMotor,
            _hoodSensor,
            _hoodPID
        );
    }

    @Test
    public void testShooterOffByDefault()
    {
        assertEquals(0, _shooterSubsystem.getShooterMotor(), EPSILON);
    }

    @Test
    public void testSetShooterTo500()
    {
        _shooterSubsystem.setShooterMotor(500);

        assertEquals(500, _shooterMotor.get(), EPSILON);
    }

    @Test
    public void testSetShooterTo5000()
    {
        _shooterSubsystem.setShooterMotor(5000);

        assertEquals(5000, _shooterMotor.get(), EPSILON);
    }

    @Test
    public void testShooterMotorSetpoint0ByDefault()
    {
        assertEquals(0, _shooterSubsystem.getShooterMotorSetpoint(), EPSILON);
    }

    @Test
    public void testShooterMotorSetpoint500OnMotorSet()
    {
        _shooterSubsystem.setShooterMotor(500);

        assertEquals(500, _shooterSubsystem.getShooterMotorSetpoint(), EPSILON);
    }

    @Test
    public void testShooterMotorSetpoint5000OnMotorSet()
    {
        _shooterSubsystem.setShooterMotor(5000);

        assertEquals(5000, _shooterSubsystem.getShooterMotorSetpoint(), EPSILON);
    }

    @Test
    public void testShooterMotorSpeed0ByDefault()
    {
        assertEquals(0, _shooterSubsystem.getShooterMotor(), EPSILON);
    }

    @Test
    public void testShooterMotorSpeed0OnSet()
    {
        _shooterSubsystem.setShooterMotor(5000);

        assertEquals(0, _shooterSubsystem.getShooterMotor(), EPSILON);
    }

    @Test
    public void testShooterMotorSpeedFromVelocitySensor()
    {
        ((MockVelocitySensor)_shooterMotor.getVelocitySensor()).set(5000);

        assertEquals(5000, _shooterSubsystem.getShooterMotor(), EPSILON);
    }

    @Test
    public void testHoodMotorOffByDefault()
    {
        assertEquals(0, _hoodMotor.get(), EPSILON);
    }

    @Test
    public void testHoodMotorSetTo17()
    {
        _shooterSubsystem.setHoodMotor(17);

        assertEquals(17, _hoodMotor.get(), EPSILON);
    }

    @Test
    public void testHoodMotorSetToMinus105()
    {
        _shooterSubsystem.setHoodMotor(-105);

        assertEquals(-105, _hoodMotor.get(), EPSILON);
    }

    @Test
    public void testHoodPosition0ByDefault()
    {
        assertEquals(0, _shooterSubsystem.getHoodPosition(), EPSILON);
    }

    @Test
    public void testHoodPositionSetTo17()
    {
        _hoodSensor.set(17);

        assertEquals(17, _shooterSubsystem.getHoodPosition(), EPSILON);
    }

    @Test
    public void testHoodPositionSetToMinus105()
    {
        _hoodSensor.set(-105);

        assertEquals(-105, _shooterSubsystem.getHoodPosition(), EPSILON);
    }

    @Test
    public void testTarget0()
    {
        _shooterSubsystem.setHoodSetpoint(0);

        assertTrue(_hoodPID.atSetpoint());
    }

    @Test
    public void testTarget1()
    {
        _shooterSubsystem.setHoodSetpoint(1);

        assertEquals(1, _shooterSubsystem.hoodExec(), EPSILON);
        assertFalse(_shooterSubsystem.hoodAtSetpoint());
    }

    @Test
    public void testTargetMinusHalf()
    {
        _shooterSubsystem.setHoodSetpoint(-0.5);

        assertEquals(-0.5, _shooterSubsystem.hoodExec(), EPSILON);
        assertFalse(_shooterSubsystem.hoodAtSetpoint());
    }

    @Test
    public void testTargetHalfFromMinusHalf()
    {
        _hoodSensor.set(-0.5);
        _shooterSubsystem.setHoodSetpoint(0.5);

        assertEquals(1, _shooterSubsystem.hoodExec(), EPSILON);
        assertFalse(_shooterSubsystem.hoodAtSetpoint());

        _hoodSensor.set(0);

        assertEquals(0.5, _shooterSubsystem.hoodExec(), EPSILON);
        assertFalse(_shooterSubsystem.hoodAtSetpoint());

        _hoodSensor.set(0.5);

        assertEquals(0, _shooterSubsystem.hoodExec(), EPSILON);
        assertTrue(_shooterSubsystem.hoodAtSetpoint());
    }
}
