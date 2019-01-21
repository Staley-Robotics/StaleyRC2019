package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

public class DriveStraight extends Command {

	private final String TAG = (this.getName() + ": ");

	private DriveTrain driveTrain;
	private double distance;

	// PID values
	private final double kP = 0.0353;
	private final double kI = 0.005;
	private final double kD = 0.2; // 0.2

	public DriveStraight(double distance) {
		requires(DriveTrain.getInstance());
		driveTrain = DriveTrain.getInstance();
		this.distance = distance;

	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return drivePos.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.execute(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}