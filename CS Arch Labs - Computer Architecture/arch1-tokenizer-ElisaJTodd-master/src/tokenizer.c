#include <stdio.h>
#include <stdlib.h>

int space_char(char c){
  return (c == '\t' || c == ' ') ? 1 : 0; //truee if space
}

int non_space_char(char c){
  return (c == '\t' || c == ' ') ? 0 : 1; //false if space
}

char *word_start(char *str){
  while(space_char(*str) == 1 ){ //find the first non space
    if(*str=='\0'){ //no word, all space
      return 0;
    }
    str++;
  }
  return str;
}

char *word_end(char *str){
  if (*str=='\0'){ //there is a word
      return 0;
  }
  while (non_space_char(*(str+1)) == 1 && *(str+1) != '\0'){//finds last character in a word
    str++;
  }
  return str;
}

int count_words(char *str){
  int cnt = 0;
  char *p = str;
  while (*(p = word_start(p)) != '\0'){ //gets next word until there are no more
    cnt++;
    p = word_end(p);
    p++;
  }
  return cnt;
}

//extra method
int word_len(char *str){
  int cnt = 0;
  while (*str != '\0'){ //counts until end of string
    cnt++;
    str++;
  }
  return cnt;
}

char *copy_str(char *inStr, short len){
  char *p = malloc(sizeof(char) * (len+1)); //allocates memory for string and zero
  for(int i = 0; i<len; i++){
    *p++ = *inStr++;
  }
  *p = '\0';
  return (p-len);
}

char** tokenize(char* str){
  int words = count_words(str);
  char **p = (char **) malloc(sizeof(char *)*(words+1)); //malloc array of pointers for tokens
  char *c = str; //iterates through words
  int len = 0;
  int i;
  for (i = 0; i<words; i++){ //loop for each word
    char *s = word_start(c);
    char *e = word_end(c);
    len = (e - s) + 1;
    p[i] = copy_str(str, len); // pointer to first str
    str += len;
    c = str = word_start(str);
  }
  return p;
}

void print_tokens(char **tokens){
  for (; *tokens != 0; ++tokens){ //prints until no more tokens
    printf("\t-%s\n", *tokens);
  }
}

void free_tokens(char **tokens){
  int i = 0;
  for(; tokens[i] != 0; ++i){
    free(tokens[i]); //frees tokens
  }
  free(tokens); //frees token pointer
}

/*
int main(){
  char a = ' ';
  char b = 'a';
  char empty[] = "";
  char hello[] = "hello world ";
  char sentence[] = "Hey! Try this sentence";
  char num[] = "21";
  char ab[] = "ab";
  char **tokens;
  
  //space char
  printf("Test space_char ('%c'):\t\t\t%i\n", a, space_char(a));
  printf("Test space_char ('%c'):\t\t\t%i\n", b, space_char(b));

  //non_space char
  printf("Test non_space_char ('%c'):\t\t%i\n", a, non_space_char(a));
  printf("Test non_space_char ('%c'):\t\t%i\n", b, non_space_char(b));

  //word start
  printf("Test word_start (\"%s\"):\t%s\n", hello, word_start(hello));
  printf("Test word_start (\"%s\"):\t\t\t%s\n", num, word_start(num));

  //word end
  printf("Test word_end (\"%s\"):\t\t%s\n", hello, word_end(hello));
  printf("Test word_end (\"%s\"):\t\t\t%s\n", num, word_end(num));

  //count words
  printf("Test count_words (\"%s\"):\t%i\n", hello, count_words(hello));
  printf("Test count_words (\"%s\"):\t\t%i\n", ab, count_words(ab));
  printf("Test count_words (\"\"):\t\t\t%i\n", count_words(empty));
  printf("Test count_words (\"%s\"):\t\t%i\n", num, count_words(num));

  //copy str
  printf("Test copy_str (\"%s\")\t\t\t%s\n", ab, copy_str(ab, 2));
  printf("Test copy_str (\"%s\")\t\t%s\n", hello, copy_str(hello, 11));
    
  //tokenize print
  printf("Test tokenize (\"%s\")\n", num);
  tokens = tokenize(num);
  print_tokens(tokens);
  free_tokens(tokens);

  printf("Test tokenize (\"%s\")\n", hello);
  tokens = tokenize(hello);
  print_tokens(tokens);
  free_tokens(tokens);

  printf("Test tokenize (\"%s\")\n", sentence);
  tokens = tokenize(sentence);
  print_tokens(tokens);
  free_tokens(tokens);
}
*/


