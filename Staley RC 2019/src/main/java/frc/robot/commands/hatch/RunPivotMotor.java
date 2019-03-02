/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.HatchSlingingSlasher;

/**
 * Runs the pivot motor based off user input
 */
public class RunPivotMotor extends Command {

  private static OI oi;
  private static HatchSlingingSlasher hatchSlingingSlasher;

  public RunPivotMotor() {
    oi = OI.getInstance();
    hatchSlingingSlasher = HatchSlingingSlasher.getInstance();
    requires(hatchSlingingSlasher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double forwardPower = oi.getAltRightTrigger() * 0.5;
    double reversePower = oi.getAltLeftTrigger() * 0.5;

    if (forwardPower > 0.1) {
      hatchSlingingSlasher.runPivotMotor(forwardPower);
    } else if (reversePower > 0.1) {
      hatchSlingingSlasher.runPivotMotor(-reversePower);
    } else {
      hatchSlingingSlasher.runPivotMotor(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
