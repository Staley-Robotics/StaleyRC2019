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
import frc.robot.commands.drivetrain.ShifterToggle;
import frc.robot.commands.hatch.HatchExtenderToggle;
import frc.robot.commands.hatch.RunPivotMotor;
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

  private OI() {
    driveController = new XboxController(RobotMap.XBOX_DRIVE_PORT);
    altController = new XboxController(RobotMap.XBOX_ALT_PORT);

    // Toggle for shifting between high and low gear
    Button shifterToggle = new JoystickButton(driveController, XBoxButtons.kB.getvalue());
    shifterToggle.whenPressed(new ShifterToggle());

    // Button lifterToggle = new JoystickButton(driveController, XBoxButtons.kA.getvalue());
    //lifterToggle.whenPressed(new LifterToggle());

    Button hatchToggle = new JoystickButton(altController, XBoxButtons.kX.getvalue());
    hatchToggle.whenPressed(new HatchExtenderToggle());

    Button runShooter = new JoystickButton(altController, XBoxButtons.kA.getvalue());
    hatchToggle.whileHeld(new RunShooter(0.4));
  }

  public static OI getInstance() {
    if (instance == null) {
      instance = new OI();
    }
    return instance;
  }

  public double getDriveLeftY() {
    return driveController.getY(Hand.kLeft);
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

  public double getDriveLeftX() {
    return driveController.getX(Hand.kLeft);
  }

  public double getAltLeftTrigger() {
    return altController.getTriggerAxis(Hand.kLeft);
  }

  public double getAltRightTrigger() {
    return altController.getTriggerAxis(Hand.kRight);
  }

  public double getAltLeftY() {
    return altController.getY(Hand.kLeft);
  }

  public double getAltRightY() {
    return altController.getY(Hand.kRight);
  }
}
