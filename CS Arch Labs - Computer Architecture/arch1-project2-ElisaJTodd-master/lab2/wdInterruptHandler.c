#include <msp430.h>
#include "stateMachines.h"
#include "led_state_s.h"
#include "buzzer.h"

void
__interrupt_vec(WDT_VECTOR) WDT(){
  static char second_count = 0;
  if (++second_count == 50 && i<=n) { //note length
    play_notes(); 
    second_count = 0;
  }
  if (i>1 && i <=n){
    led_dim();
  }
}
