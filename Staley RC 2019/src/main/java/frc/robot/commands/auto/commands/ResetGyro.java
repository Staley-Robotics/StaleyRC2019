package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

public class ResetGyro extends Command {

    private DriveTrain driveTrain;

    public ResetGyro() {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    @Override
    protected void execute() {
        driveTrain.resetYaw();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}