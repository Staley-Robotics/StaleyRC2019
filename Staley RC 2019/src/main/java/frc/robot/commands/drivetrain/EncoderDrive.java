package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

/**
 * Definitely doesnt work
 */
public class EncoderDrive extends Command {

    private DriveTrain driveTrain;

    private double startingLeftEncoderPulses;
    private double startingRightEncoderPulses;
    private double desiredLeftEncoderPulses;
    private double desiredRightEncoderPulses;

    private double leftSpeed;
    private double rightSpeed;

    // PID values
    private PIDController pidTurn;

    private double currentLeft;
    private double currentRight;

    private double targetLeft;
    private double targetRight;
    private double leftError;
    private double rightError;

    // PID Values
    private final double lKP = 0.03; // 0.023 //0.03
    private final double rKP = 0.03;
    private final double kI = 0.0; // 0
    private final double kD = 0.06; // 0.06
    private final double pThreshold = 500;

    private final double SAFETYSPEEDREDUCER = 0.4;

    private final double stopThreshold = 30;

    public EncoderDrive(double endLeft, double endRight) {
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);

        desiredLeftEncoderPulses = endLeft;
        desiredRightEncoderPulses = endRight;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startingLeftEncoderPulses = driveTrain.getLeftPosition();
        startingRightEncoderPulses = driveTrain.getRightPosition();

        desiredLeftEncoderPulses -= startingLeftEncoderPulses;
        desiredRightEncoderPulses -= startingRightEncoderPulses;

    }

    // Called repeatedly when this Command is scheduled to run
    // EncoderDrive drives x extra pulses, not total
    @Override
    protected void execute() {
        leftError = Math.abs(desiredLeftEncoderPulses) - Math.abs(driveTrain.getLeftPosition());
        rightError = Math.abs(desiredRightEncoderPulses) - Math.abs(driveTrain.getRightPosition());
        // double leftSign =
        // Math.signum(desiredLeftEncoderPulses-driveTrain.getLeftPosition());
        // double . = Math.signum(desiredRightEncoderPulses -
        // driveTrain.getRightPosition());

        // Issue because right doesn't turn at same rate as left, PID to fix issue
        // this might work
        double anglet = 0.24;
        if (leftError < pThreshold || rightError < pThreshold) {
            driveTrain.tankDrive(leftError * lKP, rightError * rKP);
        } else {
            // lemme get uhh
            // solid chance I messed one of these up
            driveTrain.tankDrive(
                    leftError < -1 ? -1 + SAFETYSPEEDREDUCER : leftError > 1 ? 1 - SAFETYSPEEDREDUCER : leftError,
                    rightError < -1 ? -1 + SAFETYSPEEDREDUCER : rightError > 1 ? 1 - SAFETYSPEEDREDUCER : rightError);

        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {

        if (Math.abs(leftError - desiredLeftEncoderPulses) <= stopThreshold
                && Math.abs(rightError - desiredRightEncoderPulses) <= stopThreshold)
            System.out.println("Success Obtained: " + leftError + " , " + desiredLeftEncoderPulses);
        return Math.abs(leftError - desiredLeftEncoderPulses) <= stopThreshold
                && Math.abs(rightError - desiredRightEncoderPulses) <= stopThreshold;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}