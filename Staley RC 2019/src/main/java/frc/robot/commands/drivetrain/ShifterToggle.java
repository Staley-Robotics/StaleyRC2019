package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.enums.GearStates;
import frc.robot.subsystems.DriveTrain;

/**
 * Toggles the shifter to high/low gear
 */
public class ShifterToggle extends Command {
	
    private final String TAG = (this.getName() + ": ");
    
    DriveTrain driveTrain;
	
	// Color lowColor = Color.RED;
	// Color highColor = Color.GREEN;

	public ShifterToggle() {
        requires(DriveTrain.getInstance());
        driveTrain = DriveTrain.getInstance();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Toggles to high gear
		if (driveTrain.gearState == GearStates.LOW_GEAR) {
			driveTrain.shifterOn();
						
			System.out.println(TAG + "High Gear");
		}
		// Toggles to low gear
		else {
			driveTrain.shifterOff();
						
			System.out.println(TAG + "Low Gear");
        }
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		if (DriveTrain.gearState == GearStates.LOW_GEAR) {
//			System.out.println(TAG + "Shifter on End");
		} else {
//			System.out.println(TAG + "Shifter off End");
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}