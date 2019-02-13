/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchSlingingSlasher;

/**
 * Toggles between extending and retracting the hatch placing mechanism
 */
public class HatchExtenderToggle extends Command {

  private HatchSlingingSlasher hatchSlingingSlasher;

  private static boolean isExtended;

  public HatchExtenderToggle() {
    hatchSlingingSlasher = HatchSlingingSlasher.getInstance();
    requires(hatchSlingingSlasher);

    isExtended = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(isExtended) {
      hatchSlingingSlasher.retract();
    } else {
      hatchSlingingSlasher.extend();
    }
    isExtended = !isExtended;
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
