package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RunCompressor;

public class Pneumatics extends Subsystem {

    private static Pneumatics instance;
    private Compressor compressor;

    private Pneumatics() {
        compressor = new Compressor(RobotMap.COMPRESSOR_PORT);
    }

    public static Pneumatics getInstance(){
        if(instance == null) {
            instance = new Pneumatics();
        }

        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunCompressor());
    }

    public void runCompressor() {
        compressor.setClosedLoopControl(true);
    }
 }