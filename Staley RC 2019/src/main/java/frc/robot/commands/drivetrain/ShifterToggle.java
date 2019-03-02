/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.enums.GearState;
import frc.robot.subsystems.DriveTrain;

/**
 * Toggles the shifter between high and low gear
 */
public class ShifterToggle extends Command {

	private final String TAG = (this.getName() + ": ");

	DriveTrain driveTrain;

	public ShifterToggle() {
		driveTrain = DriveTrain.getInstance();
		requires(driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Toggles to high gear
		if (DriveTrain.gearState == GearState.LOW_GEAR) {
			driveTrain.shiftHighGear();

			System.out.println(TAG + "High Gear");
		}
		// Toggles to low gear
		else {
			driveTrain.shiftLowGear();

			System.out.println(TAG + "Low Gear");
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}