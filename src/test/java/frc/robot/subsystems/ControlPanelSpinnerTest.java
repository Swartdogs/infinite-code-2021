package frc.robot.subsystems;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;
import frc.robot.abstraction.MockMotor;
import frc.robot.abstraction.MockPositionSensor;
import frc.robot.abstraction.SwartdogSubsystem;

public class ControlPanelSpinnerTest 
{
    private static final double EPSILON = 1e-2;

    private MockMotor           _spinnerMotor;
    private MockPositionSensor  _positionSensor;
    private PIDControl          _spinnerPID;
    private ControlPanelSpinner _spinnerSubsystem;

    @BeforeClass
    public static void setup()
    {
        SwartdogSubsystem.REGISTER_SUBSYSTEMS = false;
    }

    @Before
    public void initialize()
    {
        _spinnerMotor   = new MockMotor();
        _positionSensor = new MockPositionSensor(0);
        _spinnerPID     = new PIDControl();

        _spinnerPID.setCoefficient(Coefficient.P, 0, 1, 0);
        _spinnerPID.setCoefficient(Coefficient.I, 0, 0, 0);
        _spinnerPID.setCoefficient(Coefficient.D, 0, 0, 0);
        _spinnerPID.setInputRange(-1, 1);
        _spinnerPID.setOutputRange(-1, 1);
        _spinnerPID.setOutputRamp(0, 1);
        _spinnerPID.setSetpointDeadband(EPSILON);

        _spinnerSubsystem = new ControlPanelSpinner
        (
            _spinnerMotor,
            _positionSensor,
            _spinnerPID
        );
    }

    @Test
    public void testSpinnerOffByDefault()
    {
        assertEquals(0, _spinnerMotor.get(), EPSILON);
    }

    @Test
    public void testSetSpinnerMotorTo17()
    {
        _spinnerSubsystem.setSpinnerMotor(17);

        assertEquals(17, _spinnerMotor.get(), EPSILON);
    }

    @Test
    public void testSetSpinnerMotorToMinus105()
    {
        _spinnerSubsystem.setSpinnerMotor(-105);

        assertEquals(-105, _spinnerMotor.get(), EPSILON);
    }

    @Test
    public void testPosition0ByDefault()
    {
        assertEquals(0, _spinnerSubsystem.getPosition(), EPSILON);
    }

    @Test
    public void testGetPositionAt17()
    {
        _positionSensor.set(17);

        assertEquals(17, _spinnerSubsystem.getPosition(), EPSILON);
    }

    @Test
    public void testGetPositionAtMinus105()
    {
        _positionSensor.set(-105);

        assertEquals(-105, _spinnerSubsystem.getPosition(), EPSILON);
    }

    @Test
    public void testTarget0()
    {
        _spinnerSubsystem.spinnerInit(0);

        assertTrue(_spinnerPID.atSetpoint());
    }

    @Test
    public void testTarget1()
    {
        _spinnerSubsystem.spinnerInit(1);

        assertEquals(1, _spinnerSubsystem.spinnerExec(), EPSILON);
        assertFalse(_spinnerSubsystem.spinnerIsFinished());
    }

    @Test
    public void testTargetMinusHalf()
    {
        _spinnerSubsystem.spinnerInit(-0.5);

        assertEquals(-0.5, _spinnerSubsystem.spinnerExec(), EPSILON);
        assertFalse(_spinnerSubsystem.spinnerIsFinished());
    }

    @Test
    public void testTargetHalfFromMinusHalf()
    {
        // position targetting is relative, not absolute
        _positionSensor.set(-0.5);
        _spinnerSubsystem.spinnerInit(0.5);

        assertEquals(0.5, _spinnerSubsystem.spinnerExec(), EPSILON);
        assertFalse(_spinnerSubsystem.spinnerIsFinished());

        _positionSensor.set(0.5);

        assertEquals(0, _spinnerSubsystem.spinnerExec(), EPSILON);
        assertTrue(_spinnerSubsystem.spinnerIsFinished());
    }
}
