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
import frc.robot.commands.drivetrain.ControllerDrive;
import frc.robot.commands.drivetrain.ResetEncoders;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.auto.commands.Delay;
import frc.robot.commands.auto.commands.DriveStraight;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.auto.commands.GyroTurning;
import frc.robot.commands.auto.commands.ResetGyro;
import frc.robot.commands.auto.modes.AutoBrettV6;
import frc.robot.commands.auto.modes.DriveTurnMode;;

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

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = OI.getInstance();
    driveTrain = DriveTrain.getInstance();

    m_chooser.setDefaultOption("Default Auto", new ControllerDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    m_chooser.setDefaultOption("Delay", new Delay(5));
    m_chooser.addOption("AutoBrett", new AutoBrettV6());
    m_chooser.addOption("Turn", new DriveTurnMode());
    m_chooser.addOption("Reset Encoders", new ResetEncoders());
    m_chooser.addOption("Drive 10 inches", new DriveTurn(10, 0.4, 0));
    m_chooser.addOption("Drive -10 inches", new DriveTurn(-10, 0.4, 0));
    m_chooser.addOption("Drive 20 inches", new DriveTurn(20, 0.6, 0));
    m_chooser.addOption("Drive -20 inches", new DriveTurn(-20, 0.6, 0));
    m_chooser.addOption("Drive 30 inches", new DriveTurn(30, 0.6, 0));
    m_chooser.addOption("Drive -30 inches", new DriveTurn(-30, 0.6, 0));
    m_chooser.addOption("Drive 48 inches", new DriveTurn(48, 0.6, 0));

    SmartDashboard.putData("Auto mode", m_chooser);
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
    
    // SmartDashboard.putRaw(driveTrain.gearState);
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
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
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
