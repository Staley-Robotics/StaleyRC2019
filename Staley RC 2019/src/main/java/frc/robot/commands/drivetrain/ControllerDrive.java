package frc.robot.commands.drivetrain;

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

        // double leftPower = -1 * OI.getInstance().getDriveLeftY();
        // double rightPower = -1 * OI.getInstance().getDriveRightY();

        // System.out.println("Left Stick: " + leftPower + "\tRight Power: " + rightPower);

        // driveTrain.tankDrive(leftPower, rightPower);

        double forwardPower = OI.getInstance().getDriveRightTrigger();
        double reversePower = OI.getInstance().getDriveLeftTrigger();
        double turnPower = OI.getInstance().getDriveLeftX();

        driveTrain.worldOfTanksDrive(forwardPower, reversePower, turnPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}