#include <stdio.h>
#include <stdlib.h>
#include "history.h"
#include "tokenizer.h"

//asks user for input, tokenizes and adds to linked list
void add_token(char *input, List *list){
    printf("Intput your string:\n$");
    fgets(input, 100, stdin);
    add_history(list, input); //add to list
    char **tokens = tokenize(input); //tokenize
    print_tokens(tokens);
    free_tokens(tokens);
 }

//shows history, shows tokens chosen
void see_history(char *input, List *list){
  print_history(list);
  printf("Write the number of the token you want to see.\n!");
  fgets(input, 64, stdin);
  char *s = get_history(list, atoi(input)); //gets string
  if(*s == '-'){
    printf("\033[0;31m"); //colors text red
    printf("%i %s", atoi(input), s);
    printf("\033[0m"); //reset color
  }else{
  printf("Choice: %s\n", s);
  char **tokens = tokenize(s); //tokenizes again
  print_tokens(tokens);
  free_tokens(tokens);
  }
}
int main(){
  char user_in[100];
  List *list = init_history();
  int loop = 1;

  printf("Welcome to the tokenizer!\n");

  while(loop){ //loops
    printf("Choose what you want to do:\n\t1) Add string to tokenize\n\t2) View history\n\t3) Exit\n");
    printf("$");
    fgets(user_in, 100, stdin); //option input
    if(*user_in == '1'){ //add string
      add_token(user_in, list);
    }else if (*user_in == '2'){ //show history
      see_history(user_in, list);
    }else{
      loop = 0; //exit loop
    }
  }
  free_history(list);
}
