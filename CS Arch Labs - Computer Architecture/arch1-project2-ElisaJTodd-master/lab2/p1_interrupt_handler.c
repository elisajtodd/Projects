#include <msp430.h>
#include "switches.h"
#include "buzzer.h"

/* Switch on P2 (S2) */
void
__interrupt_vec(PORT2_VECTOR) Port_2(){
  if (P2IFG & SWITCHES) {	      /* did a button cause this interrupt? */
    P2IFG &= ~SWITCHES;		      /* clear pending sw interrupts o*/
    switch_interrupt_handler();	/* single handler for all switches */
  }
}
