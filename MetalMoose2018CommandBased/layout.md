# Subsystems
* Systems not yet implemented are in italics.

## Basic
* Drivetrain
  * 3 driving motors on each side; total 6 motors
* *FidgetSpinner*
  * 1 turning motor on each side; total 2 motors
* *Elevator*
  * 1 or 2 motors

## Hypothetical
* Gyroscope
* Cameras

# Commands
* Drive (Arcade/Tank) (digital)
  * requires: Drivetrain
* *Intake high (analog)*
  * requires: FidgetSpinner
* *Outtake high (analog)*
  * requires: FidgetSpinner
* *Outtake low (analog)*
  * requires: FidgetSpinner
* *Elevate (digital)*
  * requires: Elevator
* *Endgame Switch (analog)*
  * Disables non-endgame functions
  
# To-do
* Figure out the best way to approach the autonomous code - layout of the commands.
	* Decide on sensor input for autonomous.
* Figure out, how to best connect SmartDashboard to variable values in the code.
* Finalize naming conventions for variables in Robot.java (kDriveTrain, m_oi...).