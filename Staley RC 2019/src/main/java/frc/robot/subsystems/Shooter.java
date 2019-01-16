package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    
    private static Shooter instance;

    private Shooter() {

    }

    public Shooter getInstance() {
        if(instance == null) {
            instance = new Shooter();
        }

        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}