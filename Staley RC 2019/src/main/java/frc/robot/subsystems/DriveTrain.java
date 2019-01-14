/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static DriveTrain instance;

  private WPI_TalonSRX rightFront;
  private WPI_VictorSPX rightFollower;

  private WPI_TalonSRX leftFront;
  private WPI_VictorSPX leftFollower;

  public DifferentialDrive drive;

  private DriveTrain() {
    rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_MOTOR_PORT);
    rightFollower = new WPI_VictorSPX(RobotMap.RIGHT_FOLLOWER_DRIVE_MOTOR_PORT);
    leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_MOTOR_PORT);
    leftFollower = new WPI_VictorSPX(RobotMap.LEFT_FOLLOWER_DRIVE_MOTOR_PORT);

    drive = new DifferentialDrive(leftFront, rightFront);
  }

  public static DriveTrain getInstance() {
    if(instance == null) {
      instance = new DriveTrain();
    }

    return instance;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }
}
