package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

public class ResetEncoders extends Command {

    private DriveTrain driveTrain;

    public ResetEncoders() {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    @Override
    protected void execute() {
        driveTrain.zeroDriveEncoders();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}