/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.ShooterPivotStates;
import frc.robot.subsystems.Shooter;

/**
 * Sets target angle for shooting mechanism. When started, will run until
 * another target angle is given or input from controller is received
 */
public class SetPivotAngle extends Command {

  private OI oi;
  private Shooter shooter;

  private double targetAngle;

  public SetPivotAngle(double angle) {
    shooter = Shooter.getInstance();
    requires(shooter);
    oi = OI.getInstance();

    targetAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    shooter.setPivotTarget(targetAngle);
    shooter.shooterPivotState = ShooterPivotStates.PID_CONTROL;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(oi.getAltLeftY()) > 0.1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.runPivot(0);
    shooter.shooterPivotState = ShooterPivotStates.USER_CONTROL;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
