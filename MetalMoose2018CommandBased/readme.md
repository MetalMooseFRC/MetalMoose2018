# Subsystems

* Drivetrain
  * 3 driving motors on each side; total 6 motors
* Fidget / Collector
  * 1 turning motor on each side; total 2 motors
* Elevator
  * 1 motor for lifting
* Cameras
* Gyroscope

# Command

* ArcadeDrive/TankDrive
  * requires: Drivetrain
  * Not written as a command; impelment it like iterative
* Intake
  * requires: Fidget
* Discharge 
  * requires: Fidget
* Lift/Drop 
  * requires: Elevator
  * controlled by a joystick

# Things to be done

* Decide what sensors/encoder to use
* Mapping the controller to commands