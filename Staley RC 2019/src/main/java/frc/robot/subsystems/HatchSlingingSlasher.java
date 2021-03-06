/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.hatch.RunPivotMotor;

/**
 * Hatch placing and collecting mechanism that uses one piston to push the hatch
 * off
 */
public class HatchSlingingSlasher extends Subsystem {

  private static HatchSlingingSlasher instance;

  private Talon pivotMotor;
  private Solenoid hatchRelease;

  private HatchSlingingSlasher() {
    pivotMotor = new Talon(RobotMap.HATCH_PIVOT_MOTOR_PORT);
    hatchRelease = new Solenoid(RobotMap.HATCH_SOLENOID_PORT);
  }

  public static HatchSlingingSlasher getInstance() {
    if (instance == null) {
      instance = new HatchSlingingSlasher();
    }
    return instance;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new RunPivotMotor());
  }

  public void extend() {
    hatchRelease.set(true);
  }

  public void retract() {
    hatchRelease.set(false);
  }

  public void runPivotMotor(double power) {
    pivotMotor.set(power);
  }
}