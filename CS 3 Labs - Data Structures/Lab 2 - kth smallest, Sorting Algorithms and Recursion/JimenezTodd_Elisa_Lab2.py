# -*- coding: utf-8 -*-
# =============================================================================
# 1"""
# COURSE: CS 2302 Data Structures
# AUTHOR: Elisa Jimenez Todd
# ASSIGNMENT: Lab 2: Sorting
# INSTRUCTOR: Olac Fuentes
# TA: Anindita Nath
# # =============================================================================
# # DATE: 9/19/2019
# =============================================================================
# 
# Program description:This program finds the kth smallest element in a list. If k==0, select(L,k) returns the
# smallest element in list L, if k==1 it returns the second smallest, and so on. This lets the user decide
# which method to use to fin the kth element. The user inputs the size of the list and the program randomly
# generates one.
# 1. select bubble(L,k). Uses bubble sort
# 2. select quick(L,k). Uses quick sort
# 3. select modified quick(L,k). Uses quick sort but does not finish the sorting in order to bring back the
# kth element. It only sorts where the k will be.
# 4. stack quick(L, k). uses stacks on the quicksort instead of recursion.
# 5. while modified quick(L,k). Uses only a while loop to sort. No recursion or stacks.
# """
# =============================================================================
import math
import random
import time

#PART ONE
#Find smallest kth with bubble sort
def select_bubble(L,k):
    if k>=len(L):    #changing kth number as it is out of bounds
        print('k is larger than the list, returning largest element instead: ')
        k = len(L)-1
    if len(L) == 0: #nothing in the list to return
        return math.inf
    #bubble sort compares every pair of numbers and rearranges them
    for i in range(len(L)):
        for j in range(0, len(L)-i-1):
            if (L[j]>L[j+1]):
                L[j], L[j+1] = L[j+1], L[j]
    print('Result: ', end ='')
    return L[k]

#With quick sort
def select_quick(L,k):
    if len(L) == 0:
        return math.inf
    if k>=len(L):    #changing kth number as it is out of bounds
        print('k is larger than the list, returning largest element instead: ')
        k = len(L)-1
    quicksort(L,0,len(L)-1)
    print ('Result: ', end='')
    print(L)
    return L[k]

def quicksort(L,l,h):
    # base case
    if l >= h:  
        return
    # range
    low = l 
    high = h
    #last element in range
    pivot = L[h]
    #new height
    h -= 1
    #loop to make swaps
    while l < h:
        #move low index
        while L[l]<pivot and l != h:
            l += 1
        #move right index
        while L[h]>=pivot and l != h:
            h -= 1
        #swap
        L[l], L[h] = L[h], L[l]
        #next indices
        if (l != h):
            l +=1
            h -=1
    #replace pivot
    if L[l]<pivot:
        l += 1
    L[high], L[l] = L[l], L[high]
    #partition 
    quicksort(L,low,l-1)
    quicksort(L,l+1, high)

#with modified quick sort
def select_modified_quick(L,k):
    if len(L) == 0:
        return math.inf
    if k>=len(L):    #changing kth number as it is out of bounds
        print('k is larger than the list, returning largest element instead: ')
        k = len(L)-1
    modified_partition(L,0,len(L)-1,k)
    print ('Result: ', end='')
    return L[k]

def modified_partition(L,l,h,k):
    # base case
    if l >= h:  
        return
    # range
    low = l 
    high = h
    #last element in range
    pivot = L[h]
    #new height
    h -= 1
    #loop to make swaps
    while l < h:
        #move low index
        while L[l]<pivot and l != h:
            l += 1
        #move right index
        while L[h]>=pivot and l != h:
            h -= 1
        #swap
        L[l], L[h] = L[h], L[l]
        #next indices
        if (l != h):
            l +=1
            h -=1
    #replace pivot
    if L[l]<pivot:
        l += 1
    L[high], L[l] = L[l], L[high]
    #partition 
    if l == k: #k is already in its place so it does not need to do anything else
        return
    elif l > k:     #sort lower half if k is in the lower half
        modified_partition(L,low,l-1,k) 
    else:           #or sort higher half if k is in the higher half
        modified_partition(L,l+1, high,k)

#PART TWO
#quicksort with stacks
def stack_quicksort(L,k):
    if len(L) == 0:
        return math.inf
    if k>=len(L):    #changing kth number as it is out of bounds
        print('k is larger than the list, returning largest element instead: ')
        k = len(L)-1
    qs2(L,0,len(L)-1) #call to actual stack quicksort
    print ('Result: ', end='')
    print(L)
    return L[k]

