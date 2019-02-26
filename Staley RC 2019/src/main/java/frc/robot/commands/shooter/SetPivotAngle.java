/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.PivotControlModes;
import frc.robot.enums.PivotTargets;
import frc.robot.subsystems.Shooter;

public class SetPivotAngle extends Command {

  private Shooter shooter;

  private double targetAngle;

  private PivotTargets targetPosition;

  public SetPivotAngle(PivotTargets targetPosition) {
    shooter = Shooter.getInstance();
    requires(shooter);

    this.targetPosition = targetPosition;
    this.targetAngle = targetPosition.getAngle();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    shooter.pivotControlMode = PivotControlModes.PID_CONTROL;
    shooter.setPivotTarget(targetAngle);

    // untested
    // for (PivotTargets pt : PivotTargets.values()) {
    //   if (pt.getAngle() == targetAngle) {
    //     shooter.pivotTarget = pt;
    //     break;
    //   }
    // }

    shooter.pivotTarget = targetPosition;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("PID Control, Target:\t" + targetAngle + "\tActual:\t" + shooter.getPivotAngle());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(OI.getInstance().getAltLeftY()) > 0.1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.pivotControlMode = PivotControlModes.USER_CONTROL;
    shooter.stopPivot();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
