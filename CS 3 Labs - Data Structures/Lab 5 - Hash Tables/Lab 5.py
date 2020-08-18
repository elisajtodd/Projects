# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 5 - Hash Tables
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/01/2019
Program: Stores into hash tables linearly or by chaining
"""
import hash_linear as htlp
import hash_chain as htc
import bst 
import btree
import WordEmbedding
import numpy as np
import time

#files to read
embed_file = "glove.6B.50d.txt" #embeddings file
word_file = "words.txt"         #words with similarities file

#reading into a Hash Table with Chaining
def readIntoHashChain(size, choice):
    h = htc.HashTableChain(size)
    if choice == "1":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertString(n)
                content = ef.readline()
    elif choice == "2":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertAscii(n)
                content = ef.readline()
    elif choice == "3":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertPAscii(n)
                content = ef.readline()
    elif choice == "4":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertSAscii(n)
                content = ef.readline()
    elif choice == "5":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertRecursive(n)
    elif choice == "6":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertFE(n)
                content = ef.readline()
    print("Done")
    return h
    
#reading into a Hash Table with Linear Probing
def readIntoHashLP(size, choice):
    h = htlp.HashTableLP(size)
    if choice == "1":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertS(n)
                content = ef.readline()
                
    elif choice == "2":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertAscii(n)
                content = ef.readline()
    elif choice == "3":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertPAscii(n)
                content = ef.readline()
                
    elif choice == "4":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertSAscii(n)
                content = ef.readline()
    elif choice == "5":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertRecursive(n)
                content = ef.readline()
    elif choice == "6":
        with open(embed_file, encoding="utf8") as ef:
            content = ef.readline()
            #goes through every line on the file
            cnt = 0
            for i in range(10000):
                word = content.split()
                #creates Word Embedding with information from file
                n = WordEmbedding.WordEmbedding(word[0], word[1:])
                #inserts on bst
                h.insertFE(n)
                cnt += 1
                if cnt % 10000 == 0:
                    print(cnt)
                content = ef.readline()
    print("Done")
    return h

#similarities HT Chaining
def similaritiesHTC(T,c):
    with open(word_file) as wf:
        content = wf.readline().split()
        #reads every line in the file
        while content:
            #separates words
            word1 = T.Embedding(T,content[0],c)
            word2 = T.Embedding(T,content[1],c)
            #returns similarities with similarities function
            sim = (np.dot(word1,word2))/(np.linalg.norm(word1)*np.linalg.norm(word2))
            #Prints values found
            print("Similarity ", content, " = ", sim)
            content = wf.readline().split()
    return

#similarities HT Linear Probing
def similaritiesHTLP(T,c):
    with open(word_file) as wf:
        content = wf.readline().split()
        #reads every line in the file
        while content:
            #separates words
            word1 = T.Embedding(T,content[0],c)
            word2 = T.Embedding(T,content[1],c)
            #returns similarities with similarities function
            sim = (np.dot(word1,word2))/(np.linalg.norm(word1)*np.linalg.norm(word2))
            #Prints values found
            print("Similarity ", content, " = ", sim)
            content = wf.readline().split()
    return
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
        while content:
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
    option = input("Choose table implementation:\n\t1)Binary Search Tree\n\t2)B-Tree\n\t3)Hash Table Chaining\n\t4)Hash Table Linear Probing\n\t5)Exit\n")
    
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
    
    elif (option == "3"):
        print("============ H.T. CHAINING ============")
        size = int(input("What size do you want the table?\n"))
        choice = (input("Which way to store?\n\t1)length of string\n\t2)ascii value\n\t3)product of ascii\n\t4)sum of ascii\n\t5)recursive formula\n\t6)First embedding\n"))
    
        #call to method and time 
        start = time.time()
        table = readIntoHashChain(size, choice)
        end = time.time()
        print("Time elapsed building Hash Table with Chaining (with size = ", size, "): "+"{:.2f}".format(end-start) + " seconds.")
        
        #display table option
        if(input("Do you want to print the table?\n\t1)Yes\n\t2)No\n") == "1"):
            table.print_table()
            
        #similarities
        print("Reading word file to determine similarities...")
        start = time.time()
        similaritiesHTC(table, choice)
        end = time.time()
        print("Running time for HT query processing (with size = ", size, "): "+"{:.2f}".format(end-start) + " seconds.")
    
    elif (option == "4"):
        print("========= H.T. LINEAR PROBING =========")
        size = int(input("What size do you want the table?\n"))
        choice = (input("Which way to store?\n\t1)length of string\n\t2)ascii value\n\t3)product of ascii\n\t4)sum of ascii\n\t5)recursive formula\n\t6)First embedding\n"))
    
        #call to method and time
        start = time.time()
        table = readIntoHashLP(size, choice)
        end = time.time()
        print("Time elapsed building Hash Table with Linear Probing (with size = ", size, "): "+"{:.2f}".format(end-start) + " seconds.")
    
        #display table option
        if(input("Do you want to print the table?\n\t1)Yes\n\t2)No\n") == "1"):
            table.print_table()
        
        #similarities
        print("Reading word file to determine similarities...")
        start = time.time()
        similaritiesHTLP(table,choice)
        end = time.time()
        print("Running time for HT query processing (with size = ", size, "): "+"{:.2f}".format(end-start) + " seconds.")
            
    #Exit
    elif (option == "5"):
        print("Thank you for using this program! Goodbye!")
        stay = False
    else:
        print("Choose 1, 2, 3, 4 or 5.")