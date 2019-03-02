/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

/**
 * Ryan's jank ass shit
 */
public class EncoderDrive2 extends Command {
  private double leftDistance;
  private double rightDistance;
  private double leftDistanceDifference;
  private double rightDistanceDifference;
  private double leftPower;
  private double rightPower;
  private Timer timer;

  private final double timeConstant = 0.2;// 0.2 seconds
  private final double leftEncoderError = 1;
  private final double rightEncoderError = 1;

  private final double maxPower = 0.8;

  public DriveTrain driveTrain;

  public EncoderDrive2(double leftDistance, double rightDistance) {
    this.leftDistance = leftDistance;
    this.rightDistance = rightDistance;
    driveTrain = DriveTrain.getInstance();
    timer = new Timer();
    timer.start();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftDistanceDifference = leftDistanceDifference - driveTrain.getLeftPosition();
    rightDistanceDifference = rightDistanceDifference - driveTrain.getRightPosition();
    leftDistance = 0;
    rightDistance = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (leftDistanceDifference / leftEncoderError > rightDistanceDifference / rightEncoderError) {
      leftPower = maxPower;
      rightPower = maxPower / (rightDistance / leftDistance);
    } else {
      rightPower = maxPower;
      leftPower = maxPower / (leftDistance / rightDistance);

    }
    driveTrain.tankDrive(leftPower, rightPower);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(timeConstant);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
