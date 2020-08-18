## Description

A song plays once when a switch is pressed. When nothing is playing the red
led is on, when a switch is pressed the green light is turned on, and while a
song is playing the green light dims.

This table  shows each file and its functionality

File                    | Description
----------------------  | ------------------------------------------
buzzer.c                | Stores an array for each song for each song to play and iterates through that array and plays the notes.  
led.c                   | Turns red led on when nothing is playing, turns green led on and green off when a switch is pressed
musicBox.c              | Has the main to initialize led, switches and the watchdog
p2_interrupt_handler.c  | Reads switches from P2
stateMachines.c         | Reads the song according to the switch and uses watchdog to cause the green light to dim while a song is playing 
switches.c              | Receives input from any switch pressed
wdInterruptHandler.c    | Uses timer to play notes on buzzer and dim the green light

## How to Use

Type "make load"
