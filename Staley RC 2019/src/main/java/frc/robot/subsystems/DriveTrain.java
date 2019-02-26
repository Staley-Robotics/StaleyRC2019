/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ControllerDrive;
import frc.robot.enums.GearStates;
import frc.robot.util.SpeedControllerFactory;

/**
 * Controls the basic drive functionality of the robot
 */
public class DriveTrain extends Subsystem {

  private final String TAG = (this.getName() + ": ");

  // number of inches per pulse for encoders
  // private final double distancePerPulse = 0.0024543693; // (1/4096) * 8 *
  // Math.PI * 0.4
  private final double distancePerPulse = 0.002377670221223; // (1/4096) * 7.75 * Math.PI * 0.4

  private static DriveTrain instance;

  private AHRS navx;

  // whether speed controllers in brake mode or coast mode
  private boolean brakeMaster;
  private boolean brakeFollower;

  private WPI_TalonSRX rightMaster;
  private WPI_VictorSPX rightFollower;

  private WPI_TalonSRX leftMaster;
  private WPI_VictorSPX leftFollower;

  private DifferentialDrive drive;

  // tracks whether shifter is in low or high gear
  public static GearStates gearState;

  private DoubleSolenoid shifter;


  private DriveTrain() {

    // initialize gyro
    try {
      navx = new AHRS(I2C.Port.kMXP);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error Instantiating NavX: " + ex.getMessage(), true);
    }

    brakeMaster = false;
    brakeFollower = false;

    rightMaster = SpeedControllerFactory.createMasterSrx(RobotMap.RIGHT_MASTER_DRIVE_CAN_ID, false, brakeMaster);
    rightFollower = SpeedControllerFactory.createFollowerSpx(RobotMap.RIGHT_FOLLOWER_DRIVE_CAN_ID, rightMaster,
        brakeFollower);
    rightMaster.setInverted(false);
    rightFollower.setInverted(true);

    leftMaster = SpeedControllerFactory.createMasterSrx(RobotMap.LEFT_MASTER_DRIVE_CAN_ID, false, brakeMaster);
    leftFollower = SpeedControllerFactory.createFollowerSpx(RobotMap.LEFT_FOLLOWER_DRIVE_CAN_ID, leftMaster,
        brakeFollower);
    leftMaster.setInverted(false);
    leftFollower.setInverted(false);

    drive = new DifferentialDrive(leftMaster, rightMaster);
    drive.setSafetyEnabled(false);
    drive.setRightSideInverted(false);

    shifter = new DoubleSolenoid(RobotMap.DRIVE_SHIFTER_SOLENOID_PORT1, RobotMap.DRIVE_SHIFTER_SOLENOID_PORT2);

    zeroDriveEncoders();
  }

  public static DriveTrain getInstance() {
    if (instance == null) {
      instance = new DriveTrain();
    }
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ControllerDrive());
  }

  // ***** Gyro *****

  public AHRS getNavx() {
    return navx;
  }

  public void resetNavx() {
    navx.reset();
  }

  /**
   * yaw is the axis we use from the gyro
   */
  public double getYaw() {
    return navx.getYaw();
  }

  public void resetYaw() {
    navx.zeroYaw();
  }

  // ***** Drive Modes *****

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double power, double turn) {
    drive.arcadeDrive(power, turn);
  }

  /**
   * right trigger used to go forward, left trigger used to reverse, left joystick
   * x-axis used to turn
   * 
   * @param forward  speed for moving forward
   * @param backward speed for moving backward
   * @param rotate   turning speed
   */
  public void worldOfTanksDrive(double forward, double backward, double rotate) {
    double speedModifier = 1;
    double turnSpeedModifier = 0.85;

    backward = backward * speedModifier;
    forward = forward * speedModifier;
    if (rotate > 0.1 || rotate < 0.1) {
      rotate = rotate * turnSpeedModifier;
    } else {
      rotate = 0;
    }

    if (backward > 0) {
      drive.arcadeDrive(-backward, rotate);
    } else if (forward > 0) {
      drive.arcadeDrive(forward, rotate);
    } else {
      drive.arcadeDrive(0, rotate);
    }
  }

  public void stopMotors() {
    rightMaster.stopMotor();
    leftMaster.stopMotor();
  }

  // ***** Encoders *****

  public void zeroDriveEncoders() {
    rightMaster.setSelectedSensorPosition(0, 0, 10);
    leftMaster.setSelectedSensorPosition(0, 0, 10);

    System.out.println(TAG + "Zeroing Drive Encoders");
  }

  /**
   * @return average position of left and right encoders
   */
  public double getPosition() {
    double avg = (rightMaster.getSelectedSensorPosition(0) + leftMaster.getSelectedSensorPosition(0)) / 2;
    return avg;
  }

  public double getRightPosition() {
    return rightMaster.getSelectedSensorPosition(0);
  }

  public double getLeftPosition() {
    return leftMaster.getSelectedSensorPosition(0);
  }

  public void setTarget(double inches) {
    double target = inchesToPulses(inches);
    System.out.println("Target in pulses: " + target);

    leftMaster.set(ControlMode.Position, target);
    rightMaster.set(ControlMode.Position, target);
  }

  /**
   * The encoder measures distance in pulses, this converts inches to pulses to be
   * used as a setpoint
   * 
   * @param inches distance setpoint
   * @return number of encoder pulses to setpoint
   */
  public double inchesToPulses(double inches) {
    return (inches / distancePerPulse);
  }

  /**
   * @param pulses value recorded by encoder
   * @return pulses converted to inches
   */
  public double pulsesToInches(double pulses) {
    return (pulses * distancePerPulse);
  }

  // ***** Shifter *****

  /**
   * Shifts into high gear
   */
  public void shiftHighGear() {
    shifter.set(Value.kReverse);
    gearState = GearStates.HIGH_GEAR;
  }

  /**
   * Shifts into low gear
   */
  public void shiftLowGear() {
    shifter.set(Value.kForward);
    gearState = GearStates.LOW_GEAR;
  }
}
