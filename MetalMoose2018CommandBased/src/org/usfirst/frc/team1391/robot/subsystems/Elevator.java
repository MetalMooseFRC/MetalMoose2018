package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.ElevatorManualControl;

/**
 * Controls the elevator.
 */
public class Elevator extends Subsystem {
    // Speed controllers for the motors of the elevator
    private Spark elevatorLeftMotor = new Spark(RobotMap.elevatorLeftMotorPort);
    private Spark elevatorRightMotor = new Spark(RobotMap.elevatorRightMotorPort);
    private SpeedControllerGroup elevatorMotors = new SpeedControllerGroup(elevatorLeftMotor, elevatorRightMotor);

    public Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderAPort, RobotMap.elevatorEncoderBPort, false, Encoder.EncodingType.k4X);

    public Elevator() {
        // The motors have to run in the opposite directions
        elevatorLeftMotor.setInverted(true);

        // Change the coefficient of the elevator to match the distance travelled in inches
        elevatorEncoder.setDistancePerPulse(RobotMap.elevatorEncoderCoefficient);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorManualControl());
    }

    /**
     * Set speed of the elevator motors (throttled).
     *
     * @param speed Input form the joystick.
     */
    public void setThrottledSpeed(double speed) {
        double elevatorPosition = elevatorEncoder.getDistance();
        double throttledSpeed = speed * getThrottledSpeed(elevatorPosition);

        elevatorMotors.set(throttledSpeed);
    }

    /**
     * Get the y value at point x of the graph of the throttle function.
     *
     * @param x The x of the graph.
     * @return The y of the graph.
     */
    public double getThrottledSpeed(double x) {
        // The coefficients of the polynomial
        double[] coefficients = new double[]{-0.0000071507642949, 0.0004862519720525, -0.0117169374510997, 0.1173222334909840, 0.6};

        // Calculate the y value at point x of the polynomial
        // Example for 4th degree polynomial: ax^3 + bx^2 + cx + d = x(x(x(a) + b) + c) + d... this simplifies the calculation
        double value = 0;
        for (int i = 0; i < coefficients.length; i++) value = value * x + coefficients[i];

        // The maximum motor speed is 1 (or -1, for that matter)... it does not make sense to have more than that.
        if (value > 1) return 1;
        else return value;
    }

    /**
     * Set speed of the elevator motors (absolute).
     *
     * @param speed Speed to set for the motors (-1 to 1).
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
