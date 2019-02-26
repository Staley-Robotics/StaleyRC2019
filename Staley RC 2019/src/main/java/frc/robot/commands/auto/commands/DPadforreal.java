/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.shooter.SetPivotPosition;
import frc.robot.enums.PivotTargets;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class DPadforreal extends Command {

  private int position;

  private Shooter shooter;
  private PivotTargets[] pivotTargets = { PivotTargets.GROUND, PivotTargets.LOW, PivotTargets.MID, PivotTargets.HIGH,
      PivotTargets.CARGO };

  public DPadforreal(int position) {
    this.position = position;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (position == 0) {

    } else if (position == 90) {
      position = 1;
    } else if (position == 180) {
      position = 2;
    } else if (position == 270) {
      position = 3;
    }
    if (position != -1) {
      setEnum();
      shooter.setPivotTarget(pivotTargets[position].getAngle());
    }
  }

  public void setEnum() {
    // GROUND, LOW, MID, HIGH, CARGO in order of height
    if (position == 0) {
      shooter.pivotTarget = PivotTargets.GROUND;
    } else if (position == 1) {
      shooter.pivotTarget = PivotTargets.LOW;
    } else if (position == 2) {
      shooter.pivotTarget = PivotTargets.MID;
    } else if (position == 3) {
      shooter.pivotTarget = PivotTargets.HIGH;
    } else if (position == 4) {
      shooter.pivotTarget = PivotTargets.CARGO;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
