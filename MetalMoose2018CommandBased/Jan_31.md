# Subsystems

* Drivetrain
  * 3 driving motors on each side; total 6 motors
* Fidget / Collector
  * 1 turning motor on each side; total 2 motors
* Elevator
  * 1 motor for lifting
  * 1 motor for rotating
* Cameras
* Gyroscope

# Command

* DifferentiateDrive 
  * requires: Drivetrain
  * Not written as a command; impelment it like iterative
* SpinIn
  * requires: Fidget
* SpinOut 
  * requires: Fidget
* Lift 
  * requires: Elevator
* Drop
  * requires: Elevator

# Things to be done

* Learning the final designes of the fidget and the elevator
* Decide what sensors/encoder to use
* Decide which joystick to use and the mapping of buttons
* 
