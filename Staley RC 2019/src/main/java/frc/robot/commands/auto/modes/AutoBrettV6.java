package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.DriveStraight;

public class AutoBrettV6 extends CommandGroup {

    public AutoBrettV6() {
        addSequential(new DriveStraight(150));
    }
}