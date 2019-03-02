/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter.throughput;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.Delay;

public class ShootSequence extends CommandGroup {

  /**
   * Shooting sequence.
   */
  public ShootSequence() {
    // Run shooting motors for X seconds
    addParallel(new RunShooterTime(4));
    // Wait Y seconds for the motors to get to full speed
    addSequential(new Delay(1.5));
    // Extend shooter piston
    addSequential(new ExtendShooterPiston());
    // Little delay
    addSequential(new Delay(0.5));
    // Retract shooter piston
    addSequential(new RetractShooterPiston());

  }
}
