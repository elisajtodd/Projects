# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 4 - BST
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 10/25/2019
Program: -------------------
"""

class BST(object):
    def __init__(self, data, left=None, right=None):  
        self.data = data
        self.left = left 
        self.right = right      
        
def Insert(T,newItem):
    if T == None:
        T =  BST(newItem)
    else:
        if T.data.word > newItem.word: #alphabetical order
            T.left = Insert(T.left, newItem) #before = left
        else:
            T.right = Insert(T.right,newItem) #after = right
    return T

def countNodes(T):
    #counts nodes in bst
    if T == None:
        return 0
    n = 1 + countNodes(T.left) + countNodes(T.right)
    return n

def Height(T):
    #height of binary search tree
    if T == None: 
        return 0
    return 1 + max(Height(T.left), Height(T.right)) #max of either side is max of tree

def Embedding(T,k):
    # Returns embedding of the word k is, or None if k is not in the tree
    if T == None:
        return None
    if k == T.data.word:
        return T.data.emb
    elif T.data.word > k:
        return Embedding(T.left,k)
    return Embedding(T.right,k)