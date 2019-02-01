package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.VisionTurning;

public class VisionTurnTest extends CommandGroup {

    public VisionTurnTest() {
        // addSequential(new GyroTurning(-90), 5);
        addSequential(new VisionTurning());
    }
}