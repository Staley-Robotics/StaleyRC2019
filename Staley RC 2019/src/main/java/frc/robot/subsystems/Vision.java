/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.PutNewtorkTableValues;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {

  private static Vision instance;

  private NetworkTableInstance inst;
  private NetworkTable table;
  private NetworkTableEntry tape;

  private Vision() {
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("Vision Info");
  }

  public static Vision getInstance() {
    if (instance == null) {
      instance = new Vision();
    }

    return instance;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new PutNewtorkTableValues());
  }

  public void setTapeTrue() {
    tape = table.getEntry("Tape");
    tape.setBoolean(true);
  }
}
