/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.RunCompressor;
import frc.robot.commands.ToggleCompressor;
import frc.robot.commands.auto.commands.VisionTurning;
import frc.robot.commands.climber.FrontLifterToggle;
import frc.robot.commands.climber.RearLifterToggle;
import frc.robot.commands.drivetrain.ShifterToggle;
import frc.robot.commands.hatch.HatchExtenderToggle;
import frc.robot.commands.shooter.pivot.SetPivotPosition;
import frc.robot.commands.shooter.throughput.RunShooter;
import frc.robot.commands.shooter.throughput.ShootSequence;
import frc.robot.enums.PivotTarget;
import frc.robot.enums.XBoxButtons;
import frc.robot.util.DPadButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@SuppressWarnings("resource")
public class OI {
  private static OI instance;

  private XboxController driveController;
  private XboxController altController;

  // Commands used to set the targetAngle of the shooter pivot
  private final Command setPivotGround;
  private final Command setPivotLow;
  private final Command setPivotMid;
  private final Command setPivotHigh;
  private final Command setPivotCargo;

  /**
   * Sets driveController and altController. Sets the functions of each button for
   * both controllers
   */
  private OI() {
    driveController = new XboxController(RobotMap.XBOX_DRIVE_PORT);
    altController = new XboxController(RobotMap.XBOX_ALT_PORT);

    setPivotGround = new SetPivotPosition(PivotTarget.GROUND);
    setPivotLow = new SetPivotPosition(PivotTarget.LOW);
    setPivotMid = new SetPivotPosition(PivotTarget.MID);
    setPivotHigh = new SetPivotPosition(PivotTarget.HIGH);
    setPivotCargo = new SetPivotPosition(PivotTarget.CARGO);

    // ***** Drive Controller Buttons *****

    // Toggle for shifting between high and low gear
    Button shifterToggle = new JoystickButton(driveController, XBoxButtons.kB.getValue());
    shifterToggle.whenPressed(new ShifterToggle());

    // Toggle for switching between climbing mode and driving mode
    // Button shiftToClimberToggle = new JoystickButton(driveController,
    // XBoxButtons.kX.getValue());
    // shiftToClimberToggle.whenPressed(new ShiftToClimberToggle());

    // Toggle for extending and retracting the rear lifter pistons
    Button rearLifterToggle = new JoystickButton(driveController, XBoxButtons.kA.getValue());
    rearLifterToggle.whenPressed(new RearLifterToggle());

    /*
     * Does rearLifter pistons with Dpad up rather than button A Button
     * rearLifterToggle = new DPadButton(driveController, Direction.Down);
     * rearLifterToggle.whenPressed(new RearLifterToggle());
     */

    // Toggle for extending and retracting the front lifter pistons
    Button frontLifterToggle = new JoystickButton(driveController, XBoxButtons.kY.getValue());
    frontLifterToggle.whenPressed(new FrontLifterToggle());

    /*
     * Does frontLifter pistons with Dpad dwon rather than button Y Button
     * frontLifterToggle = new DPadButton(driveController, Direction.Up);
     * frontLifterToggle.whenPressed(new FrontLifterToggle());
     */

    Button turnToTape = new JoystickButton(driveController, XBoxButtons.kBumperLeft.getValue());
    turnToTape.whenPressed(new VisionTurning());

    // ***** Alt Controller Buttons *****

    Button setGround = new JoystickButton(altController, XBoxButtons.kStart.getValue());
    setGround.whenPressed(setPivotGround);
    setGround.cancelWhenPressed(setPivotLow);
    setGround.cancelWhenPressed(setPivotMid);
    setGround.cancelWhenPressed(setPivotHigh);
    setGround.cancelWhenPressed(setPivotCargo);

    Button setLow = new DPadButton(altController, DPadButton.Direction.Down);
    setLow.cancelWhenPressed(setPivotGround);
    setLow.whenPressed(setPivotLow);
    setLow.cancelWhenPressed(setPivotMid);
    setLow.cancelWhenPressed(setPivotHigh);
    setLow.cancelWhenPressed(setPivotCargo);

    Button setMid = new DPadButton(altController, DPadButton.Direction.Right);
    setMid.cancelWhenPressed(setPivotGround);
    setMid.cancelWhenPressed(setPivotLow);
    setMid.whenPressed(setPivotMid);
    setMid.cancelWhenPressed(setPivotHigh);
    setMid.cancelWhenPressed(setPivotCargo);

    Button setHigh = new DPadButton(altController, DPadButton.Direction.Up);
    setHigh.cancelWhenPressed(setPivotGround);
    setHigh.cancelWhenPressed(setPivotLow);
    setHigh.cancelWhenPressed(setPivotMid);
    setHigh.whenPressed(setPivotHigh);
    setHigh.cancelWhenPressed(setPivotCargo);

    Button setCargo = new DPadButton(altController, DPadButton.Direction.Left);
    setCargo.cancelWhenPressed(setPivotGround);
    setCargo.cancelWhenPressed(setPivotLow);
    setCargo.cancelWhenPressed(setPivotMid);
    setCargo.cancelWhenPressed(setPivotHigh);
    setCargo.whenPressed(setPivotCargo);

    // Toggle for extending and retracting hatch placer
    Button hatchToggle = new JoystickButton(altController, XBoxButtons.kX.getValue());
    hatchToggle.whenPressed(new HatchExtenderToggle());

    Button runIntake = new JoystickButton(altController, XBoxButtons.kA.getValue());
    runIntake.whileHeld(new RunShooter(-0.25));

    Button runShooter = new JoystickButton(altController, XBoxButtons.kB.getValue());
    runShooter.whenPressed(new ShootSequence());

    // Button runOutput = new JoystickButton(altController,
    // XBoxButtons.kB.getValue());
    // runOutput.whileHeld(new RunShooter(0.4));

    Button runCompressor = new JoystickButton(altController, XBoxButtons.kY.getValue());
    runCompressor.whenPressed(new ToggleCompressor());
  }

