/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.ShooterPivotSetpoints;
import frc.robot.enums.ShooterPivotStates;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;

/**
 * Sets target angle for shooting mechanism. When started, will run until
 * another target angle is given or input from controller is received
 */
public class SetPivotPosition extends Command {

  private OI oi;
  private Shooter shooter;
  private static int index = 0;
  private int iter;
  // fill these out
  private double[] shooterConsts = { Constants.GROUND_ANGLE, Constants.LOW_ROCKET_ANGLE, Constants.MID_ROCKET_ANGLE,
      Constants.HIGH_ROCKET_ANGLE, Constants.CARGO_ANGLE };

  public SetPivotPosition(int iter) {
    oi = OI.getInstance();
    shooter = Shooter.getInstance();
    requires(shooter);

    this.iter = iter;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    shooter.shooterPivotState = ShooterPivotStates.PID_CONTROL;

    if (inBounds(index + iter)) {
      index += iter;
    } else {
      if (index < 0) {
        index = 0;
      } else {
        index = shooterConsts.length - 1;
      }
    }

    setEnum();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(shooter.getPivotAngle() - shooterConsts[index]) < 2 || Math.abs(oi.getAltLeftY()) > 0.2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.shooterPivotState = ShooterPivotStates.USER_CONTROL;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  public void setEnum() {
    // GROUND, LOW, MID, HIGH, CARGO in order of height
    if (index == 0) {
      shooter.pivotSetpoint = ShooterPivotSetpoints.GROUND;
    } else if (index == 1) {
      shooter.pivotSetpoint = ShooterPivotSetpoints.LOW;
    } else if (index == 2) {
      shooter.pivotSetpoint = ShooterPivotSetpoints.MID;
    } else if (index == 3) {
      shooter.pivotSetpoint = ShooterPivotSetpoints.HIGH;
    } else if (index == 4) {
      shooter.pivotSetpoint = ShooterPivotSetpoints.CARGO;
    }
  }

  protected boolean inBounds(int i) {
    return i >= 0 && i < shooterConsts.length;
  }
}
