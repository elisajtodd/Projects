#include <msp430.h>
#include "stateMachines.h"
#include "switches.h"
#include "buzzer.h"

void state_advance(){
  
  if (sw_1){ /* first button play first song */
    song = &susanna[0];
    n = 32;
  } 
  if (sw_2){ /* second button play second song */
    song = &letItGo[0];
    n = 39;
  }
  if (sw_3){ /* third button play third song */
    song = &hakuna[0];
    n = 38;
  }
  if (sw_4){ /* fourth button play fourth song */
    song = &aladdin[0];
    n = 39; 
  }
}
