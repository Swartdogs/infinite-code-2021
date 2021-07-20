package frc.robot;

public final class Constants
{
    // Constants
    public static final double              SWERVE_MODULE_SCALE     = 360 / 0.92;
    public static final double              SWERVE_MODULE_OFFSET    = (360 - SWERVE_MODULE_SCALE) / 2.0;
    public static final double              DRIVE_ENCODER_SCALE     = 155.5 /* inches */ / 99086.0 /* encoder ticks */;

    public static final double              ROBOT_LENGTH            = 28.75;
    public static final double              ROBOT_WIDTH             = 20;

    public static final int                 FL_INDEX                = 0;
    public static final double              FL_MODULE_X             = -ROBOT_WIDTH / 2;
    public static final double              FL_MODULE_Y             = ROBOT_LENGTH / 2;

    public static final int                 FR_INDEX                = 1;
    public static final double              FR_MODULE_X             = ROBOT_WIDTH / 2;
    public static final double              FR_MODULE_Y             = ROBOT_LENGTH / 2;

    public static final int                 BL_INDEX                = 2;
    public static final double              BL_MODULE_X             = -ROBOT_WIDTH / 2;
    public static final double              BL_MODULE_Y             = -ROBOT_LENGTH / 2;

    public static final int                 BR_INDEX                = 3;
    public static final double              BR_MODULE_X             = ROBOT_WIDTH / 2;
    public static final double              BR_MODULE_Y             = -ROBOT_LENGTH / 2;

    public static final double              MAX_ROTATE_SPEED        = 0.75;

    public static final double              LIMELIGHT_LED_OFF       = 1;
    public static final double              LIMELIGHT_LED_ON        = 3;

    public static final double              TARGET_HEIGHT           = 0;
    public static final double              LIMELIGHT_HEIGHT        = 0;
    public static final double              TARGET_HEIGHT_DELTA     = TARGET_HEIGHT - LIMELIGHT_HEIGHT;

    public static final double              LIMELIGHT_ANGLE         = 0;

    public static final double              MOTOR_MOTION_THRESHOLD  = 0.02;

    public static final double              SHOOTER_NEAR_DISTANCE   = 10;
    public static final double              SHOOTER_FAR_DISTANCE    = 300;

    // Default Dashboard Settings
    public static final double              DEFAULT_FL_MODULE_OFFSET             = 17;
    public static final double              DEFAULT_FR_MODULE_OFFSET             = 194;
    public static final double              DEFAULT_BL_MODULE_OFFSET             = 30;
    public static final double              DEFAULT_BR_MODULE_OFFSET             = 176;

    public static final double              DEFAULT_HANGER_SPEED                 = 1;
    public static final double              DEFAULT_HANGER_MIN_POSITION          = -1195;
    public static final double              DEFAULT_HANGER_MAX_POSITION          = -590;

    public static final int                 DEFAULT_BALLPATH_MAX_BALL_COUNT      = 5;
    public static final int                 DEFAULT_BALLPATH_STARTING_BALL_COUNT = 3;
    public static final double              DEFAULT_BALLPATH_JAM_TIME            = 1.2;
    public static final double              DEFAULT_BALLPATH_RAMP_MIN            = 0.025;
    public static final double              DEFAULT_BALLPATH_RAMP_STEP           = 0.05;
    public static final double              DEFAULT_BALLPATH_SPEED               = 0.525;

    public static final double              DEFAULT_PICKUP_SPEED                 = 0.8;
    public static final double              DEFAULT_PICKUP_CLOG_TIME             = 2;
    public static final double              DEFAULT_PICKUP_STOW_DELAY            = 1.5;

    public static final int                 DEFAULT_SHOOTER_RPM_DEADBAND         = 50;
    public static final double              DEFAULT_HOOD_MIN_POSITION            = 980;
    public static final double              DEFAULT_HOOD_NEAR_TARGET             = DEFAULT_HOOD_MIN_POSITION - 100;
    public static final double              DEFAULT_HOOD_FAR_TARGET              = DEFAULT_HOOD_MIN_POSITION - 590;
    public static final double              DEFAULT_HOOD_MAX_POSITION            = DEFAULT_HOOD_MIN_POSITION - 700;
    public static final double              DEFAULT_SHOOTER_NEAR_SPEED           = 3525;
    public static final double              DEFAULT_SHOOTER_FAR_SPEED            = 5500;
    public static final double              DEFAULT_SHOOTER_ROTATE_SCALER        = 0.25;
}
