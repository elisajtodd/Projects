#include <msp430.h>
#include "led.h"

void led_dim(){
  static int cnt = 0;
  static char state = 0; //red or green
  red_on = 0; //turn off red

  switch (state) {
  case 0://on for 1
    green_on = 1;
    state = 1;
    break;
  case 1: //off for 5
    green_on = 0;
    if(cnt==5){
      state = 0;
      cnt = 0;
    }
    break;
  }
  cnt++;
  led_changed = 1;
  led_update();
}
