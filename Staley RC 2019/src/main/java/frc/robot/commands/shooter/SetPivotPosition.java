/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.PivotTargets;
import frc.robot.enums.PivotControlModes;
import frc.robot.subsystems.Shooter;

/**
 * Sets target angle for shooting mechanism. When started, will run until
 * another target angle is given or input from controller is received
 */
public class SetPivotPosition extends Command {

  private OI oi;
  private Shooter shooter;
  private static int index = 0;
  private int iter;
  private double tolerance = 2;

  // private double[] shooterAngleConsts = {
  // ShooterPivotSetpoints.GROUND.getAngle(),
  // ShooterPivotSetpoints.LOW.getAngle(), ShooterPivotSetpoints.MID.getAngle(),
  // ShooterPivotSetpoints.HIGH.getAngle(), ShooterPivotSetpoints.CARGO.getAngle()
  // };
  // private double[] shooterPowerConsts =
  // {ShooterPivotSetpoints.GROUND.getPower(),ShooterPivotSetpoints.LOW.getPower(),ShooterPivotSetpoints.MID.getPower(),ShooterPivotSetpoints.HIGH.getPower(),ShooterPivotSetpoints.CARGO.getPower()};
  private PivotTargets[] pivotTargets = { PivotTargets.GROUND, PivotTargets.LOW, PivotTargets.MID, PivotTargets.HIGH,
      PivotTargets.CARGO };

  public SetPivotPosition(int index) {
    oi = OI.getInstance();
    shooter = Shooter.getInstance();
    requires(shooter);

    this.index = index;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    shooter.pivotControlMode = PivotControlModes.PID_CONTROL;
    setEnum();
    shooter.setPivotTarget(pivotTargets[index].getAngle());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    shooter.runShooter(pivotTargets[index].getPower());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(shooter.getPivotAngle() - pivotTargets[index].getAngle()) < tolerance
        || Math.abs(oi.getAltLeftY()) > 0.2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.pivotControlMode = PivotControlModes.USER_CONTROL;

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
      shooter.pivotTarget = PivotTargets.GROUND;
    } else if (index == 1) {
      shooter.pivotTarget = PivotTargets.LOW;
    } else if (index == 2) {
      shooter.pivotTarget = PivotTargets.MID;
    } else if (index == 3) {
      shooter.pivotTarget = PivotTargets.HIGH;
    } else if (index == 4) {
      shooter.pivotTarget = PivotTargets.CARGO;
    }
  }

  private boolean inBounds(int i) {
    return i >= 0 && i < pivotTargets.length;
  }
}
