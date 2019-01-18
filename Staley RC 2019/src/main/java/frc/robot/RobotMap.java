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
	public static final int XBOX_PORT = 0;
	public static final int XBOX_TWO_PORT = 1;

	// ***** Motor/Speed Controller Ports *****
	public static final int LEFT_FRONT_DRIVE_MOTOR_PORT = 3;
	public static final int LEFT_FOLLOWER_DRIVE_MOTOR_PORT = 8;
	public static final int RIGHT_FRONT_DRIVE_MOTOR_PORT = 2;
	public static final int RIGHT_FOLLOWER_DRIVE_MOTOR_PORT = 5;

	// ***** Pneumatics Ports *****
	public static final int COMPRESSOR_PORT = 0;
}
