package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.ElevatorManualControl;

/**
 * Controls the elevator motors and encoder.
 */
public class Elevator extends Subsystem {

    // Speed controllers for the motors of the elevator
    private Spark elevatorLeftMotor = new Spark(RobotMap.elevatorLeftMotorPort);
    private Spark elevatorRightMotor = new Spark(RobotMap.elevatorRightMotorPort);

    // Speed group for the elevator
    private SpeedControllerGroup elevatorMotors = new SpeedControllerGroup(elevatorLeftMotor, elevatorRightMotor);

    // Elevator encoder
    public Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderAPort, RobotMap.elevatorEncoderBPort, false, Encoder.EncodingType.k4X);

    public Elevator() {
        // The motors have to run in the opposite directions
        elevatorRightMotor.setInverted(true);

        // Change the coefficient of the elevator to match the distance travelled in percent of total height
        elevatorEncoder.setDistancePerPulse(RobotMap.elevatorEncoderCoefficient);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorManualControl());
    }

    /**
     * Set throttled speed of the elevator motors
     *
     * @param speed Speed to which to set the motor to (this will be throttled).
     */
    public void setThrottledSpeed(double speed) {
        // The x of the function
        double x = elevatorEncoder.getDistance();

        // The actual speed (multiplying the y of the function with the input speed)
        double throttledSpeed = speed * getThrottledSpeed(x, Math.signum(speed));

        elevatorMotors.set(throttledSpeed);
    }

    /**
     * Get the y value at point x of the graph of the throttle function.
     *
     * @param x         The x value of the graph.
     * @param direction The direction that the elevator is going (the functions are different when going up and down)
     * @return The y value of the graph.
     */
    private double getThrottledSpeed(double x, double direction) {
        // Restrict the x values (from 0 to maximum), to remove any unpredictable behavior
        if (x < 0) x = 0;
        else if (x > RobotMap.elevatorMaximumDistance) x = RobotMap.elevatorMaximumDistance;

        // Two sets of coefficients for the elevator going up and down
        final double[] upCoefficients = new double[]{-0.0000001233779715, 0.000024675594291, -0.0017622246431853, 0.052844492636667, 0.45};
        final double[] downCoefficients = new double[]{-0.0000000957264957, 0.0000191452991454, -0.0016365811965889, 0.0679316239315355, -0.1};

        // This variable will just take one of the previous two arrays as a reference
        double[] coefficients;

        // Pick the right equation from the direction that teh elevator wants to go
        // Also, if going up, we go full speed (similar to going down) - that is why we set x to half of the polynomial max
        if (direction == 1) {
            coefficients = upCoefficients;
            if (x < RobotMap.elevatorMaximumDistance / 2) x = RobotMap.elevatorMaximumDistance / 2;
        } else {
            coefficients = downCoefficients;
            if (x > RobotMap.elevatorMaximumDistance / 2) x = RobotMap.elevatorMaximumDistance / 2;
        }

        // Calculate the y value of the graph
        double value = 0;
        for (double coefficient : coefficients) value = value * x + coefficient;

        // The maximum motor speed is 1 (or -1, for that matter)
        // Since one of the functions gies slightly above 1
        if (value > 1) return 1;
        else if (value < -1) return -1;
        else return value;
    }

    /**
     * Sets the absolute speed of the elevator motors.
     *
     * @param speed Speed to set the motors to (1 to -1).
     */
    public void setAbsoluteSpeed(double speed) {
        elevatorMotors.set(speed);
    }

    /**
     * Sets the motors to the hold speed.
     */
    public void hold() {
        elevatorMotors.set(RobotMap.elevatorHoldSpeed);
    }
}
