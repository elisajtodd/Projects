# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 4 - BTREE
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 10/25/2019
Program: -------------------
"""
class BTree(object):
    # Constructor
    def __init__(self,data,child=[],isLeaf=True,max_data=5):  
        self.data = data
        self.child = child 
        self.isLeaf = isLeaf
        if max_data <3: #max_data must be odd and greater or equal to 3
            max_data = 3
        if max_data%2 == 0: #max_data must be odd and greater or equal to 3
            max_data +=1
        self.max_data = max_data

def FindChild(T,k):
    # Determines value of c, such that k must be in subtree T.child[c], if k is in the BTree   
    for i in range(len(T.data)):
        if k < T.data[i].word:
            return i
    return len(T.data)
             
def InsertInternal(T,i):
    #Moves through tree to find respective leaf to append data to
    if T.isLeaf:
        InsertLeaf(T,i)
    else:
        k = FindChild(T,i.word)   
        if IsFull(T.child[k]):
            m, l, r = Split(T.child[k]) #splits if found full
            T.data.insert(k,m) 
            T.child[k] = l
            T.child.insert(k+1,r) 
            k = FindChild(T,i.word)  
        InsertInternal(T.child[k],i)   
            
def Split(T):
    #print('Splitting')
    #PrintNode(T)
    mid = T.max_data//2
    if T.isLeaf:
        leftChild = BTree(T.data[:mid],max_data=T.max_data) 
        rightChild = BTree(T.data[mid+1:],max_data=T.max_data) 
    else:
        leftChild = BTree(T.data[:mid],T.child[:mid+1],T.isLeaf,max_data=T.max_data) 
        rightChild = BTree(T.data[mid+1:],T.child[mid+1:],T.isLeaf,max_data=T.max_data) 
    return T.data[mid], leftChild,  rightChild   
      
def InsertLeaf(T,i):
    T.data.append(i)   
    # Traverse through all array elements
    for i in range(len(T.data)):
        # Last i elements are already in place
        for j in range(len(T.data)-1-i):
            # traverse the array from 0 to n-i-1
            # Swap if the element found is greater
            # than the next element
            if T.data[j].word > T.data[j+1].word:
                T.data[j], T.data[j+1] = T.data[j+1], T.data[j]

def IsFull(T):
    return len(T.data) >= T.max_data

def Leaves(T):
    # Returns the leaves in a b-tree
    if T.isLeaf:
        return [T.data]
    s = []
    for c in T.child:
        s = s + Leaves(c)
    return s

        
def Insert(T,i):
    if not IsFull(T):
        InsertInternal(T,i)
    else:
        m, l, r = Split(T)
        T.data =[m]
        T.child = [l,r]
        T.isLeaf = False
        k = FindChild(T,i.word)  
        InsertInternal(T.child[k],i)   
    
def Print(T):
    # Prints data in tree in ascending order
    if T.isLeaf:
        for t in T.data:
            print(t,end=' ')
    else:
        for i in range(len(T.data)):
            Print(T.child[i])
            print(T.data[i],end=' ')
        Print(T.child[len(T.data)])    
         
def PrintD(T,space):
    # Prints data and structure of B-tree
    if T.isLeaf:
        for i in range(len(T.data)-1,-1,-1):
            print(space,T.data[i].word)
    else:
        PrintD(T.child[len(T.data)],space+'   ')  
        for i in range(len(T.data)-1,-1,-1):
            print(space,T.data[i].word)
            PrintD(T.child[i],space+'   ')
            
def countNodes(T):
    #counts nodes in the tree
    if T == None:
        return 0
    elif T.isLeaf:
        return 1
    else:
        n = 1
        for c in T.child:
            n += countNodes(c)
    return n

def Height(T):
    #returns height of btree
    if T == None:
        return 0
    elif T.isLeaf:
        return 1
    else:
        return 1 + Height(T.child[0])
    
def Embedding(T,k):
    # Returns embedding of the word k, or None if k is not in the tree
    for i in T.data:
        if k == i.word:
            return i.emb
    if T.isLeaf:
        return None
    return Embedding(T.child[FindChild(T,k)],k)
    