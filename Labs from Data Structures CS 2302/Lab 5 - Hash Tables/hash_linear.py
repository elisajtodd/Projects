# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 5 - Hash Tables Linear Probing
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/01/2019
Program: --------------------
"""
import numpy as np

class HashTableLP(object):
    # Builds a hash table of size 'size', initilizes items to -1 (which means empty)
    # Constructor
    def __init__(self,size):  
        self.item = np.zeros(size,dtype=np.object)-1
            
    def insertS(self,k):
        # Inserts k in table unless table is full
        # Returns the position of k in self, or -1 if k could not be inserted
        for i in range(len(self.item)): #Despite for loop, running time should be constant for table with low load factor
            pos = (len(k.word)+i)%len(self.item) 
            if isinstance(self.item[pos], int):
                self.item[pos] = k
                return pos
        return -1
    
    def insertAscii(self,k):
        # Inserts k in table unless table is full
        # Returns the position of k in self, or -1 if k could not be inserted
        for i in range(len(self.item)): #Despite for loop, running time should be constant for table with low load factor
            pos = (ord(k.word[0])+i)%len(self.item) 
            if isinstance(self.item[pos], int):
                self.item[pos] = k
                return pos
        return -1
    
    def hPA(self,k, i):
        return (ord(k.word[0])*ord(k.word[-1])+i)%len(self.item)  
            
    def insertPAscii(self,k):
        # Inserts k in table unless table is full
        # Returns the position of k in self, or -1 if k could not be inserted
        for i in range(len(self.item)): #Despite for loop, running time should be constant for table with low load factor
            pos = self.hPA(k,i)
            if isinstance(self.item[pos], int):
                self.item[pos] = k
                return pos
        return -1
    
    def hSA(self,k,j):
        sum=0
        for i in k:
            sum += ord(i)
        return (sum+j)%len(self.item) 
            
    def insertSAscii(self,k):
        # Inserts k in table unless table is full
        # Returns the position of k in self, or -1 if k could not be inserted
        for i in range(len(self.item)): #Despite for loop, running time should be constant for table with low load factor
            pos = self.hSA(k.word,i)
            if isinstance(self.item[pos], int):
                self.item[pos] = k
                return pos
        return -1
    
    def hR(self,k,i):
        if len(k) == 0:
            return 1            
        return ((ord(k[0]) + 255*self.hR(k[1:],i))+i)%len(self.item)
            
    def insertRecursive(self,k):
        # Inserts k in table unless table is full
        # Returns the position of k in self, or -1 if k could not be inserted
        for i in range(len(self.item)): #Despite for loop, running time should be constant for table with low load factor
            pos = self.hR(k.word,i)
            if isinstance(self.item[pos], int):
                self.item[pos] = k
                return pos
        return -1
    
    def hFE(self,k,i):
        return int((round(k.emb[0]*10000)+i)%len(self.item))
        
    def lookFe(self,k):
        for i in self.item:
            if i.word == k:
                    return i.emb
        return None    
        
    def insertFE(self,k):
        # Inserts k in table unless table is full
        # Returns the position of k in self, or -1 if k could not be inserted
        for i in range(len(self.item)): #Despite for loop, running time should be constant for table with low load factor
            pos = self.hFE(k,i)
            if isinstance(self.item[pos], int):
                self.item[pos] = k
                return pos
        return -1
    
    def find(self,k):
        # Returns the position of k in table, or -1 if k is not in the table
        for i in range(len(self.item)):
            pos = self.h(k+i)
            if self.item[pos] == k:
                return pos
            if self.item[pos] == -1:
                return -1
        return -1
     
    def delete(self,k):
        # Deletes k from table. It returns the position where k was, or -1 if k was not in the table
        # Sets table item where k was to -2 (which means deleted)
        f = self.find(k)
        if f >=0:
            self.item[f] = -2
        return f
    
    def h(self,k):
        return k%len(self.item)  
            
    
    def print_table(self):
        print('Table contents:')
        for i in self.item:
            print(i.word)
        
    def load_factor(self):
        length =0
        for i in self.item:
            if i > 0:
                length += 1
        return length/11
    
    def collisions(self):
        collisions = 0
        for i in range(len(self.item)):
            if self.h(self.item[i]) > 0 and self.h(self.item[i]) != i:
                collisions += 1
                print(self.item[i])
                print( collisions)
        return collisions

    def Embedding(self,T,k,c):
    # Returns embedding of the word k, or None if k is not in the table
        if c == "1":
            pos = (len(k.word))%len(self.item)#position where k must be
            while isinstance(self.item[pos], object): #checks next places for k
                if self.item[pos].word == k:
                    return self.item[pos].emb
                pos+=1
            return None #k was not found
        elif c == "2":
            pos = (ord(k.word[0]))%len(self.item)
            while isinstance(self.item[pos], object):
                if self.item[pos].word == k:
                    return self.item[pos].emb
                pos+=1
            return None
        elif c == "3":
            pos = self.hPA(k,0) 
            while isinstance(self.item[pos], object):
                if self.item[pos].word == k:
                    return self.item[pos].emb
                pos+=1
            return None
        elif c == "4":
            pos = self.hSA(k,0)
            while isinstance(self.item[pos], object):
                if self.item[pos].word == k:
                    return self.item[pos].emb
                pos+=1
            return None
        elif c == "5":
            pos = self.hR(k,0)
            while isinstance(self.item[pos], object):
                if self.item[pos].word == k:
                    return self.item[pos].emb
                pos+=1
            return None
        elif c == "6":
            return self.lookFe(k,0)
    