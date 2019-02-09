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

public class RunShooterPivot extends Command {

  private static OI oi;
  private static Shooter shooter;

  public RunShooterPivot() {
    oi = OI.getInstance();
    shooter = Shooter.getInstance();
    requires(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (shooter.shooterPivotState == ShooterPivotStates.USER_CONTROL) {
      double power = oi.getAltLeftY();

      if (Math.abs(power) > 0.1) {
        shooter.pivotShooter(power);
      } else {
        shooter.pivotShooter(0);
      }
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
    shooter.pivotShooter(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
   end();
  }
}