  public static OI getInstance() {
    if (instance == null) {
      instance = new OI();
    }
    return instance;
  }

  // ***** Getters for X-Box controller(s) joystick axis and trigger values *****

  // Drive controller

  public double getDriveLeftX() {
    return driveController.getX(Hand.kLeft);
  }

  public double getDriveLeftY() {
    return driveController.getY(Hand.kLeft);
  }

  public double getDriveRightX() {
    return driveController.getX(Hand.kRight);
  }

  public double getDriveRightY() {
    return driveController.getY(Hand.kRight);
  }

  public double getDriveLeftTrigger() {
    return driveController.getTriggerAxis(Hand.kLeft);
  }

  public double getDriveRightTrigger() {
    return driveController.getTriggerAxis(Hand.kRight);
  }

  public boolean getDriveLeftBumper() {
    return driveController.getBumper(Hand.kLeft);
  }

  public boolean getDriveRightBumper() {
    return driveController.getBumper(Hand.kRight);
  }

  public int getDriveDPad() {
    return driveController.getPOV();
  }

  // Alternate controller

  public double getAltLeftX() {
    return altController.getX(Hand.kLeft);
  }

  public double getAltLeftY() {
    return altController.getY(Hand.kLeft);
  }

  public double getAltRightX() {
    return altController.getX(Hand.kRight);
  }

  public double getAltRightY() {
    return altController.getY(Hand.kRight);
  }

  public double getAltLeftTrigger() {
    return altController.getTriggerAxis(Hand.kLeft);
  }

  public double getAltRightTrigger() {
    return altController.getTriggerAxis(Hand.kRight);
  }

  public boolean getAltLeftBumper() {
    return altController.getBumper(Hand.kLeft);
  }

  public boolean getAltRightBumper() {
    return altController.getBumper(Hand.kRight);
  }

  public int getAltDPad() {
    return altController.getPOV();
  }
}