def qs2(L, l, h):
    #Auxiliary stack will store 'activation records'
    auxL = []
    #push initial values of l and h to stack (start and end)
    auxL.append(l)
    auxL.append(h)
  
    #While there are no more instructions on aux stack
    while (len(auxL) > 0):
        #Pop h and l 
        h = auxL.pop() 
        l = auxL.pop()
        #Set pivot element at its correct position in the list
        p = part2(L, l, h)
        #If there are elements on left side of pivot, then push left side to stack (activation record) 
        if (p - 1 > l):
            auxL.append(l)
            auxL.append(p - 1) 
  
        #If there are elements on right side of pivot, #then push right side to stack  (activation record) 
        if (p + 1 < h):
            auxL.append(p + 1) 
            auxL.append(h)
    return

def part2(L, l, h):
    x = L[h] 
    i = (l - 1);
    for j in range(l , h ):
        if L[j] <= x:
            i = i +1 
            L[i], L[j] = L[j], L[i]
    L[i+1], L[h]  = L[h], L[i+1]
    return (i+1)

#Quicksort with while no recursion
def while_modified_quick(L,k):
    if len(L) == 0:
        return math.inf
    if k>=len(L):    #changing kth number as it is out of bounds
        print('k is larger than the list, returning largest element instead: ')
        k = len(L)-1
    print ('Result: ', end='')
    return qs3(L,k)

def qs3(L,k):
    p = -1  #pivot index
    s = 0   #index to make next pivot
    while p!=k:
        p = part3(L, 0, len(L)-1, s)
        if p<k: #goes ahead on list
            s = p + 1
        else:   #goes to the next index
            s = s + 1        
        if s==len(L):
            s = 0
    return L[k]
    
def part3(L, l, h, s):
    L[s], L[h] = L[h], L[s] #swap s to be the pivot
    x = L[h]    #last index is pivot
    i = (l - 1);    #i will be l next, and then keep adding
    for j in range(l , h ):
        if L[j] <= x:
            i = i +1 
            L[i], L[j] = L[j], L[i] #swap values
    L[i+1], L[h]  = L[h], L[i+1] #swap values
    return (i+1) #returns pivot index

#creates a random list of a size the user inputs
def makeList(size):
    L = []
    for i in range(size):
        L.append(random.randint(-99,99))
    return L

#Default print to test a select
def printTest():
    print('What size will the list be?', end = '')
    size = input('Size: ')
    L = makeList(int(size))
    print('Testing list:', end = '')
    print(L)
    return L

#MAIN
stay = True
#Show menu again
while stay:
    
    print('What sort do you want to test?')
    print('\t1) Bubble\n\t2) Quick\n\t3) Modified quick\n\t4) Stack quick\n\t5) While modified quick\n\t6) Exit')
    whatSort = input('Option: ')#stores option
       
    #bubble select
    if whatSort.upper() == 'BUBBLE' or whatSort == '1':
        print('\n————————TESTING BUBBLE SELECT————————')
        L= printTest()
        k = int(input('What kth smallest number? '))
        start = time.time()
        print(select_bubble(L,k))
        end = time.time()
        print('Time executing bubble sort: ' + str(end-start) + '\n_____________________________________\n')
        
    #quick select    
    elif whatSort.upper() == 'QUICK' or whatSort == '2':
        print('\n————————TESTING QUICK SELECT————————')
        L= printTest()
        k = int(input('What kth smallest number? '))
        start = time.time()
        print(select_quick(L,k))
        end = time.time()
        print('Time executing quick sort: ' + str(end-start) + '\n_____________________________________\n')
        
    #modified quick select    
    elif whatSort.upper() == 'MODIFIED QUICK' or whatSort == '3':
        print('\n————TESTING MODIFIED QUICK SELECT————')
        L= printTest()
        k = int(input('What kth smallest number? '))
        start = time.time()
        print(select_modified_quick(L,k))
        end = time.time()
        print('Time executing modified quick sort: ' + str(end-start) + '\n_____________________________________\n')
        
    #stack quick sort  
    elif whatSort.upper() == 'STACK QUICK' or whatSort == '4':
        print('\n————TESTING STACK QUICK SELECT————')
        L= printTest()
        k = int(input('What kth smallest number? '))
        start = time.time()
        print(stack_quicksort(L,k))
        end = time.time()
        print('Time executing stack quick sort: ' + str(end-start) + '\n_____________________________________\n')
    
    #modified quick select    
    elif whatSort.upper() == 'WHILE MODIFIED QUICK' or whatSort == '5':
        print('\n————TESTING WHILE MODIFIED QUICK SELECT————')
        L= printTest()
        k = int(input('What kth smallest number? '))
        start = time.time()
        print(while_modified_quick(L,k))
        end = time.time()
        print('Time executing while modified quick sort: ' + str(end-start) + '\n_____________________________________\n')
    #exit program
    elif whatSort.upper() == 'EXIT' or whatSort == '6':
        stay = False #to exit the loop
        print('Goodbye! Thank you for using this program.')
    else:
        print('Please enter a number from 1-4 or type one of the options')
        stack_quicksort([1],3)