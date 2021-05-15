package frc.robot.subsystems;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import frc.robot.abstraction.MockMotor;
import frc.robot.abstraction.MockPositionSensor;
import frc.robot.abstraction.MockSolenoid;
import frc.robot.abstraction.SwartdogSubsystem;
import frc.robot.abstraction.Enumerations.ExtendState;

import static org.junit.Assert.*;

public class HangerTest 
{
    private static final double EPSILON = 1e-2;

    private MockMotor           _hangerMotor;
    private MockSolenoid        _releaseSolenoid;
    private MockSolenoid        _ratchetSolenoid;
    private MockPositionSensor  _hangerPositionSensor;

    private Hanger              _hangerSubsystem;

    @BeforeClass
    public static void setup()
    {
        SwartdogSubsystem.REGISTER_SUBSYSTEMS = false;
    }

    @Before
    public void initialize()
    {
        _hangerMotor            = new MockMotor();
        _releaseSolenoid        = new MockSolenoid();
        _ratchetSolenoid        = new MockSolenoid();
        _hangerPositionSensor   = new MockPositionSensor();

        _hangerSubsystem = new Hanger
        (
            _hangerMotor,
            _releaseSolenoid,
            _ratchetSolenoid,
            _hangerPositionSensor
        );
    }

    @Test
    public void testHangerMotorOffByDefault()
    {
        assertEquals(0, _hangerMotor.get(), EPSILON);
    }

    @Test
    public void testSetHangerMotorTo17()
    {
        _hangerSubsystem.setHangerMotor(17);

        assertEquals(17, _hangerMotor.get(), EPSILON);
    }

    @Test
    public void testSetHangerMotorToMinus105()
    {
        _hangerSubsystem.setHangerMotor(-105);

        assertEquals(-105, _hangerMotor.get(), EPSILON);
    }

    @Test
    public void testReleaseHanger()
    {
        _hangerSubsystem.releaseHanger();

        assertEquals(ExtendState.Retracted, _releaseSolenoid.get());
    }

    @Test
    public void testIsHangerReleased()
    {
        _releaseSolenoid.set(ExtendState.Retracted);

        assertTrue(_hangerSubsystem.isHangerReleased());
    }

    @Test
    public void testEngageRatchet()
    {
        _hangerSubsystem.engageRatchet();

        assertEquals(ExtendState.Retracted, _ratchetSolenoid.get());
    }

    @Test
    public void testDisengageRatchet()
    {
        _hangerSubsystem.disengageRatchet();

        assertEquals(ExtendState.Extended, _ratchetSolenoid.get());
    }

    @Test
    public void testPosition0ByDefault()
    {
        assertEquals(0, _hangerSubsystem.getPosition(), EPSILON);
    }

    @Test
    public void testPositionAt17()
    {
        _hangerPositionSensor.set(17);

        assertEquals(17, _hangerSubsystem.getPosition(), EPSILON);
    }

    @Test
    public void testPositionAtMinus105()
    {
        _hangerPositionSensor.set(-105);

        assertEquals(-105, _hangerSubsystem.getPosition(), EPSILON);
    }
}
