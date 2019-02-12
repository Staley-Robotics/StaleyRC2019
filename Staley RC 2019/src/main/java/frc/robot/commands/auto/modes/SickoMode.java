// DOES NOT WORK
package frc.robot.commands.auto.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.commands.DriveTurn;
import frc.robot.commands.drivetrain.EncoderDrive;

/**
 * This shouldnt even be a thing yeet
 */
public class SickoMode extends CommandGroup {

    public SickoMode() {
        // addSequential(new GyroTurning(-90), 5);
        //MoBamba();
        addSequential(new EncoderDrive(3000,3500));
        int yee;
    }
    //sicko mode uses encoder turn
    public void MoBamba() {
        double val = 0.6;
        int ang = 1;
        addSequential(new DriveTurn(0.0, val, ang*27.360000610351562, true));
        System.out.println("1");
        addSequential(new DriveTurn(0.0, val, ang*27.3700008392334, true));
        System.out.println('2');
        addSequential(new DriveTurn(-13414.0, val, ang*9.609999656677246, true));
        System.out.println(3);
        addSequential(new DriveTurn(-13520.0, val, ang*18.889999389648438, true));
        System.out.println('4');
        addSequential(new DriveTurn(-13520.0, val,ang* 18.889999389648438, true));
        System.out.println(5);
        addSequential(new DriveTurn(-7807.0, val, ang*33.630001068115234, true));
        System.out.println(6);
        addSequential(new DriveTurn(-5808.0, val, ang*21.940000534057617, true));
        System.out.println(7);
        addSequential(new DriveTurn(-13520.0, val, ang*17.700000762939453, true));
        System.out.println(8);
        addSequential(new DriveTurn(0.0, val, ang*27.360000610351562, true));
        System.out.println(9);
        addSequential(new DriveTurn(-16155.0, val, ang*23.6200008392334, true));
        System.out.println(10);
        addSequential(new DriveTurn(-6841.0, val,ang* 23.579999923706055, true));  System.out.println(11);
        addSequential(new DriveTurn(-15397.0, val, ang*23.90999984741211, true)); System.out.println(12);
    }
}