#include <stdio.h>
#include <stdlib.h>
#include "history.h"
#include "tokenizer.h"

List *init_history(){
  List *inList = malloc(sizeof(List *)); //allocates list pointer
  inList->root = NULL;
  return inList;
}

void add_history(List *list, char *str){
  int currId = 1; //counter for id
  Item *new = malloc(sizeof(Item)); //new node
  if(list->root == NULL){
    list->root = new;
  }else{
    struct s_Item *temp = list->root; //variable will help iterate to end of list
    currId++;
    while(temp->next != NULL){ //loop to get to the end
      temp = temp->next;
      currId++;
    }
    temp->next = new;
  }
  new->id = currId;
  new->str = copy_str(str, word_len(str)); //calls copy_str
  new->next = NULL;
}

char *get_history(List *list, int id){
  struct s_Item *temp = list->root;
  while(temp != NULL && temp->id < id){ //finds id or last id
    temp = temp->next;
   }
  if(temp == NULL){ //null pointer if no such id
    return "- ID DOESN'T EXIST\n";
  }
  return temp->str;
}

void print_history(List *list){
  struct s_Item *temp = list->root;
  while(temp != NULL){ //prints nodes
    printf("%i: %s\n", temp->id, temp->str);
    temp = temp->next;
  }
}

void free_history(List *list){
  struct s_Item *temp = list->root;
  while(temp != NULL){
    free(temp->str); //frees rest of str
    free(temp); //frees item
    temp = temp->next;
  }
  free(list); //frees pointer of list
}

/*
int main(){
  List *list = init_history();
  add_history(list, "hello");
  add_history(list, "second string");
  add_history(list, "the third time is the charm");
  add_history(list, "fourth");
  add_history(list, "five strings so far");
  add_history(list, "six");
  add_history(list, "seven eleven");
  add_history(list, "eight");
  add_history(list, "eight nine");
  add_history(list, "ten after nine");
  add_history(list, "eleven");
  add_history(list, "twelve");

  print_history(list);
 
  printf("%s\n", get_history(list, 12));
  free_history(list);
  print_history(list);
}
*/
