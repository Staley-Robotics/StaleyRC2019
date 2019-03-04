package frc.robot.enums;
/**
 * Holds angle setpoints for the Shooter pivots
 */
public enum PivotTarget {
    
    GROUND(0, 0), LOW(300000, 0.2), MID(1030000, 0.31), HIGH(960000, 0.355), CARGO(800000, 0.3);

    private final double position;
    private final double power;

    /**
     * Constructor: enum object takes in angle and power
     */
    PivotTarget(double position, double power) {
        this.position = position;
        this.power = power;
    }

    /**
     * 
     * @return angle
     */
    public double getPosition() {
        return position;
    }

    /**
     * 
     * @return power
     */
    public double getPower() {
        return power;
    }
}