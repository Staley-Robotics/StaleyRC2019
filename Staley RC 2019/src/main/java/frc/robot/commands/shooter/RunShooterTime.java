/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.enums.PivotTargets;
import frc.robot.enums.PivotControlModes;
import frc.robot.subsystems.Shooter;

/**
 * Runs shooting/intake mechanism depending on whether power is +/- for given
 * amount of time
 */
public class RunShooterTime extends TimedCommand {

  private static Shooter shooter;

  private double power;
  private double defaultPower = 0.65;

  private PivotTargets pivotState;

  public RunShooterTime(double time) {
    super(time);
    shooter = Shooter.getInstance();
    requires(shooter);
    pivotState = shooter.pivotTarget;

    if (shooter.pivotControlMode == PivotControlModes.PID_CONTROL)
      this.power = pivotState.getPower();
    else
      this.power = defaultPower;
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    shooter.runShooter(power);
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