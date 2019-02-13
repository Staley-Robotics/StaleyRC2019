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
 * Retrieves data from NetworkTables for Vision use
 */
public class Vision extends Subsystem {

  private static Vision instance;

  private NetworkTableInstance inst;
  private NetworkTable table;
  private NetworkTableEntry tape;
  private NetworkTableEntry tapeYaw;

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

  public double getYaw() {
    tapeYaw = table.getEntry("tapeYaw");
    return tapeYaw.getDouble(0.0);
  }
  /**
   * For PyVision to process image, tape must be true
   */
  public void setTapeTrue() {
    tape = table.getEntry("Tape");
    tape.setBoolean(true);
  }
  /**
   * In case we want PyVision to not process the image, call this
   */
  public void setTapeFalse(){
    tape = table.getEntry("Tape");
    tape.setBoolean(false);
  }

  public boolean tapeDetected() {
    return tape.getBoolean(false);
  }
}
