/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Shooting mechanism with three motors for intaking and shooting
 */
public class ShooterThroughput extends Subsystem {

    private static ShooterThroughput instance;
    
    public static boolean succ;
    public static boolean hasBall;
    private Talon lowMotor, highMotor;

    private DigitalInput limitSwitch;

    private ShooterThroughput() {
        hasBall = false;
        succ = false;
        lowMotor = new Talon(RobotMap.THROUGHPUT_LOW_TALON_PORT);
        highMotor = new Talon(RobotMap.THROUGHPUT_HIGH_TALON_PORT);

        // added by ryan
        lowMotor.setInverted(true);
        highMotor.setInverted(false);
        // end added by ryan

        limitSwitch = new DigitalInput(RobotMap.THROUGHPUT_LIMIT_SWITCH);
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

    @Override
    protected void initDefaultCommand() {
    }

    // ***** Motor Control *****

    /**
     * Runs shooter when power > 0, runs intake when power < 0
     */
    public void runThroughput(double power) {
        lowMotor.set(power);
        highMotor.set(power);
    }

    public void stopThroughput() {
        lowMotor.set(0);
        highMotor.set(0);
    }

    // ***** Limit Switch *****

    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }

}