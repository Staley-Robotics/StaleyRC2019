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

public class MidToFrontCargoRight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public MidToFrontCargoRight() {
    // Drive off ramp
    addSequential(new DriveTurn(50, 0.7, 0));

    // slight veer left
    addSequential(new DriveTurn(11, 0.7, 0.8));
    //24
    addSequential(new DriveTurn(16, 0.7, -0.74));
    addSequential(new DriveTurn(1,0.8,0));

    addSequential(new Delay(0.3));
    addSequential(new VisionTurning2());

    System.out.println("Start Delay");
    addSequential(new Delay(0.1));

    // System.out.println("Start DriveTurn");
    // addSequential(new DriveTurn(50,0.8,0));

    addSequential(new DriveTurn(25,0.7,0));
    addSequential(new VisionTurning2(), 3);
    addSequential(new DriveTurn(25, 0.7, 0));

    System.out.println("Start DriveTurn 2");
    addSequential(new DriveTurn(0,0.5,0));
    // Vision turn
    // vision turn temporary replacement
    // addSequential(new DriveTurn(10,0.8,));
    // Drive the rest of the way to the cargo bay

    // place the hatch somehow

    // win
  }
}
