/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter.pivot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.PivotControlMode;
import frc.robot.subsystems.ShooterPivot;
import frc.robot.util.Constants;

/**
 * Angles the shooting mechanism
 */
public class RunShooterPivot extends Command {

  private static OI oi;

  private static ShooterPivot shooterPivot;

  public RunShooterPivot() {
    oi = OI.getInstance();
    shooterPivot = ShooterPivot.getInstance();
    requires(shooterPivot);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //If the Limit Switch is activated, the shooterPivot is set to zero
    if (shooterPivot.getLimitSwitch()) {
      shooterPivot.zeroPivotEncoder();
    }

    double power = oi.getAltLeftY();

    if (Math.abs(power) > Constants.SHOOTER_PIVOT_DEADZONE) {
      shooterPivot.pivotControlMode = PivotControlMode.USER_CONTROL;
    }

    if (shooterPivot.pivotControlMode == PivotControlMode.USER_CONTROL) {
      shooterPivot.runPivot((power > 0 && shooterPivot.getLimitSwitch()) ? 0 : power);
    } else {
      shooterPivot.runPivot(0);
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
    shooterPivot.runPivot(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
