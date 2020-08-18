# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 4 - Binary Search Trees and BTrees
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 10/25/2019
Program: --------------------
"""
import bst 
import btree
import WordEmbedding
import numpy as np
import time

#files to read
embed_file = "glove.6B.50d.txt" #embeddings file
word_file = "words.txt"         #words with similarities file

#reading into a binary search tree
def readIntoBST():
    tree = None
    with open(embed_file, encoding="utf8") as ef:
        content = ef.readline()
        #goes through every line on the file
        while content:
            word = content.split()
            #creates Word Embedding with information from file
            n = WordEmbedding.WordEmbedding(word[0], word[1:])
            #inserts on bst
            tree = bst.Insert(tree, n)
            content = ef.readline()
    print("Done")
    return tree
    
#reading into a BTree
def readIntoBTree(max):
    tree = btree.BTree([],max_data=max)
    with open(embed_file, encoding="utf8") as ef:
        content = ef.readline()
        #goes through every line on the file
        #while content:
        for i in range(150000):
            word = content.split()
            #creates Word Embedding with information from file
            n = WordEmbedding.WordEmbedding(word[0], word[1:])
            #inserts on btree
            btree.Insert(tree, n)
            btree.Print
            content = ef.readline()
    print("Done")
    return tree

#prints bst in ascending order (inorder)
def printTree(T):
    if T.left != None:
        printTree(T.left)
    print(T.data.word, end = ' ')
    if T.right != None:
        printTree(T.right)

#finds similarities between words stored in a BST
def similaritiesBST(T):
    with open(word_file) as wf:
        content = wf.readline().split()
        #reads every line in the file
        while content:
            #separates words
            word1 = bst.Embedding(T,content[0])
            word2 = bst.Embedding(T,content[1])
            #returns similarities with similarities function
            sim = (np.dot(word1,word2))/(np.linalg.norm(word1)*np.linalg.norm(word2))
            #Prints values found
            print("Similarity ", content, " = ", sim)
            content = wf.readline().split()
    return

#finds similarities between words stored in a BTree
def similaritiesBTree(T):
    with open(word_file) as wf:
        content = wf.readline().split()
        #reads every line in the file
        while content:
            word1 = btree.Embedding(T,content[0])
            word2 = btree.Embedding(T,content[1])
            #returns similarities with similarities function
            sim = (np.dot(word1,word2))/(np.linalg.norm(word1)*np.linalg.norm(word2))
            #prints values found
            print("Similarity ", content, " = ", sim)
            content = wf.readline().split()
    return

stay = True
#looping until exit
while(stay):
    option = input("Choose table implementation:\n\t1)Binary Search Tree\n\t2)B-Tree\n\t3)Exit\n")
    
    #BST option
    if (option == "1"):
        print("========= BINARY SEARCH TREE =========")
        
        #call to method and time
        start = time.time()
        tree = readIntoBST()
        end = time.time()
        
        #results
        print("Number of nodes: ", bst.countNodes(tree))
        print("Height: ", bst.Height(tree))
        print("Time elapsed building BST: "+"{:.2f}".format(end-start) + " seconds.")
        
        #display tree option
        if(input("Do you want to print the stored tree?\n\t1)Yes\n\t2)No\n") == "1"):
            printTree(tree)
            
        #similarities
        print("Reading word file to determine similarities...")
        start = time.time()
        similaritiesBST(tree)
        end = time.time()
        print("Running time for binary search tree query processing: "+"{:.2f}".format(end-start) + " seconds.")
        
    #BTree option
    elif (option == "2"):
        print("=============== B-TREE ===============")
        max = int(input("Input the maximum number of items to store in a node:"))
        
        #call to method and time
        start = time.time()
        tree = readIntoBTree(max)
        end = time.time()
        
        #results
        print("Number of nodes: ", btree.countNodes(tree))
        print("Height: ", btree.Height(tree))
        print("Time elapsed building BTree (with max_items = ", max, "): "+"{:.2f}".format(end-start) + " seconds.")
       
        #display tree option
        if(input("Do you want to print the stored tree?\n\t1)Yes\n\t2)No\n") == "1"):
            btree.PrintD(tree, "")
            
        #similarities
        print("Reading word file to determine similarities...")
        start = time.time()
        similaritiesBTree(tree)
        end = time.time()
        print("Running time for B-tree query processing (with max_items = ", max, "): "+"{:.2f}".format(end-start) + " seconds.")
        
    #Exit
    elif (option == "3"):
        print("Thank you for using this program! Goodbye!")
        stay = False
    else:
        print("Choose 1, 2 or 3.")