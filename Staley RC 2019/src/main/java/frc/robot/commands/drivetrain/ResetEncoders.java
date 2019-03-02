/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

/**
 * Sets drive encoder values to zero.
 */
public class ResetEncoders extends Command {

    private DriveTrain driveTrain;

    public ResetEncoders() {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    @Override
    protected void execute() {
        driveTrain.zeroDriveEncoders();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}