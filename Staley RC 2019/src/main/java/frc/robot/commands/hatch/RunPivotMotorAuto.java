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

public class RunPivotMotorAuto extends Command {

  private static OI oi;
  private static HatchSlingingSlasher hatchSlingingSlasher;

  private double speed;

  double forwardPower;
  double reversePower;

  public RunPivotMotorAuto(double forwardPower,double reversePower) {
    hatchSlingingSlasher = HatchSlingingSlasher.getInstance();
    requires(hatchSlingingSlasher);
    oi = OI.getInstance();
    this.forwardPower = forwardPower;
    this.reversePower = reversePower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(forwardPower > 0.1) {
      hatchSlingingSlasher.runPivotMotor(forwardPower);
    } else if(reversePower > 0.1) {
      hatchSlingingSlasher.runPivotMotor(reversePower);
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