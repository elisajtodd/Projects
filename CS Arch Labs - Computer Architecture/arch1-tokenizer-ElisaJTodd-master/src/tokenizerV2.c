
#include <stdio.h>
#include <stdlib.h>

int space_char (char c){
  return (c == '\t' || c == ' ') ? 1 : 0;
}

int non_space_char(char c){
  return (c == '\t' || c == ' ') ? 0 : 1;
}

char *word_start(char *str){
  while(space_char(*str) == 1 ){
    str++;
    if(*str=='\0'){
      return 0;
    }
  }
  return str;
}

char *word_end (char *str){
  while (non_space_char(*str) == 1 ){
    if (*str=='\0'){
      return 0;
    }
    str++;
  }
  return str;
}

int count_words (char *str){
  int cnt = 0;
  while (*(str = word_start(str)) != '\0'){
    cnt++;
    if(word_end(str) == 0){
      break;
    }else{
      str = word_end(str);
    }
   }
  return cnt;
}

char *copy_str(char *inStr, short len){
  char *p = malloc(sizeof(char) * (len+1));
  while(*p++ = *inStr++ != '\0');
  return (p-len-1);
}

//char** tokenize(char* str){
//}

void print_tokens(char **tokens){
  
}

void free_tokens(char **tokens){

}

int main (){
  char a = ' ';
  char b = 'a';
  char empty[] = "";
  char hello[] = "hello world";
  char num[] = "21";
  char ab[] = "ab";
  
  printf("Test space_char (' '):\t\t\t%i\n", space_char(a));
  printf("Test space_char ('a'):\t\t\t%i\n", space_char(b));
  printf("Test non_space_char (' '):\t\t%i\n", non_space_char(a));
  printf("Test non_space_char ('a'):\t\t%i\n", non_space_char(b));
  printf("Test word_start (\"hello world\"):\t%s\n", word_start(hello));
  printf("Test word_start (\"21\"):\t\t\t%s\n", word_start(num));
  printf("Test word_end (\"hello world\"):\t\t%s\n", word_end(hello));
  printf("Test word_end (\"21\"):\t\t\t%s\n", word_end(num));
  printf("Test count_words (\"hello world\"):\t%i\n", count_words(hello));
  printf("Test count_words (\"ab\"):\t\t%i\n", count_words(ab));
  printf("Test count_words (\"\"):\t\t\t%i\n", count_words(empty));
  printf("Test copy_str (\"ab\")\t\t\t%s\n", copy_str(ab, 2));
}
