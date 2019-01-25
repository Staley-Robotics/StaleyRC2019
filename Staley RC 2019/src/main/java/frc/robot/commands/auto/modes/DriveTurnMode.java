package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.auto.commands.GyroTurning;

public class DriveTurnMode extends CommandGroup {
    
    public DriveTurnMode() {
        // addSequential(new GyroTurning(-90), 5);
        addSequential(new DriveTurn(40, 0.6, 0));
    }
}