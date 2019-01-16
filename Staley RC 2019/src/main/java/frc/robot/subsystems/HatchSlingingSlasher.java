package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchSlingingSlasher extends Subsystem {

    private static HatchSlingingSlasher instance;

    private HatchSlingingSlasher() {

    }

    public HatchSlingingSlasher getInstance() {
        if(instance == null) {
            instance = new HatchSlingingSlasher();
        }

        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }

}