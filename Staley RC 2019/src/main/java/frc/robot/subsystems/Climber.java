package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

    private static Climber instance;

    private DoubleSolenoid frontLifter;
    private DoubleSolenoid rearLifter;

    private Climber() {
        frontLifter = new DoubleSolenoid(2, 3);
        rearLifter = new DoubleSolenoid(3, 4);
    }

    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }

        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void extendFront() {
        frontLifter.set(Value.kForward);
    }

    public void retractFront() {
        frontLifter.set(Value.kReverse);
    }

    public void extendRear() {
        rearLifter.set(Value.kForward);
    }

    public void retractRear() {
        rearLifter.set(Value.kReverse);
    }
}