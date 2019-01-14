package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;

public class ControllerDrive extends Command {

    private DriveTrain driveTrain;

    public ControllerDrive() {
        requires(DriveTrain.getInstance());
        driveTrain = DriveTrain.getInstance();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        double leftPower = OI.getInstance().getDriveLeftY();
        double rightPower = OI.getInstance().getDriveRightY();

        driveTrain.tankDrive(leftPower, rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        driveTrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}