#ifndef switches_included
#define switches_included

#include "msp430.h"

#define SW1 BIT0       	/* switch1 is p2.0 */
#define SW2 BIT1       	/* switch1 is p2.1 */
#define SW3 BIT2       	/* switch1 is p2.2 */
#define SW4 BIT3       	/* switch1 is p2.3 */
#define SWITCHES (SW1 | SW2 | SW3 | SW4)/* 4 switches on this board */

void switch_interrupt_handler();
unsigned int p2sw_read();
extern char sw_1, sw_2, sw_3, sw_4;
extern char switch_state_down; /* effectively boolean */
unsigned int p2sw_read();
void switch_init();

#endif // included
