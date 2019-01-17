/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ControllerDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  private final String TAG = (this.getName() + ": ");

  private static DriveTrain instance;

  private WPI_TalonSRX rightFront;
  private WPI_VictorSPX rightFollower;

  private WPI_TalonSRX leftFront;
  private WPI_VictorSPX leftFollower;

  private AHRS navx;

  private boolean brakeFront;
  private boolean brakeFollower;

  public DifferentialDrive drive;

  private final double kP = 0.015;
	private final double kI = 0.0;
  private final double kD = 0.7;

  private DriveTrain() {

    brakeFront = false;
    brakeFollower = false;

    try{
      rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_MOTOR_PORT);
      rightFollower = new WPI_VictorSPX(RobotMap.RIGHT_FOLLOWER_DRIVE_MOTOR_PORT);

      leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_MOTOR_PORT);
      leftFollower = new WPI_VictorSPX(RobotMap.LEFT_FOLLOWER_DRIVE_MOTOR_PORT);

      rightFollower.follow(rightFront);
      leftFollower.follow(leftFront);

      rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
      leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

      rightFront.setSensorPhase(false);
      leftFront.setSensorPhase(true);

      rightFront.setNeutralMode(brakeFront ? NeutralMode.Brake : NeutralMode.Coast);
      leftFront.setNeutralMode(brakeFront ? NeutralMode.Brake : NeutralMode.Coast);
      rightFollower.setNeutralMode(brakeFollower ? NeutralMode.Brake : NeutralMode.Coast);
      leftFollower.setNeutralMode(brakeFollower ? NeutralMode.Brake : NeutralMode.Coast);

      rightFront.config_kP(0, kP, 0);
      leftFront.config_kP(0, kP, 0);

      rightFront.config_kI(0, kI, 0);
      leftFront.config_kI(0, kI, 0);

      rightFront.config_kD(0, kD, 0);
      leftFront.config_kD(0, kD, 0);

    } catch (RuntimeException ex) {
        DriverStation.reportError("Error Instantiating TalonSRX: " + ex.getMessage(), true);
    }

    try{
      navx = new AHRS(Port.kUSB);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error Instantiating NavX: " + ex.getMessage(), true);
    }

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
    setDefaultCommand(new ControllerDrive());
  }

  public void execute(double power, double turn) {
		arcadeDrive(power, turn);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double power, double turn) {
		drive.arcadeDrive(power, turn);
  }

  public void zeroDriveEncoders() {
    rightFront.setSelectedSensorPosition(0, 0, 0);
    leftFront.setSelectedSensorPosition(0, 0, 0);

    System.out.println(TAG + "Zeroing Drive Encoders");
  }

  public double getPosition() {
    double avg = (rightFront.getSelectedSensorPosition(0) + leftFront.getSelectedSensorPosition(0)) / 2;
    return avg;
  }

  public double getRightPosition() {
    return rightFront.getSelectedSensorPosition(0);
  }

  public double getLeftPosition() {
    return leftFront.getSelectedSensorPosition(0);
  }

  public void stopMotors() {
    rightFront.stopMotor();
    leftFront.stopMotor();
  }
}
