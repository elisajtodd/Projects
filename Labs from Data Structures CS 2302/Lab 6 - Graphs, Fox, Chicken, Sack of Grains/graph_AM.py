"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 6 - Adjacency Matrix Graph
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/15/2019
Program: implementation of Adjacency Matrix graph.
Inserting edges, deleting edges, displaying the table, breadth-first search,
and depth first search.
"""
# Adjacency matrix representation of graphs
import numpy as np
import matplotlib.pyplot as plt
import math
from scipy.interpolate import interp1d
import graph_AL as gal
import graph_EL as gel

class Graph:
    # Constructor
    def __init__(self, vertices, weighted=False, directed = False):
        self.am = np.zeros((vertices,vertices),dtype=int)-1
        self.weighted = weighted
        self.directed = directed
        self.representation = 'AM'
        
    def insert_edge(self,source,dest,weight=1):
        self.am[source,dest]=weight #change value of corresponding place to the weight
        if not self.directed and source!=dest: #double if not directed
            self.am[dest,source]=weight
        
    def delete_edge(self,source,dest):
        self.am[source,dest]=-1 #reset corresponding value to -1
        if not self.directed: #double if not directed
            self.am[dest,source]=-1
                
    def display(self):
        print(self.am) #prints table
     
    def draw(self):
        return 
    
    def as_EL(self):
        #constructor for edge graph
        g = gel.Graph(len(self.am), weighted=self.weighted,directed = self.directed)
        for source in range(len(self.am)): #iterates length of matrix
            for dest in range(len(self.am)):#iterates for width of matrix
                if(self.am[source,dest] != -1): #sends only values existing
                    g.insert_edge(source, dest, self.am[source,dest]) #inserts corresponding values
        return g
    
    def as_AM(self):
        return self #self already AM
    
    def as_AL(self):
        #constructor for AL graph
        g = gal.Graph(len(self.am), weighted=self.weighted,directed = self.directed)
        for source in range(len(self.am)): #itereates length
            for dest in range(len(self.am)): #iterates width
                if(self.am[source,dest] != -1): #only values existing
                    g.insert_edge(source, dest, self.am[source,dest]) #insert corresponding values
        return g
    
    def depth_search(self, start=0):
        stack = [start] #Push startV to stack
        path = [-1] * len(self.am)
        visited = set()
        while (len(stack)>0):
            currentV = stack.pop()
            if (not currentV in visited):
                visited.add(currentV)#Add currentV to visited
                for i in range(len(self.am)):# for each vertex adjacent to currentV
                    if self.am[currentV,i] != -1:
                        stack.append(i)
                        if (not i in visited):
                            path[i] = currentV
        return path
    
    def breadth_search(self, start=0):
        fqueue = [start] #Push start to fqueue
        path = [-1] * len(self.am) #path to return
        discovered = {start} #Add  start to discovered
        while (len(fqueue)>0):
            currentV = fqueue.pop()
            #for each vertex adjacent to currentV
            for i in range(len(self.am)):
                if (self.am[currentV, i] != -1 and not i in discovered):
                    fqueue.insert(0,i)
                    discovered.add(i)
                    path[i]=currentV
        return path
    