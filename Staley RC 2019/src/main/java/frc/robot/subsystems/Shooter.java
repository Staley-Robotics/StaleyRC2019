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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.RunShooterPivot;
import frc.robot.enums.ShooterPivotSetpoints;
import frc.robot.enums.ShooterPivotStates;
import frc.robot.util.SpeedControllerFactory;

/**
 * Shooting mechanism with two motors for pivot and three motors for intaking
 * and shooting
 */
public class Shooter extends Subsystem {

    private static Shooter instance;

    public static ShooterPivotStates shooterPivotState;
    public static ShooterPivotSetpoints pivotSetpoints;

    private Talon leftMotor, rightMotor, topMotor;
    private WPI_TalonSRX pivotTalon;
    private WPI_VictorSPX pivotVictor;

    private Solenoid shooterPiston;

    private Shooter() {
        leftMotor = new Talon(RobotMap.SHOOTER_LEFT_TALON_PORT);
        rightMotor = new Talon(RobotMap.SHOOTER_RIGHT_TALON_PORT);
        topMotor = new Talon(RobotMap.SHOOTER_TOP_TALON_PORT);

        shooterPiston = new Solenoid(7); // 9, >=8 gives an error

        pivotTalon = SpeedControllerFactory.createMasterSrx(6, false, true);
        pivotVictor = SpeedControllerFactory.createFollowerSpx(9, pivotTalon, true);

        shooterPivotState = ShooterPivotStates.USER_CONTROL;
    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunShooterPivot());
    }

    /**
     * Runs shooter when power > 0, runs intake when power < 0
     */
    public void runShooter(double power) {
        leftMotor.set(power);
        rightMotor.set(power);
        topMotor.set(power);
    }

    public void stopShooter() {
        leftMotor.set(0);
        rightMotor.set(0);
        topMotor.set(0);
    }

    /**
     * Angles shooter with input from controller
     */
    public void runPivot(double power) {
        pivotTalon.set(power);
    }

    /**
     * Sets shooter angle to given target angle
     */
    public void setPivotTarget(double angle) {
        pivotTalon.set(ControlMode.Position, angleToPulses(angle));
    }

    public static void setAngle() {
        
    }

    /**
     * Converts given angle value from degrees to encoder pulses
     * 
     * @param angle angle in degrees
     * @return angle in pulses
     */
    public double angleToPulses(double angle) {
        return (angle / 360) * 4096;
    }
}