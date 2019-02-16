package frc.robot.enums;
/**
 * Holds angle setpoints for the Shooter pivots
 */
public enum ShooterPivotSetpoints {
    
    GROUND(0, 0), LOW(20, 0.2), MID(45, 0.4), HIGH(60, 0.6), CARGO(80, 0.8);

    private final int angle;
    private final double power;
    /**
     * Constructor: enum object takes in angle and power
     */
    ShooterPivotSetpoints(int angle, double power) {
        this.angle = angle;
        this.power = power;
    }
    /**
     * 
     * @return angle
     */
    public int getAngle() {
        return angle;
    }
    /**
     * 
     * @return power
     */
    public double getPower() {
        return power;
    }
}