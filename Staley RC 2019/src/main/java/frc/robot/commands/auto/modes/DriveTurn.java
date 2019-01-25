package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.GyroTurning;

public class DriveTurn extends CommandGroup {
    
    public DriveTurn() {
        addSequential(new GyroTurning(-90), 5);
    }
}