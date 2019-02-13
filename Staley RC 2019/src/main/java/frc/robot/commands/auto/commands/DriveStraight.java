/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

/**
 * Drive straight for a given distance. Needs fixing, driveturn is better
 */
public class DriveStraight extends Command {

	private final String TAG = (this.getName() + ": ");

	private DriveTrain driveTrain;
	private double distance;

	public DriveStraight(double distance) {
		driveTrain = DriveTrain.getInstance();
		requires(driveTrain);

		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain.zeroDriveEncoders();
		driveTrain.setTarget(distance);
		driveTrain.shiftLowGear();
		System.out.println("Starting target with distance of " + distance + " inches");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Left encoder pos: " + driveTrain.pulsesToInches(driveTrain.getLeftPosition())
				+ "\nRight encoder pos: " + driveTrain.pulsesToInches(driveTrain.getRightPosition())
				+ "\nAvg encoder pos: " + driveTrain.pulsesToInches(driveTrain.getPosition()));

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.stopMotors();
		System.out.println("End");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}