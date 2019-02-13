package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Delay for given time
 */
public class Delay extends TimedCommand {

    public Delay(double seconds) {
        super(seconds);
    }
}