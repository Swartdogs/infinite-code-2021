package frc.robot;

import java.util.function.DoubleUnaryOperator;

public final class Constants
{
    public static final double              SWERVE_MODULE_SCALE     = 360 / 0.92;
    public static final double              SWERVE_MODULE_OFFSET    = (360 - SWERVE_MODULE_SCALE) / 2.0;

    public static final double              ROBOT_LENGTH            = 28.75;
    public static final double              ROBOT_WIDTH             = 20;

    public static final double              FL_MODULE_X             = -ROBOT_WIDTH / 2;
    public static final double              FL_MODULE_Y             = ROBOT_LENGTH / 2;
    public static final double              FL_MODULE_OFFSET        = 17;
    public static final double              FR_MODULE_X             = ROBOT_WIDTH / 2;
    public static final double              FR_MODULE_Y             = ROBOT_LENGTH / 2;
    public static final double              FR_MODULE_OFFSET        = 194;
    public static final double              BL_MODULE_X             = -ROBOT_WIDTH / 2;
    public static final double              BL_MODULE_Y             = -ROBOT_LENGTH / 2;
    public static final double              BL_MODULE_OFFSET        = 30;
    public static final double              BR_MODULE_X             = ROBOT_WIDTH / 2;
    public static final double              BR_MODULE_Y             = -ROBOT_LENGTH / 2;
    public static final double              BR_MODULE_OFFSET        = 176;

    public static final double              LIMELIGHT_LED_OFF       = 1;
    public static final double              LIMELIGHT_LED_ON        = 3;

    public static final double              TARGET_HEIGHT           = 0;
    public static final double              LIMELIGHT_HEIGHT        = 0;
    public static final double              TARGET_HEIGHT_DELTA     = TARGET_HEIGHT - LIMELIGHT_HEIGHT;

    public static final double              LIMELIGHT_ANGLE         = 0;

    public static final double              MOTOR_MOTION_THRESHOLD  = 0.02;
    public static final double              HANGER_MIN_POSITION     = -3755;
    public static final double              HANGER_MAX_POSITION     = -1780;

    public static final int                 MAX_BALL_COUNT          = 5;

    public static final double              PICKUP_SPEED            = 1;
    public static final double              PICKUP_CLOG_TIME        = 2;
    public static final double              PICKUP_STOW_DELAY       = 1;

    public static final double              BALLPATH_JAM_TIME       = 1.2;
    public static final double              BALLPATH_RAMP_MIN       = 0.02;
    public static final double              BALLPATH_RAMP_STEP      = 0.05;
    public static final double              BALLPATH_SPEED          = 0.55;

    public static final int                 SHOOTER_RPM_DEADBAND    = 50;

    public static final DoubleUnaryOperator HOOD_LOOKUP             = DoubleUnaryOperator.identity();
    public static final DoubleUnaryOperator SHOOTER_LOOKUP          = DoubleUnaryOperator.identity();
}
