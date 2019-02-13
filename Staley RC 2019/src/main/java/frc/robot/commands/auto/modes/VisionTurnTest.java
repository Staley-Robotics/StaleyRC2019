/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.VisionTurning;

public class VisionTurnTest extends CommandGroup {

    public VisionTurnTest() {
        // addSequential(new GyroTurning(-90), 5);
        addSequential(new VisionTurning());
    }
}