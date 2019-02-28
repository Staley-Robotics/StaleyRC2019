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
  private Solenoid shiftToClimber;

  // Solenoid ports need to be changed;:,
  // ports are defined in robot map
  private Climber() {
    frontLifter = new DoubleSolenoid(RobotMap.CLIMBER_FRONT_LIFTER_SOLENOID_PORT1, RobotMap.CLIMBER_FRONT_LIFTER_SOLENOID_PORT2);
    rearLifter = new DoubleSolenoid(RobotMap.CLIMBER_REAR_LIFTER_SOLENOID_PORT1, RobotMap.CLIMBER_REAR_LIFTER_SOLENOID_PORT2);
    shiftToClimber = new Solenoid(RobotMap.CLIMBER_SHIFT_TO_CLIMB_SOLENOID_PORT);
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

  /**
   * Shifts to lifting mode
   */
  public void shiftToClimber() {
    shiftToClimber.set(false);
  }

  /**
   * Shifts control back to the drivetrain
   */
  public void shiftToDrive() {
    shiftToClimber.set(true);
  }
}
