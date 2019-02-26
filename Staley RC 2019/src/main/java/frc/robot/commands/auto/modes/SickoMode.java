// DOES NOT WORK
package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.drivetrain.EncoderDrive2;

/**
 * we're keepomg
 */
public class SickoMode extends CommandGroup {

    public SickoMode() {
        // addSequential(new GyroTurning(-90), 5);
        //MoBamba();
        addSequential(new EncoderDrive2(3000,3500));
        addSequential(new EncoderDrive2(3500,3000));
    }
    //sicko mode uses encoder turn
    
}