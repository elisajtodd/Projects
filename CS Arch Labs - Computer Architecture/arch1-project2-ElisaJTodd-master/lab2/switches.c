#include <msp430.h>
#include "switches.h"
#include "led.h"
#include "switches.h"
#include "stateMachines.h"

char switch_state_down, switch_state_changed; /* effectively boolean */
char sw_1, sw_2, sw_3, sw_4;

static char 
switch_update_interrupt_sense()
{
  char p2val = P2IN;
  /* update switch interrupt to detect changes from current buttons */
  P2IES |= (p2val & SWITCHES);	/* if switch up, sense down */
  P2IES &= (p2val | ~SWITCHES);	/* if switch down, sense up */
  return p2val;
}

void 
switch_init()			/* setup switch */
{  
  P2REN |= SWITCHES;		/* enables resistors for switches */
  P2IE = SWITCHES;		/* enable interrupts from switches */
  P2OUT |= SWITCHES;		/* pull-ups for switches */
  P2DIR &= ~SWITCHES;		/* set switches' bits for input */
  switch_update_interrupt_sense();
}

void
switch_interrupt_handler()
{
  char p1val = switch_update_interrupt_sense();
  sw_1 = (p1val & SW1) ? 0 : 1;
  sw_2 = (p1val & SW2) ? 0 : 1;
  sw_3 = (p1val & SW3) ? 0 : 1;
  sw_4 = (p1val & SW4) ? 0 : 1; // 0 when switch down
  switch_state_down = (sw_1 || sw_2 || sw_3 || sw_4); //any switch pressed
  state_advance();
  switch_state_changed = 1;
  led_update();
}
