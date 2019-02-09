package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.commands.shooter.RunShooterPivot;
import frc.robot.enums.ShooterPivotStates;
import frc.robot.util.SpeedControllerFactory;

public class Shooter extends Subsystem {

    private static Shooter instance;

    private Talon leftMotor, rightMotor, topMotor;
    private Solenoid shooterPiston;
    private WPI_TalonSRX pivotTalon;
    private WPI_VictorSPX pivotVictor;

    public ShooterPivotStates shooterPivotState;

    private Shooter() {
        leftMotor = new Talon(RobotMap.SHOOTER_LEFT_TALON);
        rightMotor = new Talon(RobotMap.SHOOTER_RIGHT_TALON);
        topMotor = new Talon(RobotMap.SHOOTER_TOP_TALON);

        shooterPiston = new Solenoid(9);

        pivotTalon = SpeedControllerFactory.createMasterSrx(6, false, true);
        pivotVictor = SpeedControllerFactory.createFollowerSpx(9, pivotTalon, true);

        shooterPivotState = ShooterPivotStates.USER_CONTROL;
    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunShooterPivot());
    }

    public void runShooter(double speed) {
        leftMotor.set(speed);
        rightMotor.set(speed);
        topMotor.set(speed);
    }

    public void stopShooter() {
        leftMotor.set(0);
        rightMotor.set(0);
        topMotor.set(0);
    }

    public void runPivot(double speed) {
        pivotTalon.set(speed);
    }

    public void setPivotTarget(double angle) {
        pivotTalon.set(ControlMode.Position, angleToPulses(angle));
    }

    public double angleToPulses(double angle) {
        return (angle / 360) * 4096;
    }
}