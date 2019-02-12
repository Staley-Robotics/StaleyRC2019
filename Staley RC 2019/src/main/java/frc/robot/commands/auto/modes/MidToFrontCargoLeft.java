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
import frc.robot.commands.hatch.RunPivotMotorAuto;

public class MidToFrontCargoLeft extends CommandGroup {
  /**
   * Add your docs here.
   */
  public MidToFrontCargoLeft() {
    // Drive off ramp
    addSequential(new DriveTurn(50, 0.7, 0));

    // slight veer left
    addSequential(new DriveTurn(11.5, 0.7, -0.77));

    addSequential(new DriveTurn(18, 0.7, 0.7));

    // drive straight a bit (about halfway)
    addSequential(new Delay(0.1));
    addSequential(new VisionTurning2(), 3);

    addSequential(new DriveTurn(20, 0.7, 0));
    addSequential(new VisionTurning2(), 3);
    addSequential(new DriveTurn(25, 0.7, 0));

    // addSequential(new RunPivotMotorAuto(0.5,0),0.5);

    // Corrects itself one more time
    // addSequential(new Delay(0.2));
    // addSequential(new VisionTurning2());

    // Drives the rest of the way towards the cargo ship
    // addSequential(new DriveTurn(30,0.8,0.00)); // not correct distance
    // addSequential(new DriveTurn(5,0.5,0));

    // Vision turn
    // vision turn temporary replacement
    // addSequential(new DriveTurn(10,0.8,));
    // Drive the rest of the way to the cargo bay

    // place the hatch somehow

    // win
  }
}
