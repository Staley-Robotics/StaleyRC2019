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
 * Uses encoders to drive to a desired distance while also be capable of turning
 * at the same time.
 */
public class DriveTurn extends Command {

	private final String TAG = (this.getName() + ": ");

	private final int pThreshold = 4;// 5
	private final double stopThreshold = 0.5;

	private final double kP = 0.15;

	DriveTrain driveTrain;

	private double desiredDistance;
	private double desiredPower;
	private double startingDisplacement;
	private double currentDisplacement;

	private double turnPower;
	private double startingAngle;
	private double currentAngle;

	public DriveTurn(double inches, double power, double turnPower) {
		driveTrain = DriveTrain.getInstance();
		requires(driveTrain);

		desiredDistance = inches;
		desiredPower = power;

		this.turnPower = turnPower;
	}

	// For SickoMode
	// Sickomode, more like "bad"omode
	public DriveTurn(double pulses, double power, double turn, boolean b) {
		driveTrain = DriveTrain.getInstance();
		requires(driveTrain);

		desiredDistance = driveTrain.pulsesToInches(pulses);
		desiredPower = power;

		this.turnPower = turn;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startingDisplacement = driveTrain.pulsesToInches(driveTrain.getPosition());

		startingAngle = driveTrain.getYaw();
		currentAngle = startingAngle;

		System.out.println(TAG + "Drive Turn Initialized.  Gyro at: " + startingAngle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		currentAngle = driveTrain.getYaw() - startingAngle;
		currentDisplacement = driveTrain.pulsesToInches(driveTrain.getPosition()) - startingDisplacement;

		double error = Math.abs(desiredDistance) - Math.abs(currentDisplacement);
		double kTurnP = 0.24;
		if (error < pThreshold) {
			if (turnPower == 0 && desiredPower > 0) {
				driveTrain.arcadeDrive(error * kP, -currentAngle * kTurnP);
			} else if (turnPower == 0 && desiredPower < 0) {
				driveTrain.arcadeDrive(-error * kP, currentAngle * kTurnP);
			} else {
				driveTrain.arcadeDrive(desiredPower, turnPower);
			}
		} else {
			if (turnPower == 0) {
				driveTrain.arcadeDrive(desiredPower, -currentAngle * kTurnP);
			} else {
				driveTrain.arcadeDrive(desiredPower, turnPower);
			}
		}
		System.out.println(TAG + "Gyro: " + currentAngle + "\t\tError: " + error + "\t\tCurrent Diplacemnt: "
				+ currentDisplacement);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(currentDisplacement - desiredDistance) <= stopThreshold)
			System.out.println(TAG + "Ended with distance");
		return Math.abs(currentDisplacement - desiredDistance) <= stopThreshold
				|| Math.abs(desiredDistance) - Math.abs(currentDisplacement) < 0;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out
				.println(TAG + "Target: " + desiredDistance + "\tCurrent Displacement: " + currentDisplacement + "\n");
		driveTrain.arcadeDrive(0, 0);
		currentDisplacement = 0;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
