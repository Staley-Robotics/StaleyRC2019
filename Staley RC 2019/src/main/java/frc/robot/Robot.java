/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.commands.Delay;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.auto.commands.GyroTurning;
import frc.robot.commands.auto.commands.ResetGyro;
import frc.robot.commands.auto.modes.AutoBrettV6;
import frc.robot.commands.auto.modes.SickoMode;
import frc.robot.commands.auto.modes.VisionTurnTest;
import frc.robot.commands.drivetrain.ResetEncoders;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

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
  private EncoderFollower leftFollower;
  private EncoderFollower rightFollower;

  private Notifier followerNotifier;

  private Trajectory leftTrajectory;
  private Trajectory rightTrajectory;

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

    chooser.setDefaultOption("Delay", new Delay(5));
    chooser.addOption("AutoBrett", new AutoBrettV6());
    chooser.addOption("Turn 90", new GyroTurning(90));
    chooser.addOption("Sicko Mode", new SickoMode());
    chooser.addOption("Vision Turn", new VisionTurnTest());
    chooser.addOption("Reset Encoders", new ResetEncoders());
    chooser.addOption("Drive 100 inches", new DriveTurn(100, 0.8, 0));
    chooser.addOption("Drive -10 inches", new DriveTurn(10, -0.3, 0));
    chooser.addOption("Drive 108 inches", new DriveTurn(108, 0.8, 0));
    //chooser.addOption("Follow Path", new FollowPath());

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
    // driveTrain.putCrap(); This might be taking too long and causing the annoying
    // warning messages about the overriding 20ms loop
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

    // schedule the autonomous command (example)
     if (autonomousCommand != null) {
     autonomousCommand.start();
     }
    // leftTrajectory = Pathfinder.readFromCSV(new File(
    // "C:\\Users\\Staley Robotics\\Documents\\fuvk\\StaleyRC2019\\Staley RC
    // 2019\\src\\main\\resources\\output\\DriveAutoLine.right.pf1.csv"));
    // rightTrajectory = Pathfinder.readFromCSV(new File(
    // "C:\\Users\\Staley Robotics\\Documents\\fuvk\\StaleyRC2019\\Staley RC
    // 2019\\src\\main\\resources\\output\\DriveAutoLine.left.pf1.csv"));
    /*
    System.out.println("Hitting");

    Trajectory rightTrajectory;
    Trajectory leftTrajectory;

    // Waypoint[] points = new Waypoint[] {
    // new Waypoint(0, 0, 0),
    // new Waypoint(2, 0, 0),
    // new Waypoint(4, 0, 0) };
    // Trajectory.Config config = new
    // Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
    // Trajectory.Config.SAMPLES_HIGH,
    // 0.05, 1.7, 2.0, 60.0);
    // Trajectory trajectory = Pathfinder.generate(points, config);

    // // Wheelbase Width = 0.635m
    // TankModifier modifier = new TankModifier(trajectory).modify(0.635);

    // // Do something with the new Trajectories...
    // Trajectory leftTrajectory = modifier.getLeftTrajectory();
    // Trajectory rightTrajectory = modifier.getRightTrajectory();
    rightTrajectory = PathfinderFRC.getTrajectory("DriveAutoLine.left");
    leftTrajectory = PathfinderFRC.getTrajectory("DriveAutoLine.right");

    leftFollower = new EncoderFollower(leftTrajectory);
    rightFollower = new EncoderFollower(rightTrajectory);

    leftFollower.configureEncoder((int) driveTrain.getLeftPosition(), (int) Constants.PULSES_PER_REV,
        Constants.WHEEL_DIAMETER);
    leftFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / Constants.MAX_VELOCITY, 0);

    rightFollower.configureEncoder((int) driveTrain.getRightPosition(), (int) Constants.PULSES_PER_REV,
        Constants.WHEEL_DIAMETER);
    rightFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / Constants.MAX_VELOCITY, 0);

    followerNotifier = new Notifier(this::followPath);
    followerNotifier.startPeriodic(leftTrajectory.get(0).dt);
    */

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
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
      
    //followerNotifier.stop();
    }
    driveTrain.tankDrive(0, 0);
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

  private void followPath() {
    double left = 0;
    double right = 0;
    if (leftFollower.isFinished() || rightFollower.isFinished()) {
      followerNotifier.stop();
    } else {
      double leftSpeed = leftFollower.calculate((int) driveTrain.getLeftPosition());
      double rightSpeed = rightFollower.calculate((int) driveTrain.getRightPosition());

      double heading = driveTrain.getYaw();
      double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
      double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading);
      double turn = 0.8 * (-1.0 / 80.0) * headingDifference;
      // double turn = 0.05 * headingDifference;
      System.out.println("Heading: " + heading);
      System.out.println("Desired Heading: " + desiredHeading);
      System.out.println("heading difference: " + headingDifference);
      System.out.println("Turn: " + turn);

      if ((leftSpeed + turn) > 1) {
        left = 1;
      } else if ((leftSpeed + turn) < -1) {
        left = -1;
      } else {
        left = leftSpeed + turn;
      }
      if ((rightSpeed - turn) > 1) {
        right = 1;
      } else if ((rightSpeed - turn) < -1) {
        right = -1;
      } else {
        right = rightSpeed - turn;
      }
      System.out.println(driveTrain.getRightMaster().getMotorOutputVoltage());
      System.out.println(
driveTrain.getLeftMaster().getMotorOutputVoltage());
      System.out.println("driveTrain.tankDrive(" + left + ", " + right + "));");
      System.out.println("\n");

      driveTrain.tankDrive(left, right);
    }
  }
}
