/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RunCompressor;

/**
 * Runs compressor
 */
public class Pneumatics extends Subsystem {

    private static Pneumatics instance;
    private Compressor compressor;

    private Pneumatics() {
        compressor = new Compressor(RobotMap.COMPRESSOR_PORT);
    }

    public static Pneumatics getInstance() {
        if (instance == null) {
            instance = new Pneumatics();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunCompressor());
    }

    public void runCompressor() {
        compressor.setClosedLoopControl(true);
    }
}