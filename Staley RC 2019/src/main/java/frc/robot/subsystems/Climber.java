/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem uses the Drivetrain motors to lift the robot up onto the platforms
 */
public class Climber extends Subsystem {

  private static Climber instance;

  private DoubleSolenoid frontLifter;
  private DoubleSolenoid rearLifter;
  private DoubleSolenoid shiftToClimber;

  // Solenoid ports need to be changed;:,
  // ports are defined in robot map
  private Climber() {
    frontLifter = new DoubleSolenoid(2, 3);
    rearLifter = new DoubleSolenoid(3, 4);
    shiftToClimber = new DoubleSolenoid(4, 5);
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
    shiftToClimber.set(Value.kForward);
  }

  public void shiftToDrive() {
    shiftToClimber.set(Value.kReverse);
  }
}
