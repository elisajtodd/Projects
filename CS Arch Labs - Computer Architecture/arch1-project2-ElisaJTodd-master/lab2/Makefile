# makfile configuration
COMMON_OBJECTS  = 
CPU             = msp430g2553
CFLAGS          = -mmcu=${CPU} -I../h
LDFLAGS		= -L/opt/ti/msp430_gcc/include

#switch the compiler (for the internal make rules)
CC              = msp430-elf-gcc
AS              = msp430-elf-as

all: songs.elf 

#additional rules for files
songs.elf: ${COMMON_OBJECTS} p1_interrupt_handler.o wdInterruptHandler.o stateMachines.o led_state_s.o buzzer.o led.o switches.o musicBox.o ../lib/libTimer.a
	${CC} ${CFLAGS} ${LDFLAGS} -o $@ $^

load: songs.elf
	msp430loader.sh songs.elf

clean:
	rm -f *.o *.elf
