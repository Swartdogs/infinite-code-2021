package frc.robot.subsystems;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import PIDControl.PIDControl;
import PIDControl.PIDControl.Coefficient;
import frc.robot.Constants;
import frc.robot.abstraction.MockNetworkTableBoolean;
import frc.robot.abstraction.MockNetworkTableDouble;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Enumerations.State;

public class VisionTest 
{
    private static final double EPSILON = 1e-2;

    private MockNetworkTableDouble  _xPosition;
    private MockNetworkTableDouble  _yPosition;
    private MockNetworkTableDouble  _targetFound;
    private MockNetworkTableDouble  _ledMode;
    private MockNetworkTableDouble  _pipeline;
    private PIDControl              _pidControl;
    private Vision                  _visionSubsystem;

    @BeforeClass
    public static void setup()
    {
        SwartdogSubsystem.REGISTER_SUBSYSTEMS = false;
    }

    @Before
    public void initialize() 
    {
        _xPosition          = new MockNetworkTableDouble(0);
        _yPosition          = new MockNetworkTableDouble(0);
        _targetFound        = new MockNetworkTableDouble(0);
        _ledMode            = new MockNetworkTableDouble(Constants.LIMELIGHT_LED_OFF);
        _pipeline           = new MockNetworkTableDouble(0);
        _pidControl         = new PIDControl();

        _pidControl.setCoefficient(Coefficient.P, 0, 1, 0);
        _pidControl.setCoefficient(Coefficient.I, 0, 0, 0);
        _pidControl.setCoefficient(Coefficient.D, 0, 0, 0);
        _pidControl.setInputRange(-1, 1);
        _pidControl.setOutputRange(-1, 1);
        _pidControl.setOutputRamp(0, 1);
        _pidControl.setSetpointDeadband(EPSILON);

        _visionSubsystem = new Vision
        (
            _xPosition, 
            _yPosition, 
            _targetFound, 
            _ledMode, 
            _pipeline,
            _pidControl
        );
    }

    @Test
    public void testLEDsOffByDefault()
    {
        assertEquals(Constants.LIMELIGHT_LED_OFF, _ledMode.get(), EPSILON);
    }

    @Test
    public void testTurnLEDsOn()
    {
        _visionSubsystem.setLEDs(State.On);

        assertEquals(Constants.LIMELIGHT_LED_ON, _ledMode.get(), EPSILON);
    }

    @Test
    public void testTurnLEDsOff()
    {
        _visionSubsystem.setLEDs(State.Off);

        assertEquals(Constants.LIMELIGHT_LED_OFF, _ledMode.get(), EPSILON);
    }

    @Test
    public void testNoTargetFoundByDefault()
    {
        assertFalse(_visionSubsystem.targetFound());
    }

    @Test
    public void testFindTarget()
    {
        _targetFound.set(1);

        assertTrue(_visionSubsystem.targetFound());
    }

    @Test
    public void testTargetAngle0ByDefault()
    {
        assertEquals(0, _visionSubsystem.getTargetAngle(), EPSILON);
    }

    @Test
    public void testTargetAngle26()
    {
        _xPosition.set(26);

        assertEquals(26, _visionSubsystem.getTargetAngle(), EPSILON);
    }

    @Test
    public void testTargetAngleMinus12()
    {
        _xPosition.set(-12);

        assertEquals(-12, _visionSubsystem.getTargetAngle(), EPSILON);
    }

    @Test
    public void testDistanceWith45DegreeAngle()
    {
        _yPosition.set(45 - Constants.LIMELIGHT_ANGLE);

        assertEquals(Constants.TARGET_HEIGHT_DELTA, _visionSubsystem.getTargetDistance(), EPSILON);
    }

    @Test
    public void testPIDTargets0Angle()
    {
        _visionSubsystem.rotateInit();

        assertTrue(_pidControl.atSetpoint());
    }

    @Test
    public void testPIDOffBy1()
    {
        _xPosition.set(1);
        _visionSubsystem.rotateInit();

        assertEquals(1, _visionSubsystem.rotateExec(), EPSILON);
        assertFalse(_visionSubsystem.rotateIsFinished());
    }

    @Test
    public void testPIDOffByMinusHalf()
    {
        _xPosition.set(-0.5);
        _visionSubsystem.rotateInit();

        assertEquals(-0.5, _visionSubsystem.rotateExec(), EPSILON);
        assertFalse(_visionSubsystem.rotateIsFinished());
    }
}
