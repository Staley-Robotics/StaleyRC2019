/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.pivot.RunShooterPivot;
import frc.robot.enums.PivotControlMode;
import frc.robot.enums.PivotTarget;
import frc.robot.util.SpeedControllerFactory;

/**
 * Pivoting mechanism for Shooter
 */
public class ShooterPivot extends Subsystem {

  private static ShooterPivot instance;

  public PivotControlMode pivotControlMode;
  public PivotTarget pivotTarget;

  public WPI_TalonSRX master;
  private WPI_VictorSPX follower;

  private DigitalInput lowerLimitSwitch;

  private ShooterPivot() {
    master = SpeedControllerFactory.createMasterSrx(RobotMap.SHOOTER_PIVOT_TALONSRX_CAN_ID, false, true);
    master.setInverted(false);
    follower = SpeedControllerFactory.createFollowerSpx(RobotMap.SHOOTER_PIVOT_VICTORSPX_CAN_ID, master, true);
    follower.setInverted(true);

    pivotControlMode = PivotControlMode.USER_CONTROL;
    pivotTarget = PivotTarget.GROUND;

    lowerLimitSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH);
  }

  /**
   * ensures the user receives the instance of ShooterPivot
   * 
   * @return static instance of ShooterPivot
   */
  public static ShooterPivot getInstance() {
    if (instance == null) {
      instance = new ShooterPivot();
    }
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunShooterPivot());
  }

  // ***** Running Motors *****

  public void runPivot(double power) {
    master.set(power);
  }

  public void stopPivot() {
    master.stopMotor();
    follower.stopMotor();
  }

  // ***** PID Crap *****

  public void zeroPivotEncoder() {
    master.setSelectedSensorPosition(0, 0, 10);
  }

  public double getPivotPosition() {
    return master.getSelectedSensorPosition(0);
  }

  public void setPivotTarget(double pulses) {
    master.set(ControlMode.Position, pulses);
  }

  // ***** Limit Switch *****

  public boolean getLimitSwitch() {
    return lowerLimitSwitch.get();
  }
}
