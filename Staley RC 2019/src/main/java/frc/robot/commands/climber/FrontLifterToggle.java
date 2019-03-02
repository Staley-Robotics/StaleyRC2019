/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

/**
 * Toggles the front pistons for the climber.
 */
public class FrontLifterToggle extends Command {

	private Climber climber;

	private static boolean isExtended;

	public FrontLifterToggle() {
		climber = Climber.getInstance();
		requires(climber);
		
		isExtended = false;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Toggles to extend piston
		if (isExtended) {
			climber.retractFront();
			isExtended = false;
		}
		// Toggles to retract piston
		else {
			climber.extendFront();
			isExtended = true;
		}
		System.out.println("front " + isExtended);
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