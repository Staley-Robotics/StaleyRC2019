package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

/**
 * Toggles the front piston for the climber.
 */
public class RearLifterToggle extends Command {

	private static boolean out;

	private Climber climber;

	public RearLifterToggle() {
		climber = Climber.getInstance();
		requires(climber);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		out = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Toggles to extend piston
		if (out) {
			climber.retractRear();
			out = false;
		}
		// Toggles to retract piston
		else {
			climber.extendRear();
			out = true;
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
		end();
	}
}