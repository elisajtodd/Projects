#include <msp430.h>
#include "p2switches.h"
#include "led.h"
#include "stateMachines.h"
#include "buzzer.h"
#include "lcdtypes.h"
#include "lcddraw.h"
#include "lcdutils.h"
#include "libTimer.h"


char switch_state_down; /* effectively boolean */
char sw_1, sw_2, sw_3, sw_4;
static unsigned char switches_last_reported;
static unsigned char switches_current;

static char
switch_update_interrupt_sense()
{
  char p2val = P2IN;
  switches_current = P2IN & SWITCHES;
  /* update switch interrupt to detect changes from current buttons */
  P2IES |= (switches_current);  /* if switch up, sense down */
  P2IES &= (switches_current | ~SWITCHES); /* if switch down, sense up */
  return p2val;
}

void 
switch_init()
{
  P2REN |= SWITCHES;    /* enables resistors for switches */
  P2IE = SWITCHES;      /* enable interrupts from switches */
  P2OUT |= SWITCHES;    /* pull-ups for switches */
  P2DIR &= ~SWITCHES;   /* set switches' bits for input */
  switch_update_interrupt_sense();
}

/* Returns a word where:
 * the high-order byte is the buttons that have changed,
 * the low-order byte is the current state of the buttons
 */
unsigned int 
p2sw_read() {
  unsigned int sw_changed = switches_current ^ switches_last_reported;
  switches_last_reported = switches_current;
  return switches_current | (sw_changed << 8);
}

/* Switch on P2 (S1) */
void
__interrupt_vec(PORT2_VECTOR) Port_2(){
  if (P2IFG & SWITCHES) {  /* did a button cause this interrupt? */
    P2IFG &= ~SWITCHES;	/* clear pending sw interrupts */
    switch_interrupt_handler();
  }
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
  if(switch_state_down){
    state_advance();
    buzzer_init();
    green_on = 1;
    red_on = 0;
    led_changed = 1;
    led_update();
  }
}
