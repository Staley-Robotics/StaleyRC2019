/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// ***** HID Controllers *****
	public static final int XBOX_DRIVE_PORT = 0;
	public static final int XBOX_ALT_PORT = 1;

	// ***** Drivetrain Ports *****
	public static final int LEFT_MASTER_DRIVE_CAN_ID = 2;
	public static final int LEFT_FOLLOWER_DRIVE_CAN_ID = 7;
	public static final int RIGHT_MASTER_DRIVE_CAN_ID = 0;
	public static final int RIGHT_FOLLOWER_DRIVE_CAN_ID = 10;

	public static final int DRIVE_SHIFTER_SOLENOID_PORT_ONE = 0;
	public static final int DRIVE_SHIFTER_SOLENOID_PORT_TWO = 1;

	// ***** Compressor Port *****
	public static final int COMPRESSOR_PORT = 0;

	// ***** Hatch Slinging Slasher Ports *****
	public static final int HATCH_SOLENOID_PORT_ONE = 7;
	public static final int HATCH_SOLENOID_PORT_TWO = 4;
	public static final int HATCH_PIVOT_TALON_PORT = 3;

	// ***** Shooter Intake/Outtake Ports *****
	public static final int THROUGHPUT_HIGH_TALON_PORT = 2;
	public static final int THROUGHPUT_LOW_TALON_PORT = 4;

	public static final int THROUGHPUT_LIMIT_SWITCH = 0;

	// ***** Shooter Pivot Ports *****
	public static final int SHOOTER_PIVOT_TALONSRX_CAN_ID = 1;
	public static final int SHOOTER_PIVOT_VICTORSPX_CAN_ID = 9;
	
	// ***** Climber Ports *****
	public static final int CLIMBER_FRONT_LIFTER_SOLENOID_PORT_ONE = 2;
	public static final int CLIMBER_FRONT_LIFTER_SOLENOID_PORT_TWO = 3;

	public static final int CLIMBER_REAR_LIFTER_SOLENOID_PORT_ONE = 5;
	public static final int CLIMBER_REAR_LIFTER_SOLENOID_PORT_TWO = 6;
}
