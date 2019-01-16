package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

    private static Climber instance;

    private Climber() {

    }

    public Climber getInstance() {
        if(instance == null) {
            instance = new Climber();
        }

        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}