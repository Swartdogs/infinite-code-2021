package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public final class Hardware
{
    private Hardware()
    {
        // private constructor prevents instantiation
    }

    public static void ConfigureHardware()
    {
        /* DEFINITION */

        // Ball Path
        BallPath.Motors.UPPER_TRACK         = new CANSparkMax(6, MotorType.kBrushless);
        BallPath.Motors.LOWER_TRACK         = new CANSparkMax(5, MotorType.kBrushless);

        BallPath.Sensors.POSITION_1_SENSOR  = new DigitalInput(4);
        BallPath.Sensors.POSITION_2_SENSOR  = new DigitalInput(2);
        BallPath.Sensors.SHOOTER_SENSOR     = new DigitalInput(3);

        // Drive
        Drive.Motors.LEFT_PRIMARY           = new CANSparkMax(2, MotorType.kBrushless);
        Drive.Motors.LEFT_SECONDARY         = new CANSparkMax(1, MotorType.kBrushless);
        Drive.Motors.RIGHT_PRIMARY          = new CANSparkMax(4, MotorType.kBrushless);
        Drive.Motors.RIGHT_SECONDARY        = new CANSparkMax(3, MotorType.kBrushless);

        Drive.Sensors.LEFT_ENCODER          = new DutyCycleEncoder(0);
        Drive.Sensors.RIGHT_ENCODER         = new DutyCycleEncoder(1);
        Drive.Sensors.GYRO                  = new ADXRS450_Gyro();

        // Pickup
        Pickup.Motors.PRIMARY               = new CANSparkMax(10, MotorType.kBrushless);
        Pickup.Motors.LEFT                  = new VictorSP(0);
        Pickup.Motors.RIGHT                 = new VictorSP(1);

        Pickup.Sensors.LEFT                 = new DigitalInput(0);
        Pickup.Sensors.RIGHT                = new DigitalInput(1);

        Pickup.Solenoids.DEPLOY             = new Solenoid(0);

        // Shooter
        Shooter.Motors.PRIMARY              = new CANSparkMax(7, MotorType.kBrushless);
        Shooter.Motors.SECONDARY            = new CANSparkMax(8, MotorType.kBrushless);

        Shooter.Solenoids.HOOD              = new Solenoid(4);

        /* CONFIGURATION */

        // Ball Path
        BallPath.Motors.UPPER_TRACK.setIdleMode(IdleMode.kBrake);
        BallPath.Motors.LOWER_TRACK.setIdleMode(IdleMode.kBrake);

        BallPath.Motors.LOWER_TRACK.setInverted(true);

        // Drive
        Drive.Motors.LEFT_PRIMARY.setInverted(true);
        Drive.Motors.LEFT_SECONDARY.setInverted(true);

        Drive.Motors.LEFT_SECONDARY.follow(Drive.Motors.LEFT_PRIMARY);
        Drive.Motors.RIGHT_SECONDARY.follow(Drive.Motors.RIGHT_PRIMARY);

        Drive.Sensors.LEFT_ENCODER.setDistancePerRotation(18.86);
        Drive.Sensors.RIGHT_ENCODER.setDistancePerRotation(18.86);

        // Shooter
        Shooter.Motors.SECONDARY.follow(Shooter.Motors.PRIMARY);
    }

    public static class BallPath
    {
        public static class Motors
        {
            public static CANSparkMax       UPPER_TRACK; 
            public static CANSparkMax       LOWER_TRACK;
        }

        public static class Sensors
        {
            public static DigitalInput      POSITION_1_SENSOR;
            public static DigitalInput      POSITION_2_SENSOR; 
            public static DigitalInput      SHOOTER_SENSOR; 
        }
    }

    public static class Drive
    {
        public static class Motors
        {
            public static CANSparkMax       LEFT_PRIMARY;
            public static CANSparkMax       LEFT_SECONDARY;
            public static CANSparkMax       RIGHT_PRIMARY;
            public static CANSparkMax       RIGHT_SECONDARY;
        }
        
        public static class Sensors
        {
            public static DutyCycleEncoder  LEFT_ENCODER;
            public static DutyCycleEncoder  RIGHT_ENCODER; 
            public static ADXRS450_Gyro     GYRO;   
        }
    }

    public static class Pickup
    {
        public static class Motors
        {
            public static CANSparkMax       PRIMARY;
            public static VictorSP          LEFT;
            public static VictorSP          RIGHT;
        }

        public static class Sensors
        {
            public static DigitalInput      LEFT;
            public static DigitalInput      RIGHT; 
        }

        public static class Solenoids
        {
            public static Solenoid          DEPLOY;
        }
    }

    public static class Shooter
    {
        public static class Motors
        {
            public static CANSparkMax       PRIMARY;
            public static CANSparkMax       SECONDARY;
        }

        public static class Solenoids
        {
            public static Solenoid          HOOD; 
        }
    }
}