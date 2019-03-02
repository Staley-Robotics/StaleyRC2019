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
import frc.robot.enums.PivotTarget;
import frc.robot.subsystems.ShooterPivot;
import frc.robot.util.Constants;

public class SetPivotPosition extends Command {

  private ShooterPivot shooterPivot;

  private double targetPos;

  private PivotTarget targetPosition;

  public SetPivotPosition(PivotTarget targetPosition) {
    shooterPivot = ShooterPivot.getInstance();
    requires(shooterPivot);

    this.targetPosition = targetPosition;
    this.targetPos = targetPosition.getPosition();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    shooterPivot.pivotControlMode = PivotControlMode.PID_CONTROL;
    shooterPivot.setPivotTarget(targetPos);
    shooterPivot.pivotTarget = targetPosition;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("PID Control, Target:\t" + targetPos + "\tActual:\t" + shooterPivot.getPivotPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(OI.getInstance().getAltLeftY()) > Constants.SHOOTER_PIVOT_DEADZONE;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooterPivot.stopPivot();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
