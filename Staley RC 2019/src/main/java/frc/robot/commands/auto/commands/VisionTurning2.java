/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

/**
 * Uses gyro to turn the robot toward vision target until gyro yaw is zero
 */
public class VisionTurning2 extends Command implements PIDOutput {

  // PID Values
  private final double kP = 0.025; // 0.023 //0.03
  private final double kI = 0.0; // 0
  private final double kD = 0.06; // 0.06

  private DriveTrain driveTrain;
  private Vision vision;

  private PIDController pidTurn;

  private double currentAngle;
  private double targetAngle;
  private double offset;

  public VisionTurning2() {
    requires(DriveTrain.getInstance());
    driveTrain = DriveTrain.getInstance();
    vision = Vision.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    offset = vision.getYaw();

    currentAngle = driveTrain.getYaw();
    System.out.println("Current Angle:\t" + currentAngle);

    targetAngle = offset + currentAngle;

    targetAngle = bindTo180(targetAngle);

    pidTurn = new PIDController(kP, kI, kD, driveTrain.getNavx(), this);

    // Range of angles that can be inputted
    pidTurn.setInputRange(-180, 180);

    // Prevents the motors from receiving too little power
    if (offset > 0)
      pidTurn.setOutputRange(0.5, 0.8);
    else if (offset < 0)
      pidTurn.setOutputRange(-0.8, -0.5);

    // Tolerance of how far off the angle can be
    pidTurn.setAbsoluteTolerance(1);
    pidTurn.setContinuous(true);

    pidTurn.setSetpoint(targetAngle);
    pidTurn.enable();
    System.out.println("Starting to Vision turn");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Vision Magic crap.  who even knows\t" + currentAngle + " at offset: " + offset);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pidTurn.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTrain.arcadeDrive(0, 0);

    System.out.println("Finished - Angle: " + driveTrain.getYaw() + "\tTarget Angle:" + targetAngle);

    pidTurn.disable();
    pidTurn.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  /*
   * Outputs the motor speed from the PIDController
   */
  @Override
  public void pidWrite(double output) {
    driveTrain.arcadeDrive(0, output);
  }

  /**
   * Takes angle value and converts it to fall within a range of -180 to 180
   * degrees
   * 
   * @param angle angle value
   * @return angle value that falls between -180 and 180 degrees
   */
  private double bindTo180(double angle) {
    // Keshvi magic; she is a math god
    while (angle >= 180) {
      angle -= 360;
    }
    while (angle < -180) {
      angle += 360;
    }
    return angle;
  }
}
