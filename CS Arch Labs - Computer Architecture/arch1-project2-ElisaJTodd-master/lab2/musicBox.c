#include <msp430.h>
#include "libTimer.h"
#include "led.h"
#include "switches.h"
#include "buzzer.h"
#include "stateMachines.h"

void main(void) 
{ 
  configureClocks();
  
  led_init(); // enable leds
  switch_init(); // enable buttons

  enableWDTInterrupts(); //timer for songs

  or_sr(0x18);  // CPU off, GIE on
} 
