/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.auto.commands.VisionTurning;
import com.kauailabs.navx.frc.AHRS;

/**
 * This is jank yeet
 */
public class CenterTape extends Command {

  private DriveTrain driveTrain;
  private boolean colusion;
  private AHRS ahrs;
  public CenterTape() {
    driveTrain = DriveTrain.getInstance();
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    VisionTurning vision = new VisionTurning();
    vision.start();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //if(ahrs.get)
    if(colusion)
      driveTrain.arcadeDrive(-0.8, 0);
      else
      driveTrain.arcadeDrive(0.8, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
