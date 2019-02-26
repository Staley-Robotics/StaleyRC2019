/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.HatchSlingingSlasher;

public class StraightenHatch extends TimedCommand {

  private HatchSlingingSlasher hatchSlingingSlasher;

  private double power;

  public StraightenHatch(double power, double time) {
    super(time);
    hatchSlingingSlasher = HatchSlingingSlasher.getInstance();
    requires(hatchSlingingSlasher);

    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchSlingingSlasher.runPivotMotor(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchSlingingSlasher.stopPivotMotor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
