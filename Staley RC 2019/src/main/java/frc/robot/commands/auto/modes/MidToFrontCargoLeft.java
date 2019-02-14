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
 * Drives robot from middle of Level 1 to left side of the front cargo
 */
public class MidToFrontCargoLeft extends CommandGroup {

  public MidToFrontCargoLeft() {
    // Drive off ramp
    addSequential(new DriveTurn(50, 0.7, 0));

    // slight veer left
    addSequential(new DriveTurn(11.5, 0.7, -0.77));

    addSequential(new DriveTurn(18, 0.7, 0.7));

    // drive straight halfway to cargo
    addSequential(new Delay(0.1));
    addSequential(new VisionTurning2(), 3);

    addSequential(new DriveTurn(20, 0.7, 0));
    addSequential(new VisionTurning2(), 3);
    addSequential(new DriveTurn(25, 0.7, 0));

    // addSequential(new RunPivotMotorAuto(0.5,0),0.5);

  }
}
