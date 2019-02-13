/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.DriveStraight;

/**
 * Drives straight forward to pass auto line
 */
public class AutoBrettV6 extends CommandGroup {

    public AutoBrettV6() {
        addSequential(new DriveStraight(150));
    }
}