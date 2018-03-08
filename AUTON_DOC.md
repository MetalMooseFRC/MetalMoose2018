# Autonomous Parsing Language Documentation
This document covers the specifics of the parsing language used for the Metal Moose 2018 robot.

## Introduction
The robot operates on a set of commands that it reads from the code. It then generates the actual commands to control the various subsystems of the robot.

## Command Syntax
All of the commands issued to the robot are in a form `Command(Parameter_1, Parameter_2, Parameter_3...)`, where each of the parameters is a number (either decimal, or integral). The commands are separated using a space (or spaces). The parameters have to be separated using a comma, the space is not required. The parser itself only parses the capital letters of the command and is case-sensitive, so `Goto(1, 50)` is the same as `G(10, -10)`, and `GoTo(10, 10)` is the same as `GT(10, 10)`.

Functionality-wise, all of the commands are either parallel, or sequential (refer to the "Commands" part of this document).

All of the numbers have to be in either one of the two forms bellow:
* Integral: 1, 5, -3, +7...
* Decimal: 0.6, 2.8, +9.0, -3.5

## Commands
This part of the document goes over the specifics of the respective comma.

Optional commands are distinguished from mandatory parameters by using their name and equal sign to define their values: eg. Goto(50, 50, stopFromGoal=15).

### Elevate
**Functionality:** Moves the elevator into one of the set positions.  
**Syntax:** `Elevate(position)`, where position is an integral number describing the desired position of the elevator (these are defined as an array in the RobotMap).  
**Type:** sequential.

### Intake
**Functionality:** Intake (using the collector) until interrupted by another command.  
**Syntax:** `Intake()`.  
**Type:** parallel (meant to be used with another sequential command like Drive).

### Outtake
**Functionality:** Outtake (using the collector) for a set period of time.  
**Syntax:** `Outtake(time)`, where time is a decimal number of seconds.  
**Type:** sequential.

### Forebar Up
**Functionality:** Moves the forebar up (and tells it to start holding).  
**Syntax:** `ForebarUp()`.  
**Type:** sequential.

### Forebar Down
**Functionality:** Moves the forebar down.  
**Syntax:** `ForebarDown()`.  
**Type:** sequential.

### Drivetrain Timeout
**Functionality:** Times out the drivetrain for a set period of time.  
**Syntax:** `Timeout(time)`, where time is a decimal number of seconds.  
**Type:** sequential.

### Goto
**Functionality:** Generates two move commands, depending on the current position of the robot and the desired position of the robot. The robot first turns, then drives to the desired coordinates.  
**Syntax:** `Goto(x, y, *intake=intakeOrNot*, *stopFromGoal=distance*)`, where x is the decimal x coordinate and y is the decimal y coordinate. The point [0, 0] is mid of the field in the wall of the allied team. Going left, x is negative. Going right, x is positive. Parameter y is always positive, and measures the distance from the allied wall (if y is negative, something went terribly wrong). The optional parameter intake tells the robot, whether to start intaking during the drive part of the goto command. The command stomFromGoal tells the drive to stop a certain distance (in inches) in front of the goal.  
**Type:** sequential.

### TurnTo
**Functionality:** Turns the robot to a certain angle.  
**Syntax:** `TurnTo(angle)`, where angle is the angle that the robot should turn to. Looking at the opposing team, forward is zero degrees. Left side is negative, right side is positive.  
**Type:** sequential.

### TurnBy
**Functionality:** Turns the robot by a certain angle.  
**Syntax:** `TurnBy(angle)`, where angle is the angle that the robot needs to turn by. Left side is negative, right side is positive.  
**Type:** sequential.

### Drive
**Functionality:** Turns the robot to (not by) a certain angle.  
**Syntax:** `Drive(distance)`, where distance is the distance to be driven by the robot in inches.  
**Type:** sequential.

### Chunk and Inversed Chunk
**Functionality:** The main functionality of the parser is to insert the Chunk commands in places, where repetitive groups of commands would be. The main purpose is to call the commands and their respective chunks with the various layouts of the field (starting left and the scale being right, we can assume that it is like starting right and the scale being left). The inversed chunk inverts all x values of goto commands (x -> -x), all angles (angle = -angle) and all chunks (chunk = -chunk and yes, you can do recursion).  
**Syntax 1:** `Chunk(chunk number)`, where chunk number is the integral number of the chunk.  
**Syntax 2:** `-Chunk(chunk number)`, where chunk number is the integral number of the chunk. This chunk will be inversed.  
**Type:** N/A.