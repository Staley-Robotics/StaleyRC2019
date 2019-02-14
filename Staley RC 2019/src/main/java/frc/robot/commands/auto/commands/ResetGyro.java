/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

/**
 * Sets gyro yaw to zero
 */
public class ResetGyro extends Command {

    private DriveTrain driveTrain;

    public ResetGyro() {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    @Override
    protected void execute() {
        driveTrain.resetYaw();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}