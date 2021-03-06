/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

/**
 * Shifts drivetrain into low gear
 */
public class ShiftLowGear extends Command {

    DriveTrain driveTrain;

    public ShiftLowGear() {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		driveTrain.shiftLowGear();
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