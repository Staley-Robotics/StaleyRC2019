/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.Delay;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.auto.commands.VisionTurning2;

/**
 * Drives robot from middle of Level 1 to right side of the front cargo
 */
public class MidToFrontCargoRight extends CommandGroup {

  public MidToFrontCargoRight() {
    // Drive off ramp
    addSequential(new DriveTurn(50, 0.7, 0));

    // slight veer left
    addSequential(new DriveTurn(11, 0.7, 0.8));

    addSequential(new DriveTurn(16, 0.7, -0.74));
    addSequential(new DriveTurn(1, 0.8, 0));

    // drive straight halfway to cargo
    addSequential(new Delay(0.3));
    addSequential(new VisionTurning2());

    addSequential(new DriveTurn(25, 0.7, 0));
    addSequential(new VisionTurning2(), 3);
    addSequential(new DriveTurn(25, 0.7, 0));

  }
}
