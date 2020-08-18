#ifndef buzzer_included
#define buzzer_included

extern int erase[];
extern int color[];
extern int size[];
extern int shape[];
extern int *sound;
extern int i;

void buzzer_init();
void play_notes();
void buzzer_set_period(short cycles);

#endif // included
