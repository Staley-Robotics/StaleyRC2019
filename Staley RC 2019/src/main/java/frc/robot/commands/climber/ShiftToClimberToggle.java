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
 * Toggles between climber mode and drive mode
 */
public class ShiftToClimberToggle extends Command {

  private Climber climber;
  
  private static boolean inClimberMode;

  public ShiftToClimberToggle() {
    climber = Climber.getInstance();
		requires(climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    inClimberMode = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Shifts to drive mode
		if (inClimberMode) {
			climber.shiftToDrive();
			inClimberMode = false;
		}
		// Shifts to climb mode
		else {
			climber.shiftToClimber();
			inClimberMode = true;
		}
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
