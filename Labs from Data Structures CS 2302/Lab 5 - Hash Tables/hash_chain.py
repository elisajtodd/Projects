# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 5 - Hash Table Chain
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/01/2019
Program: --------------------
"""
class HashTableChain(object):
    # Builds a hash table of size 'size'
    # Item is a list of (initially empty) lists
    # Constructor
    def __init__(self,size):  
        self.bucket = [[] for i in range(size)]
    
    def hS(self,k):
        return len(k.word)%len(self.bucket)           
    
    def insertString(self,k):
        # Inserts k in appropriate bucket (list) 
        # Does nothing if k is already in the table
        b = self.hS(k)
        if not k in self.bucket[b]:
            new = [k]
            for i in self.bucket[b]:
                new.append(i) #insert new item at the beginning
            self.bucket[b]=new
            #self.bucket[b].append(k)         #Insert new item at the end
    def hA(self,k):
        return ord(k.word[0])%len(self.bucket)    
    
    def insertAscii(self,k):
        # Inserts k in appropriate bucket (list) 
        # Does nothing if k is already in the table
        b = self.hA(k)
        if not k in self.bucket[b]:
            new = [k]
            for i in self.bucket[b]:
                new.append(i) #insert new item at the beginning
            self.bucket[b]=new
            #self.bucket[b].append(k)         #Insert new item at the end
            
    def hPA(self,k):
        return (ord(k.word[0])*ord(k.word[-1]))%len(self.bucket)    
    
    def insertPAscii(self,k):
        # Inserts k in appropriate bucket (list) 
        # Does nothing if k is already in the table
        b = self.hPA(k)
        if not k in self.bucket[b]:
            new = [k]
            for i in self.bucket[b]:
                new.append(i) #insert new item at the beginning
            self.bucket[b]=new
            #self.bucket[b].append(k)         #Insert new item at the end

    def hSA(self,k):
        sum=0
        for i in k:
            sum += ord(i)
        return sum%len(self.bucket)    
    
    def insertSAscii(self,k):
        # Inserts k in appropriate bucket (list) 
        # Does nothing if k is already in the table
        b = self.hSA(k.word)
        if not k in self.bucket[b]:
            new = [k]
            for i in self.bucket[b]:
                new.append(i) #insert new item at the beginning
            self.bucket[b]=new
            #self.bucket[b].append(k)         #Insert new item at the end
            
    def hR(self,k):
        if len(k) == 0:
            return 1            
        return (ord(k[0]) + 255*self.hR(k[1:]))%len(self.bucket)
    
    def insertRecursive(self,k):
        # Inserts k in appropriate bucket (list) 
        # Does nothing if k is already in the table
        b = self.hR(k.word)
        if not k in self.bucket[b]:
            new = [k]
            for i in self.bucket[b]:
                new.append(i) #insert new item at the beginning
            self.bucket[b]=new
            #self.bucket[b].append(k)         #Insert new item at the end    
            
    def hFE(self,k):
        return int(round(k.emb[0]*10000)%len(self.bucket))
    
    def lookFe(self,k):
        for i in self.bucket:
            for j in i:
                if j.word == k:
                    return j.emb
        return None
    
    def insertFE(self,k):
        # Inserts k in appropriate bucket (list) 
        # Does nothing if k is already in the table
        b = self.hFE(k)
        if not k in self.bucket[b]:
            new = [k]
            for i in self.bucket[b]:
                new.append(i) #insert new item at the beginning
            self.bucket[b]=new
            #self.bucket[b].append(k)         #Insert new item at the end  
            
    def find(self,k):
        # Returns bucket (b) and index (i) 
        # If k is not in table, i == -1
        b = self.h(k)
        try:
            i = self.bucket[b].index(k)
        except:
            i = -1
        if i > -1:
            self.delete(k)
            self.insert(k)
        return b, i
     
    def print_table(self):
        print('Table contents:')
        for b in self.bucket:
            print("[", end='')
            for a in b:
                print(a.word, end = ', ')
            print("]")

    def Embedding(self,T,k,c):
    # Returns embedding of the word k, or None if k is not in the table
        if c == "1":
            b = self.hS(k) #bucket where k must be
            for i in self.bucket[b]:    #looks for index in bucket where k is
                if i.word == k:
                    return i.emb
        elif c == "2":
            b = self.hA()
            for i in self.bucket[b]:
                if i.word == k:
                    return i.emb
        elif c == "3":
            b = self.hPA(k) 
            for i in self.bucket[b]:
                if i.word == k:
                    return i.emb
        elif c == "4":
            b = self.hSA(k)
            for i in self.bucket[b]:
                if i.word == k:
                    return i.emb
        elif c == "5":
            b = self.hR(k)
            for i in self.bucket[b]:
                if i.word == k:
                    return i.emb
        elif c == "6":
            return self.lookFe(k)
        return None #k was not found