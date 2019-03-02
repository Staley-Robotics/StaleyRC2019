/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;

/**
 * Controls the power to the drivetrain with input from a controller. This
 * command is always active.
 */
public class ControllerDrive extends Command {

    private DriveTrain driveTrain;

    public ControllerDrive() {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        double forwardPower = OI.getInstance().getDriveRightTrigger();
        double reversePower = OI.getInstance().getDriveLeftTrigger();
        double turnPower = OI.getInstance().getDriveLeftX();

        // double leftPower = OI.getInstance().getDriveLeftY();
        // double rightPower = OI.getInstance().getDriveRightY();

        // driveTrain.tankDrive(leftPower, rightPower);
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
        driveTrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}