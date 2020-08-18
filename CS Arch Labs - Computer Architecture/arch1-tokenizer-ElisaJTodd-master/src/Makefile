token_test: tokenizer.o history.o interface.o
	cc -o token_test tokenizer.o history.o interface.o

clean:
	rm -f *.o token_test

history.o: tokenizer.h history.h history.c

interface.o: tokenizer.h history.h interface.c
