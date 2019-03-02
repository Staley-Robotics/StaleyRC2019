/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Shooting mechanism with three motors for intaking and shooting
 */
public class ShooterThroughput extends Subsystem {

    private static ShooterThroughput instance;

    private Talon leftMotor, rightMotor, topMotor;

    private static Solenoid piston;

    private ShooterThroughput() {

        leftMotor = new Talon(RobotMap.THROUGHPUT_LEFT_TALON_PORT);
        rightMotor = new Talon(RobotMap.THROUGHPUT_RIGHT_TALON_PORT);
        topMotor = new Talon(RobotMap.THROUGHPUT_TOP_TALON_PORT);

        topMotor.setInverted(true);

        if(piston == null) {
            piston = new Solenoid(RobotMap.THROUGHPUT_SOLENOID_PORT);
        }
    }

    /**
     * ensures the user receives the instance of Shooter
     * 
     * @return static instance of Shooter
     */
    public static ShooterThroughput getInstance() {
        if (instance == null) {
            instance = new ShooterThroughput();
        }
        return instance;
    }

    /**
     * The piston for the Shooter and the pistons for the Hatch Slinging Slasher are
     * hooked up to the same solenoid so this is needed elsewhere.
     * 
     * @return An instance of the Solenoid used for this piston.
     */
    public static Solenoid getSolenoid() {
        if (piston == null) {
            piston = new Solenoid(RobotMap.THROUGHPUT_SOLENOID_PORT);
        }
        return piston;
    }

    @Override
    protected void initDefaultCommand() {
    }

    // ***** Motor Control *****

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

    // ***** Piston *****

    public void extendShooterPiston() {
        piston.set(true);
    }

    public void retractShooterPiston() {
        piston.set(false);
    }

}