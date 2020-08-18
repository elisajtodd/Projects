#include <msp430.h>
#include "libTimer.h"
#include "buzzer.h"
#include "stateMachines.h"
#include "led.h"

int erase[] = {0, 2273, 2025, 0}; //A,B

int color[] = {0, 2273, 1911, 0}; //A,C

int size[] = {0, 2273, 1703, 0}; //A,D

int shape[] = {0, 2273, 1517, 0}; //A,E

int *sound;
int i = 0;

void buzzer_init()
{
    /* 
       Direct timer A output "TA0.1" to P2.6.  
        According to table 21 from data sheet:
          P2SEL2.6, P2SEL2.7, anmd P2SEL.7 must be zero
          P2SEL.6 must be 1
        Also: P2.6 direction must be output
    */
    timerAUpmode();		/* used to drive speaker */
    P2SEL2 &= ~(BIT6 | BIT7);
    P2SEL &= ~BIT7; 
    P2SEL |= BIT6;
    P2DIR = BIT6;		/* enable output to speaker (P2.6) */
    i = 0;
    buzzer_set_period(0);
    play_notes();	/* start buzzing!!! */
}

void play_notes() {
  /* Everytime buzzer_advance_frequency is called, 
     it will increment i by 1, 
     and it will set the period to the current value 
     marked by the array of the respective song*/
  buzzer_set_period(sound[i]);
  i++;
  if(i>3){
    red_on = 1;
    green_on = 0;
    led_changed = 1;
    led_update();
  }
}

void buzzer_set_period(short cycles)
{
  CCR0 = cycles; 
  CCR1 = cycles >> 1;		/* one half cycle */
}
