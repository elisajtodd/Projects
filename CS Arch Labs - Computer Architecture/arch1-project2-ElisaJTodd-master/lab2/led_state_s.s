#include "led.h"

	.arch msp430g2553
	.p2align 1,0

	.data
cnt:
	.word 0			;count

	.data
state:
	.word 0			;state

	.text
JT:
	.word greenOn 		;case 0
	.word greenOff		;case 1

	.data			;rom
	.global led_dim		;visible
led_dim:
	.word 0 		;cnt state red

	mov &state, r12		;state
	cmp #2, &state		;s-2 will not borrow if s>2;
	jc end			
	add r12, r12		;2*state
	mov JT(r12), r0		;mov entry to r0 / program counter
	
greenOn:
	mov.b #1, &green_on	;green on = 1
	mov.b #1, &state	;state = 1
	jmp end				

greenOff:
	mov.b #0, &green_on
	cmp #5, &cnt		;if j != 5 end
	jne end
	mov.b #0, &state
	mov.b #0, &cnt
	
end:	add #1, &cnt		;cnt++
	mov.b #0, red_on	;red off
	mov #1, &led_changed
	call #led_update
	pop r0			;return
