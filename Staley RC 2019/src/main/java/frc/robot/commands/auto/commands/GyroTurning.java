package frc.robot.commands.auto.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

public class GyroTurning extends Command implements PIDOutput {

    private DriveTrain driveTrain;

    private PIDController pidTurn;

    private Timer delay;

    private double angle;

    // PID Values
    private final double kP = 0.03; //0.023
    private final double kI = 0; //0
    private final double kD = 0.06; //0.06

    public GyroTurning(double angle) {
        requires(DriveTrain.getInstance());
        driveTrain = DriveTrain.getInstance();

        delay = new Timer();
        delay.reset();

        this.angle = angle;

        pidTurn = new PIDController(kP, kI, kD, driveTrain.getNavx(), this);
        // Range of angles that can be inputted
        pidTurn.setInputRange(-180, 180);

        // prevent the motors from receiving too little power
        if (angle > 0)
            pidTurn.setOutputRange(0.5, 1);
        else if (angle < 0)
            pidTurn.setOutputRange(-1, -0.5);

        // Tolerance of how far off the angle can be
        pidTurn.setAbsoluteTolerance(1.0);
        pidTurn.setContinuous(false);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        driveTrain.resetNavx();

        // Delay while the gyro is reseting
        delay.start();
        while (delay.get() < 0.1) {
            System.out.println("Delaying Gyro");
        }
        delay.stop();

        pidTurn.setSetpoint(angle);
        pidTurn.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Angle: " + driveTrain.getYaw());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pidTurn.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        driveTrain.arcadeDrive(0, 0);

        System.out.println("Finished: " + driveTrain.getYaw());

        pidTurn.disable();
        pidTurn.reset();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    /*
     * Outputs the motor speed from the PIDController
     */
    @Override
    public void pidWrite(double output) {
        driveTrain.arcadeDrive(0, -output);
    }

}