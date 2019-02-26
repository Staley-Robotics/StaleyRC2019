/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Shooter;

/**
 * Runs shooting/intake mechanism depending on whether power is +/-
 */
public class ControllerShooter extends Command {

  private OI oi;
  private Shooter shooter;

	public ControllerShooter() {
    oi = OI.getInstance();
		shooter = Shooter.getInstance();
		requires(shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
    // double power = 0;
    // if(oi.getAltBump > oi.getAltRightTrigger()){
    //   power = oi.getAltLeftTrigger() * -1;
    // }
    // else if(oi.getAltRightTrigger() > oi.getAltLeftTrigger()){
    //   power = oi.getAltRightTrigger();
    // }

		// shooter.runShooter(power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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