#include <msp430.h>
#include "led.h"
#include "switches.h"
#include "buzzer.h"
#include "stateMachines.h"

unsigned char red_on = 0, green_on = 0;
unsigned char led_changed = 0;

static char redVal[] = {0, LED_RED}, greenVal[] = {0, LED_GREEN};

void led_init()
{
  P1DIR |= LEDS;		// bits attached to leds are output
  switch_state_changed = 1;
  led_update();
}

void led_update(){
  char ledFlags = redVal[1];
  if (led_changed) {
    ledFlags = redVal[red_on] | greenVal[green_on];
  }

  if (switch_state_changed) {
    ledFlags = 0;
    ledFlags |= switch_state_down ? LED_GREEN : 0;//turn on green if switch down
    
    if(switch_state_down){
      green_on = 1;
      red_on = 0;
      buzzer_init();
    }else{
      green_on = 0;
      red_on = 1;
    }
  }
  if(led_changed || switch_state_changed){
    P1OUT &= (0xff - LEDS) | ledFlags; // clear bits for off leds
    P1OUT |= ledFlags;         // set bits for on leds
    switch_state_changed = 0;
  }
}
