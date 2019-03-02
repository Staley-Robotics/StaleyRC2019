/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This subsystem uses the Drivetrain motors to lift the robot up onto the platforms
 */
public class Climber extends Subsystem {

  private static Climber instance;

  private DoubleSolenoid frontLifter;
  private DoubleSolenoid rearLifter;

  private Climber() {
    frontLifter = new DoubleSolenoid(RobotMap.CLIMBER_FRONT_LIFTER_SOLENOID_PORT_ONE, RobotMap.CLIMBER_FRONT_LIFTER_SOLENOID_PORT_TWO);
    rearLifter = new DoubleSolenoid(RobotMap.CLIMBER_REAR_LIFTER_SOLENOID_PORT_ONE, RobotMap.CLIMBER_REAR_LIFTER_SOLENOID_PORT_TWO);
  }

  public static Climber getInstance() {
    if (instance == null) {
      instance = new Climber();
    }
    return instance;
  }

  @Override
  protected void initDefaultCommand() {
  }

  // ***** Lifters *****

  public void extendFront() {
    frontLifter.set(Value.kForward);
  }

  public void retractFront() {
    frontLifter.set(Value.kReverse);
  }

  public void extendRear() {
    rearLifter.set(Value.kForward);
  }

  public void retractRear() {
    rearLifter.set(Value.kReverse);
  }
}
