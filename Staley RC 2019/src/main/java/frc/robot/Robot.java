/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Random;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drivetrain.ControllerDrive;
import frc.robot.commands.drivetrain.ResetEncoders;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;
import frc.robot.commands.auto.commands.Delay;
import frc.robot.commands.auto.commands.DriveStraight;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.auto.commands.GyroTurning;
import frc.robot.commands.auto.commands.ResetGyro;
import frc.robot.commands.auto.commands.VisionTurning;
import frc.robot.commands.auto.modes.AutoBrettV6;
import frc.robot.commands.auto.modes.DriveTurnMode;
import frc.robot.commands.auto.modes.SickoMode;
import frc.robot.commands.auto.modes.VisionTurnTest;
import frc.robot.commands.PutNewtorkTableValues;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Random gen = new Random();
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

    chooser.setDefaultOption("Default Auto", new ControllerDrive());
    chooser.setDefaultOption("Delay", new Delay(5));
    chooser.addOption("AutoBrett", new AutoBrettV6());
    chooser.addOption("Drive Turn", new DriveTurnMode());
    chooser.addOption("Turn 90", new GyroTurning(90));
    chooser.addOption("Sicko Mode", new SickoMode());
    chooser.addOption("Turn -90", new GyroTurning(-90));
    chooser.addOption("Turn -45", new GyroTurning(-45));
    chooser.addOption("Vision Turn", new VisionTurnTest());
    chooser.addOption("Reset Encoders", new ResetEncoders());
    chooser.addOption("Drive 5 inches", new DriveTurn(5, 0.8, 0));
    chooser.addOption("Drive -10 inches", new DriveTurn(-10, 0.8, 0));
    chooser.addOption("Drive 20 inches", new DriveTurn(20, 0.6, 0));
    chooser.addOption("Drive -20 inches", new DriveTurn(-20, 0.6, 0));
    chooser.addOption("Drive 30 inches", new DriveTurn(30, 0.6, 0));
    chooser.addOption("Drive -30 inches", new DriveTurn(-30, 0.6, 0));
    chooser.addOption("Drive 48 inches", new DriveTurn(48, 0.6, 0));

    SmartDashboard.putData("Auto mode", chooser);
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
    SmartDashboard.putData(new ResetGyro());
    SmartDashboard.putData(new ResetEncoders());
    SmartDashboard.putNumber("Right encoder inches", driveTrain.pulsesToInches(driveTrain.getRightPosition()));
    SmartDashboard.putNumber("Left encoder inches", driveTrain.pulsesToInches(driveTrain.getLeftPosition()));
    driveTrain.putCrap();
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

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

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

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

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
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
