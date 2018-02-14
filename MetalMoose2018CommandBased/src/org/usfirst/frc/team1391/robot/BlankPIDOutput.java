package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class BlankPIDOutput implements PIDOutput {
    double output;
    
    public BlankPIDOutput()
    {
        output = 0;
    }

    public void pidWrite(double output) {
        this.output = output;
    }

    public double getOutput() {
        return output;
    }
    
}
