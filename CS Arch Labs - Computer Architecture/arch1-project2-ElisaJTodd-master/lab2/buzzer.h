#ifndef buzzer_included
#define buzzer_included

extern int susanna[];
extern int letItGo[];
extern int hakuna[];
extern int aladdin[];
extern int *song;
extern int n;
extern int i;

void buzzer_init();
void play_notes();
void buzzer_set_period(short cycles);

#endif // included
