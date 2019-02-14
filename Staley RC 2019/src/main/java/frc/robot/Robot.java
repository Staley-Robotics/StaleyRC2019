/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.commands.ResetGyro;
import frc.robot.commands.auto.commands.VisionTurning2;
import frc.robot.commands.auto.modes.AutoBrettV6;
import frc.robot.commands.auto.modes.MidToFrontCargoLeft;
import frc.robot.commands.auto.modes.MidToFrontCargoRight;
import frc.robot.commands.drivetrain.EncoderDrive;
import frc.robot.commands.drivetrain.ResetEncoders;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private OI oi;
  private DriveTrain driveTrain;
  private Command autonomousCommand;
  private SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   * 
   * Tyler sux at building
   */
  @Override
  public void robotInit() {

    oi = OI.getInstance();
    driveTrain = DriveTrain.getInstance();

    // chooser.setDefaultOption("Delay", new Delay(5));
    chooser.setDefaultOption("Mid front cargo left", new MidToFrontCargoLeft());
    chooser.addOption("Mid front cargo right", new MidToFrontCargoRight());
    chooser.addOption("AutoBrett", new AutoBrettV6());
    // chooser.addOption("Turn 90", new GyroTurning(90));
    // chooser.addOption("Sicko Mode", new SickoMode());
    chooser.addOption("Vision Turn", new VisionTurning2());
    // chooser.addOption("Reset Encoders", new ResetEncoders());
    // chooser.addOption("Drive 100 inches", new DriveTurn(100, 0.8, 0));
    // chooser.addOption("Drive -10 inches", new DriveTurn(10, -0.3, 0));
    // chooser.addOption("Drive 108 inches", new DriveTurn(108, 0.8, 0));
    // chooser.addOption("Follow Path", new FollowPath());
    chooser.addOption("Encoder drive 600 ", new EncoderDrive(600, 800));
    SmartDashboard.putData("Auto mode", chooser);

    SmartDashboard.putData(new ResetGyro());
    SmartDashboard.putData(new ResetEncoders());

    driveTrain.shiftLowGear();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Gyro angle", driveTrain.getYaw());
    SmartDashboard.putNumber("Right Encoder", driveTrain.getRightPosition());
    SmartDashboard.putNumber("Left Encoder", driveTrain.getLeftPosition());
    SmartDashboard.putNumber("Right encoder inches", driveTrain.pulsesToInches(driveTrain.getRightPosition()));
    SmartDashboard.putNumber("Left encoder inches", driveTrain.pulsesToInches(driveTrain.getLeftPosition()));
    SmartDashboard.putString("Shifter State", DriveTrain.gearState.toString());

    Vision.getInstance().setTape(true);
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }
  /**
   * Disables robot periodic 
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }
  /**
   * Insures no autonomous command is running when teleop is initialized
   */
  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
