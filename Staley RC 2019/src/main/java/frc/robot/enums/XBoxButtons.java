package frc.robot.enums;

public enum XBoxButtons {
    kBumperLeft(5), 
    kBumperRight(6), 
    kStickLeft(9), 
    kStickRight(10), 
    kA(1), 
    kB(2), 
    kX(3), 
    kY(4), 
    kBack(7), 
    kStart(8),
    kUp(0),
    kDown(180),
    kLeft(270),
    kRight(90);

    private final int value;

    XBoxButtons(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}