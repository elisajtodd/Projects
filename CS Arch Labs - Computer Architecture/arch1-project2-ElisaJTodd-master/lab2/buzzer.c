#include <msp430.h>
#include "libTimer.h"
#include "buzzer.h"
#include "stateMachines.h"
#include "led.h"

//{2273, 2551, 3405, 3608, 4050, 4545, 5102 , 5406, 6811, 0, 0}; // high to low

int susanna[] = {0, 7644, 6811, 6067, 5102, 5102, 4545, 5102, 6067, 7644, 6811, 6067, 6067, 6811, 7644,
		 6811, 6811, 7644, 6811, 6067, 5102, 5102, 4545, 5102, 6067, 7644, 6811, 6067, 6067, 6811, 6811, 7644}; // Oh! Susanna 32

int letItGo[] = {0, 2863, 2551, 2408, 2408, 0,
		 3214, 3214, 2145, 2145, 0,
		 2408, 2863, 2863, 2863, 2863, 2551, 2408, 2408, 0,
		 2863, 2551, 2408, 2408, 0,
		 3214, 3214, 2145, 2145, 0,
		 2408, 2145, 1911, 1911, 1804, 1911, 2145, 2145, 2408}; //Let it go 39

int hakuna[] = {0, 3214, 3214, 3214, 3405, 3214, 3405, 3822, 3822, 3822, 3822,
		4545, 3822, 3405, 3214, 3405, 3822, 3822, 3822, 0,
		3822, 3214, 3214, 3405, 3214, 3405, 3822, 3822, 3822, 3822,
		   4545, 3822, 3405, 3822, 3405, 3405, 3405, 0}; //Hakuna Matata 25

int aladdin[] = {0, 2703, 2551, 2025, 2273, 2273, 2273, 2273, 0, 
		 2703, 2551, 2025, 2273, 3034, 2552, 2703,2703,
		 2803, 2551, 2273, 1804, 2025, 2273, 3405, 1804, 3405, 2273,
		 3405, 2408, 3034, 3405, 3034, 2551, 2703, 3405, 1804, 3405, 3405, 3405 }; //A whole new world
int *song;
int i = 0;
int n = 1;

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
    play_notes();	/* start buzzing!!! */
}

void play_notes() {
  /* Everytime buzzer_advance_frequency is called, 
     it will increment i by 1, 
     and it will set the period to the current value 
     marked by the array of the respective song*/
  buzzer_set_period(song[i]);
  i++;
  if(i>n){
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
