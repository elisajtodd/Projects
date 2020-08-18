#include <msp430.h>
#include "stateMachines.h"
#include "p2switches.h"
#include "buzzer.h"
#include "lcddraw.h"

extern int shape2;
extern int size2;
extern int color3;

void state_advance(){
  
  //define the sound to be played
  if (sw_1){
    sound = &erase[0];
    str = &era[0];
  } 
  if (sw_2){
    sound = &shape[0];
    str = &shp[0];
    shape2++;
  }
  if (sw_3){
    sound = &size[0];
    str = &siz[0];
    size2 +=25;
    size2 = (size2>100) ? 25 : size2;
  }
  if (sw_4){
    sound = &color[0];
    str = &col[0];
    color3++;
    if(color3 > 6){
      color3 = 0;
    }
  }
}
