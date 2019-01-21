package frc.robot.util;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DriverStation;

public class SpeedControllerFactory {

    // PID Values for TalonSRX
    private static final double kP = 0.015;
    private static final double kI = 0.0;
    private static final double kD = 0.7;

    /**
     * Creates a master TalonSRX
     * 
     * @param canID       CAN ID of talonSRX
     * @param sensorPhase direction of encoder recording
     * @param brakeMode   whether talon is in brake mode or coast mode
     */
    public static WPI_TalonSRX createMasterSrx(int canID, boolean sensorPhase, boolean brakeMode) {

        WPI_TalonSRX talon = null;

        try {
            talon = new WPI_TalonSRX(canID);
            talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
            talon.setSensorPhase(sensorPhase);
            talon.setNeutralMode(brakeMode ? NeutralMode.Brake : NeutralMode.Coast);
            talon.config_kP(0, kP, 0);
            talon.config_kI(0, kI, 0);
            talon.config_kD(0, kD, 0);
        } catch (RuntimeException ex) {
            DriverStation.reportError("Error Instantiating TalonSRX: " + ex.getMessage(), true);
        }
        return talon;
    }

    /**
     * Creates a VictorSPX that follows a TalonSRX
     * 
     * @param canID     CAN ID of the VictorSPX
     * @param masterSRX master TalonSRX of VictorSPX
     * @param brakeMode whether VictorSPX is in brake mode or coast mode
     */
    public static WPI_VictorSPX createFollowerSpx(int canID, WPI_TalonSRX masterSRX, boolean brakeMode) {

        WPI_VictorSPX victor = null;

        try {
            victor = new WPI_VictorSPX(canID);
            victor.follow(masterSRX);
            victor.setNeutralMode(brakeMode ? NeutralMode.Brake : NeutralMode.Coast);
        } catch (RuntimeException ex) {
            DriverStation.reportError("Error Instantiating VictorSPX: " + ex.getMessage(), true);
        }
        return victor;
    }
}