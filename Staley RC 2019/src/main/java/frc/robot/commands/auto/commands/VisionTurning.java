/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.Vision;

//addSequential does not like a command that calls another command.
//Use VisionTurning2 for autonomous turning, or VisionTurning2 in general.

/**
 * VisionTurning works better than VisionTurning2 except in auto (where
 * VisionTurning doesn't work) Uses GyroTurning to turn the robot toward vision
 * target until gyro yaw is zero, cannot be used in auto mode
 */
public class VisionTurning extends TimedCommand {

    private Vision vision;
    private double offset;

    private GyroTurning turnMyGuy;

    public VisionTurning() {
        super(2);
        vision = Vision.getInstance();
        requires(vision);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // set networktable tape to True
        vision.setTape(true);

        offset = vision.getYaw();

        turnMyGuy = new GyroTurning(offset);
        turnMyGuy.start();
        System.out.println("Starting Vision turn with a target of " + offset);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        System.out.println("Running");
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        try {
            return turnMyGuy.isFinished();
        } catch (Exception e) {
            return true;
        }
    }

    /*
     * Called once after isFinished returns true Now that it is a timed command,
     * end() is called when the timer ends
     */
    @Override
    protected void end() {
        System.out.println("Done Vision Turning!");
        try {
            turnMyGuy.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // set networktable tape to false
        vision.setTape(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}