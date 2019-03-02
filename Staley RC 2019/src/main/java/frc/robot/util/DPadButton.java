/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Button for the up, down, left, and right on the DPad of an xbox controller.
 * Apparently they are super jank and you need this special crap.
 */
public class DPadButton extends Button {

    private XboxController xBox;
    private Direction direction;

    public DPadButton(XboxController xBox, Direction direction) {
        this.xBox = xBox;
        this.direction = direction;
    }

    @Override
    public boolean get() {
        int degree = xBox.getPOV();
        return degree == direction.degree;
    }

    public enum Direction {
        Up(0), Down(180), Left(270), Right(90);

        int degree;

        Direction(int degree) {
            this.degree = degree;
        }

        public int getDegrees() {
            return degree;
        }
    }
}
