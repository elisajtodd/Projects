#include <stdio.h>

int main(){

  char user_in[64];
  
  printf("$");
  printf("%s\nuser_in", fgets(user_in, 64, stdin));
  return 0;
}
