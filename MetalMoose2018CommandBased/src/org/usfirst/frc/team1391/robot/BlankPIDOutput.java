package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.PIDOutput;

/*
 * Interface between the PIDController object and the other robot code.
 */
public class BlankPIDOutput implements PIDOutput {

    // Saves the output of the PIDController
    private double output;

    public BlankPIDOutput() {
        output = 0;
    }

    // Writes to output so it can be read
    public void pidWrite(double output) {
        this.output = output;
    }
}
