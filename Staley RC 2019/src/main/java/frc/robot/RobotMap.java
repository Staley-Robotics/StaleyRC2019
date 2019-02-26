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

	// ***** Motor/Speed Controller Ports *****
	public static final int LEFT_MASTER_DRIVE_CAN_ID = 0;
	public static final int LEFT_FOLLOWER_DRIVE_CAN_ID = 7;
	public static final int RIGHT_MASTER_DRIVE_CAN_ID = 2;
	public static final int RIGHT_FOLLOWER_DRIVE_CAN_ID = 10;

	// ***** Compressor Port *****
	public static final int COMPRESSOR_PORT = 0;

	// ***** Hatch Slinging Slasher Ports *****
	public static final int HATCH_SOLENOID_PORT = 05;
	public static final int HATCH_PIVOT_MOTOR_PORT = 3;

	// ***** Shooter Ports *****
	public static final int SHOOTER_TOP_TALON_PORT = 1; //0
	public static final int SHOOTER_RIGHT_TALON_PORT = 2; //1
	public static final int SHOOTER_LEFT_TALON_PORT = 0; //2
	public static final int SHOOTER_LIMIT_SWITCH = 00;

	public static final int SHOOTER_PIVOT_TALONSRX_CAN_ID = 1; //1
	public static final int SHOOTER_PIVOT_VICTORSPX_CAN_ID = 9; //7
	
	// ***** Climber Ports *****
	public static final int FRONT_LIFTER_PORT = 00;
	public static final int REAR_LIFTER_PORT = 00;
	public static final int SHIFT_TO_CLIMBER_PORT = 00;
}
