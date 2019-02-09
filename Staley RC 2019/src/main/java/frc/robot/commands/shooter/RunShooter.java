package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * 
 */
public class RunShooter extends Command {

	private static boolean out;

    private static Shooter shooter;
    private double speed;
	
	public RunShooter(double speed) {
        shooter = Shooter.getInstance();
		requires(shooter);

		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.runShooter(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
        shooter.stopShooter();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}