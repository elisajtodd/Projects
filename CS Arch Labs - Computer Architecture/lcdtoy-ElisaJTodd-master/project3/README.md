# Project 3 : Shape changer

## Description
This program uses the buttons to:

- clear the screen
- change the shape (square, trapezoid, rhombus, hexagon, rectangle, and triangle)
- change the size of the shapes: 25, 50, 75, 100
- change the color of the shapes

- the shape keeps changing if the button keeps being pressed

The program sleeps after when no switch is being pressed after a shape is done
drawing and wakes with a switch press.

## How to run
1. make load - to install the program on the msp430 device
2. make clean - to clean the o files, and the elf file

## What the files do
|File  	   	|Description								|
|---------------|-----------------------------------------------------------------------|
|buzzer		|contains the sounds made when a switch is pressed			|
|lcddraw	|contains the algorithms for drawing the shapes and the strings		|
|lcdutils	|contains the colors to use						|
|led.c		|contains intrsutions to turn led lights on and of			|
|shapeShift	|contains the main to be run, code to draw the shapes, and wdInterrrupt	|
|stateMachines	|handles the switches, commands to be made, and sounds			|
|p2switches	|receives input from 4 switches, calls the draw and leds		|
