package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;

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