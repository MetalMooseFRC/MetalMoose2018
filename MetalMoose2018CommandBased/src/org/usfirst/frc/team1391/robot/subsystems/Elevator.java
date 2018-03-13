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
    private SpeedControllerGroup elevatorMotors = new SpeedControllerGroup(elevatorLeftMotor, elevatorRightMotor);

    // Elevator encoder.
    public Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderAPort, RobotMap.elevatorEncoderBPort, false, Encoder.EncodingType.k4X);

    public Elevator() {
        // The motors have to run in the opposite directions
        elevatorRightMotor.setInverted(true);

        // Change the coefficient of the elevator to match the distance travelled in percent of total height (100 is max)
        elevatorEncoder.setDistancePerPulse(RobotMap.elevatorEncoderCoefficient);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorManualControl());
    }

    /**
     * Set speed of the elevator motors (throttled).
     *
     * If start and end are left as default values (0 and RobotMap.elevatorMaximumDistance, respectively),
     * teleop is using the function. If, however, we want to go from a point on the elevator to another point
     * on the elevator, we will set starting and ending values to shift the function to reflect these points.
     *
     * @param speed Input form the joystick.
     * @param start Where is the start of the elevator movement.
     * @param end Where is the end of the elevator movement.
     */
    public void setThrottledSpeed(double speed, double start, double end) {
        // We sometimes want to go from somewhere on the elevator to somewhere else
        // That is why we have to first squish, and then shift the function
        double coefficient = RobotMap.elevatorMaximumDistance / (end - start);
        double shift = (start * coefficient);

        // The x of the function
        double x = (elevatorEncoder.getDistance() * coefficient) - shift;

        // The actual speed (multiplying the y of the function with the input speed)
        double throttledSpeed = speed * getThrottledSpeed(x);

        // Going down, we want to slow down the elevator (gravity...)
        if (throttledSpeed < 0) throttledSpeed *= RobotMap.elevatorSlowCoefficient;

        elevatorMotors.set(throttledSpeed);
    }

    /**
     * Get the y value at point x of the graph of the throttle function.
     *
     * @param x The x of the graph.
     * @return The y of the graph.
     */
    private double getThrottledSpeed(double x) {
        // The coefficients of the polynomial
        double[] coefficients = new double[]{-0.0000001233779715, 0.000024675594291, -0.0017622246431853, 0.052844492636667, 0.45};

        // Calculate the y value of the graph
        double value = 0;
        for (double coefficient : coefficients) value = value * x + coefficient;

        // The maximum motor speed is 1 (or -1, for that matter)... it does not make sense to have more than that.
        if (value > 1) return 1;
        else return value;
    }

    /**
     * Sets the absolute speed of the elevator motors.
     *
     * @param speed Speed to set for the motors (1 to -1).
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
