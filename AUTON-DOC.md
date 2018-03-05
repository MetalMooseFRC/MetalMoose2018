# Autonomous Parsing Language Documentation
This document covers the specifics of the parsing language used for the FRC 2018 robot.

## Introduction
The robot operates on a set of commands that is fed to it. It then generates the actual commands to control the various subsystems.

## Command Syntax
All of the commands issued to the robot are in a form `Command(Parameter_1, Parameter_2, Parameter_3...)`, where each of the parameters is a number (either decimal, or integral). The commands are separated using a space. The parameters have to be separated using a comma, the space is not required. The parser itself only parses the first letter of the command and is case-insensitive, so `G(1, 50)` is the same as `Goto(10, -10)` and `gOtO(10, 10)`.

Functionality-wise, all of the commands are either parallel, or sequential (refer to the "Commands" part of this document).

All of the numbers have to be in either one of the two forms bellow:
* Integral: 1, 5, -3, +7...
* Decimal: 0.6, 2.8, +9.0, -3.5

## Commands
This part of the document goes over the specifics of the respective commands.

### Move
**Functionality:** Either turn by a certain angle, or to move a certain distance. **Only one of these values can be non-zero** (due to the fact that the encoder is only on one of the sides of the robot's drivebase). After the completion of the command, it saves the current coordinates and the current angle that it stops in.
**Syntax:** `Move(angle, distance)`, where angle is a decimal number of **degrees** for the robot to turn (negative to the right, positive to the left), and distance is a decimal number of **inches** for the robot to drive.
**Type:** sequential.

### Intake
**Functionality:** Intake (using the collector) for a set period of time.
**Syntax:** `Intake(time)`, where time is a decimal number of seconds.
**Type:** parallel (used with move, goto or timeout command).

### Outtake
**Functionality:** Outtake (using the collector) for a set period of time.
**Syntax:** `Outtake(time)`, where time is a decimal number of seconds.
**Type:** sequential.

### Timeout
**Functionality:** Times out the drivebase for a set period of time.
**Syntax:** `Timeout(time)`, where time is a decimal number of seconds.
**Type:** sequential.

### Goto
**Functionality:** Generates two move commands, depending on the current position of the robot and the desired position of the robot. The robot first turns, then drives.
**Syntax:** `Goto(x, y)`, where x is the decimal x coordinate and y is the decimal y coordinate. The point [0, 0] is mid of the field in the wall of the allied team. Going left, x is negative. Going right, x is positive. Parameter y is always positive, and measures the distance from the allied wall (if y is negative, something went terribly wrong).
**Type:** sequential.

### Elevate
**Functionality:** Moves the elevator into one of the possible positions.
**Syntax:** `Elevate(position)`, where position is an integral number describing the desired position of the elevator.
**Type:** sequential.

### Chunk and Inversed Chunk
**Functionality:** The main functionality of the parser is to insert the Chunk commands in places, where repetitive groups of commands would be. The main purpose is to call the commands and their respective chunks with the various layouts of the field (starting left and the scale being right, we can assume that it is like starting right and the scale being left). The inversed chunk inverts all x values of goto commands (x -> -x), all angles (angle = -angle) and all chunks (chunk = -chunk).
**Syntax 1:** `Chunk(chunk number)`, where chunk number is the integral number of the chunk.
**Syntax 2:** `-Chunk(chunk number)`, where chunk number is the integral number of the chunk. This chunk will be inversed.
**Type:** N/A.