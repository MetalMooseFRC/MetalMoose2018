# MetalMoose2018

2018 code repository of the FRC Team 1391.

## Introduction

This repository has been the collaborative effort of the Metal Moose team for the 2018 FRC challenge. The repository includes:

* Source code to the 1391's 2018 robot.
* SmartDashboard XML file.
* Driver Station Log (changing anything on the driver stations).
* Autonomous Documentation.

## Code Features

There are a few interesting features of the 2018 year's code. To name a few:

* **Polynomial Equation Control** - the movement of the elevator is dependent on two 4th degree polynomial functions modeled for this exact purpose (the x value being the position of the elevator). Similarly, the drivebase is slowed down by another polynomial function to improve robot stability when placing cubes high up.
* **Autonomous Parsing Language** - the autonomous code is parsed from a language that we developed for this year's robot using regular expressions. A sample command sequence would be: `DD(224, S=0.9) E(2) TB(-40) O(0.4, S=1) E(0)` (drive distance of 224 inches at speed 0.9, turn by 40 degrees to the left, elevate to position 2, outtake for 0.4 seconds at speed 1 and elevate back down). This allows us to easily mirror sequences when starting in any position and even make up autonomous on-the-go.
* **Double PID Control** - the driving of the robot is controlled by two PID controllers - one controlling the gyro and the other controlling the encoder. This allows straight, precise driving.

For more, refer to the "Other Documents" section of this markdown document.

## Building
The easiest way to build and compile the code would be to create an IntelliJ project and compile with the FRC plugin, as shown in [one of the guides](https://github.com/Metal-Moose/MooseGuides/blob/master/FRC%20Development%20using%20IntelliJ.md) from our [MooseGuides repository](https://github.com/Metal-Moose/MooseGuides).

## Other Documents
* [1391's 2018 Programming Overview](https://drive.google.com/file/d/1prEAt9fbYlkzANMdPi_Zu7v9fk1xWKYo/view?usp=sharing) - going into detail about the programming on the robot.
* [1391's 2018 Offseason Guide](https://drive.google.com/file/d/1bhsEJl2fdqYKO4zaOJlK9KAFKW1eJ5QW/view?usp=sharing) - a guide on how to control the robot (from turning on to the design behind the code). This is mostly for the team.