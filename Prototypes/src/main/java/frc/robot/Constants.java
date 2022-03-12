package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Xbox Controllers Port Indexes
    public static final int DRIVE_CONTROLLER_ID = 0;
    public static final int OPERATOR_CONTROLLER_ID = 1;
    public static final int TEST_CONTROLLER_ID = 2;

    // CAN IDs for our motor controlle
    public static final int CLIMBER_1_CAN_ID = 14;
    public static final int CLIMBER_2_CAN_ID = 13;
    public static final int CLIMBER_3_CAN_ID = 2;

    public static final int CLIMBER_4_CAN_ID = 23;
    public static final int CLIMBER_5_CAN_ID = 16;
}
