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

/**
 * Handles everything vision related on the robot.
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
  }

  public double getYaw() {
    tapeYaw = table.getEntry("tapeYaw");
    return tapeYaw.getDouble(0.0);
  }

  /**
   * If trackTape is true, vision track. If it is false, the camera stream will
   * not be affected.
   * 
   * @param trackTape Weather or not to track the tape using the pi
   */
  public void setTape(boolean trackTape) {
    tape = table.getEntry("Tape");
    tape.setBoolean(trackTape);
  }

  public boolean tapeDetected() {
    return tape.getBoolean(false);
  }
}
