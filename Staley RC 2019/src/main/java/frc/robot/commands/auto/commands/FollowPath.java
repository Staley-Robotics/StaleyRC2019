/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import java.io.File;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.Constants;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class FollowPath extends Command {

  private DriveTrain driveTrain;

  private EncoderFollower leftFollower;
  private EncoderFollower rightFollower;

  private Trajectory leftTrajectory;
  private Trajectory rightTrajectory;

  // private Notifier followerNotifier;

  public FollowPath() {
    driveTrain = DriveTrain.getInstance();
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftTrajectory = Pathfinder.readFromCSV(new File(
        "C:\\Users\\Staley Robotics\\Documents\\fuvk\\StaleyRC2019\\Staley RC 2019\\src\\main\\resources\\output\\DriveAutoLine.right.pf1.csv"));
    rightTrajectory = Pathfinder.readFromCSV(new File(
        "C:\\Users\\Staley Robotics\\Documents\\fuvk\\StaleyRC2019\\Staley RC 2019\\src\\main\\resources\\output\\DriveAutoLine.left.pf1.csv"));

    leftFollower = new EncoderFollower(leftTrajectory);
    rightFollower = new EncoderFollower(rightTrajectory);

    leftFollower.configureEncoder((int) driveTrain.getLeftPosition(), (int) Constants.PULSES_PER_REV,
        Constants.WHEEL_DIAMETER);
    leftFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / Constants.MAX_VELOCITY, 0);

    rightFollower.configureEncoder((int) driveTrain.getRightPosition(), (int) Constants.PULSES_PER_REV,
        Constants.WHEEL_DIAMETER);
    rightFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / Constants.MAX_VELOCITY, 0);

    // followerNotifier = new Notifier(this::followPath);
    // followerNotifier.startPeriodic(leftTrajectory.get(0).dt);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftSpeed = leftFollower.calculate((int) driveTrain.getLeftPosition());
    double rightSpeed = rightFollower.calculate((int) driveTrain.getRightPosition());

    double heading = driveTrain.getYaw();
    double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
    double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading);
    double turn = 0.8 * (-1.0 / 80.0) * headingDifference;

    driveTrain.tankDrive(leftSpeed + turn, rightSpeed - turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return leftFollower.isFinished() || rightFollower.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // followerNotifier.stop();
    driveTrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  // private void followPath() {
  // if (leftFollower.isFinished() || rightFollower.isFinished()) {
  // followerNotifier.stop();
  // } else {
  // double leftSpeed = leftFollower.calculate((int)
  // driveTrain.getLeftPosition());
  // double rightSpeed = rightFollower.calculate((int)
  // driveTrain.getRightPosition());

  // double heading = driveTrain.getYaw();
  // double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
  // double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading -
  // heading);
  // double turn = 0.8 * (-1.0 / 80.0) * headingDifference;

  // driveTrain.tankDrive(leftSpeed + turn, rightSpeed - turn);
  // }
  // }
}
