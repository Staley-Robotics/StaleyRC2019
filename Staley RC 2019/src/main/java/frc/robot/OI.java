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
import frc.robot.commands.auto.commands.VisionTurning;
import frc.robot.commands.drivetrain.ShifterToggle;
import frc.robot.commands.hatch.HatchExtenderToggle;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.enums.XBoxButtons;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static OI instance;

  private XboxController driveController;
  private XboxController altController;

  /**
   * Sets driveController and altController. Sets the functions of each button for both controllers
   */
  private OI() {
    //Drive controller
    driveController = new XboxController(RobotMap.XBOX_DRIVE_PORT);
    //Alt controller
    altController = new XboxController(RobotMap.XBOX_ALT_PORT);

    // Toggle for shifting between high and low gear
    Button shifterToggle = new JoystickButton(driveController, XBoxButtons.kB.getValue());
    shifterToggle.whenPressed(new ShifterToggle());

    // Toggle for switching between climbing mode and driving mode
    Button shiftToClimberToggle = new JoystickButton(driveController, XBoxButtons.kX.getValue());
    // shiftToClimberToggle.whenPressed(new ShiftToClimberToggle());

    // Toggle for extending and retracting the rear lifter piston
    Button rearLifterToggle = new JoystickButton(driveController, XBoxButtons.kY.getValue());
    // rearLifterToggle.whenPressed(new RearLifterToggle());

    // Toggle for extending and retracting the front lifter piston
    Button frontLifterToggle = new JoystickButton(driveController, XBoxButtons.kA.getValue());
    // frontLifterToggle.whenPressed(new FrontLifterToggle());

    // Toggle for extending and retracting hatch placer
    Button hatchToggle = new JoystickButton(altController, XBoxButtons.kX.getValue());
    hatchToggle.whenPressed(new HatchExtenderToggle());

    Button runShooter = new JoystickButton(altController, XBoxButtons.kA.getValue());
    hatchToggle.whileHeld(new RunShooter(0.4));

    Button setPivotLow = new JoystickButton(altController, XBoxButtons.kUp.getValue());
    // What thi supposed be setPivotLow.whenPressed(new Se);
    
    Button turnToTape = new JoystickButton(driveController, XBoxButtons.kBumperRight.getValue());
    turnToTape.whenPressed(new VisionTurning());

    Button pivotUp = new JoystickButton(altController, XBoxButtons.kBumperRight.getValue());
    
    Button pivotDown = new JoystickButton(altController, XBoxButtons.kBumperLeft.getValue());
  }

  /**
   * Ensures that an instance of OI is received
   * @return the instance of OI
   */
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
}
