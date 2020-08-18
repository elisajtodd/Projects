# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 1 - Recursion
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 9/5/2019
Program: This program asks the user to input a word and then prints all the 
anagrams of that word. This program runs 3 versions of the function, optimized 
as it goes to the next. 
The first function (anagram) scrambles the given word to all its possibilities and then 
compares them with the words on file words_alpha to finally show the resulting
words.
The second function (anagramOp1) adds an if statement checking that letters
that have duplicates are only scrambled once.
The third function (anagramOp2) is complimented by the set of Prefixes,
obtained with a fourth function (getPrefix), in which it stops scrambling a 
word if the part scrambled so far is not a prefix of any word.
"""
import time

#First function
def anagram(r, s):
    global wordSet
    global words_result
    global name
    # Base case: All letters were used
    if len(r) == 0:
        if s in wordSet and s != name and s not in words_result:
            words_result.append(s)
    else:
        # Recursive case
        # move the next letter from remaining to scrambled
        for i in range(len(r)):
            # The letter at index i will be scrambled
            scrambled = r[i]
            # Remove letter to scramble from remaining letters list
            remaining = r[:i] + r[i+1:]
            # Scramble letter
            anagram(remaining,s + scrambled)

#Function with first optimization
def anagramOp1(r, s):
    global letterSet
    global wordSet
    global words_result
    global name
    check = str(r) + str(s)
    letterSet.add(check)
    # Base case: All letters were used
    if len(r) == 0:
        if s in wordSet and s != name:
            words_result.append(s)
    else:
        # Recursive case
        # move the next letter from remaining to scrambled
        for i in range(len(r)):
            # The letter at index i will be scrambled
            scrambled = r[i]
            # Remove letter to scramble from remaining letters list
            remaining = r[:i] + r[i+1:]
            # Scramble letter
            check = str(remaining) + str(s) + str(scrambled)
            if (check) not in letterSet:
                anagramOp1(remaining, s + scrambled)
            
#Function with second optimization
def anagramOp2(r, s):
    global wordSet
    global words_result
    global name
    global prefixSet
    # Base case: All letters were used
    if len(r) == 0:
        if s in wordSet and s != name:
            words_result.append(s)
    else:
        # Recursive case
        # move the next letter from remaining to scrambled
        if (s in prefixSet):
            for i in range(len(r)):
                # The letter at index i will be scrambled
                scrambled = r[i]
                # Remove letter to scramble from remaining letters list
                remaining = r[:i] + r[i+1:]
                # Scramble letter
                anagramOp2(remaining, s + scrambled)

def anagramOp3(r, s):
    global letterSet
    global wordSet
    global words_result
    global name
    global prefixSet
    # Base case: All letters were used
    if len(r) == 0:
        if s in wordSet and s != name:
            words_result.append(s)
    else:
        # Recursive case
        # move the next letter from remaining to scrambled
        if (s in prefixSet):
            check = str(r) + str(s)
            letterSet.add(check)
            for i in range(len(r)):
                # The letter at index i will be scrambled
                scrambled = r[i]
                # Remove letter to scramble from remaining letters list
                remaining = r[:i] + r[i+1:]
                # Scramble letter
                check = str(remaining) + str(s) + str(scrambled)
                if (check) not in letterSet:
                    anagramOp3(remaining, s + scrambled)

#Aux to optimization 2, makes prefix set
def getPrefixes(L):
    preSet = set() #set will store prefixes
    for i in range(len(L)):  #words in list
        for j in range(len(L[i])):    #length of word
            preSet.append(L[i][:j])    #adds prefixes
    return preSet

#opens text file and stores it in a set
wordSet = set(open("words_alpha.txt").read().split()) 

#creates set of prefixes from wordSet
prefixSet = getPrefixes(wordSet)

#creates set for scrambled letters of the word
letterSet = set()

#Boolean for menu
stay = True

#loop for menu
while stay:
    #asks the user for the word
    name = input('Enter a word to get anagrams\n\t\t\tOR\nAn empty string to finish the program: ') 
    
    #To exit the program
    if name == (''):
        stay = False
        print('Bye! Thanks for using this program!')
        
    #if input was not a real word
    else:
        if name not in wordSet:
            print('That is not a real word. Try again.')
           
        #To continue the program
        else:
            #order the string in alfabetical order so that the function prints in alfabetical orde
            nameAlf = sorted(name) #stores each letter on a list
            words_result = [] # list that will store anagrams of the word
            start = time.time()
            anagram(nameAlf, '' ) #call to function
            end = time.time()
            #Show results:
            print('\n*********FIRST FUNCTION:*********\nThe word "' + name + '" has the following ' + str(len(words_result)) + ' anagrams:')
            print(*words_result, sep = "\n")
            print('It took ' + str(end-start) + ' seconds to find the anagrams.')
            words_result.clear()
            
            #OPTIMIZATION 1
            start = time.time()
            anagramOp1(nameAlf, '') #call to function
            end = time.time()
            #Show results:
            print('\n*********OPTIMIZATION #1:*********\nThe word "' + name + '" has the following ' + str(len(words_result)) + ' anagrams:')
            print(*words_result, sep = "\n")
            print('It took ' + str(end-start) + ' seconds to find the anagrams.')
            words_result.clear() #reset
            letterSet.clear() #reset 
            
            #OPTIMIZATION 2
            start = time.time()
            anagramOp2(nameAlf, '' ) #call to function
            end = time.time()
            #Show results:
            print('\n*********OPTIMIZATION #2:*********\nThe word "' + name + '" has the following ' + str(len(words_result)) + ' anagrams:')
            print(*words_result, sep = "\n")
            print('It took ' + str(end-start) + ' seconds to find the anagrams.')
            words_result.clear() #reset
            
            #OPTIMIZATION 1 & 2
            start = time.time()
            anagramOp3(nameAlf, '' ) #call to function
            end = time.time()
            #Show results:
            print('\n*********OPTIMIZATION #2 AND #3:*********\nThe word "' + name + '" has the following ' + str(len(words_result)) + ' anagrams:')
            print(*words_result, sep = "\n")
            print('It took ' + str(end-start) + ' seconds to find the anagrams.')
            words_result.clear() #reset
            letterSet.clear() #reset