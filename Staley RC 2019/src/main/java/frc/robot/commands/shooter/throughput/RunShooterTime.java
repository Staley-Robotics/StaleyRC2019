/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter.throughput;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.enums.PivotTarget;
import frc.robot.enums.PivotControlMode;
import frc.robot.subsystems.ShooterThroughput;
import frc.robot.subsystems.ShooterPivot;

/**
 * Runs shooting/intake mechanism depending on whether power is +/- for given
 * amount of time
 */
public class RunShooterTime extends TimedCommand {

  private static ShooterThroughput shooter;

  private double power;
  private double defaultPower = 0.3;

  private double batteryWatt;

  private PivotTarget pivotTarget;

  private ShooterPivot shooterPivot;

  public RunShooterTime(double time) {
    super(time);
    shooter = ShooterThroughput.getInstance();
    requires(shooter);
    shooterPivot = ShooterPivot.getInstance();
    requires(shooterPivot);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    pivotTarget = shooterPivot.pivotTarget;

    if (shooterPivot.pivotControlMode == PivotControlMode.PID_CONTROL)
      this.power = pivotTarget.getPower();
    else
      this.power = defaultPower;
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    shooter.runShooter(power);
    System.out.println("Pivot Target: " + pivotTarget + "\tPower for ShoooterTime:" + power);
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