package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Vision;
import frc.robot.commands.auto.commands.GyroTurning;

public class VisionTurning extends Command {

    private Vision vision;
    private double offset;

    private GyroTurning turnMyGuy;
    
    public VisionTurning() {
        vision = Vision.getInstance();
        requires(vision);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
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
        // try{
        // return turnMyGuy.isFinished();
        // }
        // catch(Exception Chungus){
        //     return true;
        // }
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // try{
        // turnMyGuy.end();
        // }
        // catch(Exception e){

        // }
        System.out.println("Done Vision Turning!");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}