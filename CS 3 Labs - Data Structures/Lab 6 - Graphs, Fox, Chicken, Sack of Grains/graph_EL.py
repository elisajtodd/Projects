"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 6 - Edge List Graph
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/15/2019
Program: implementation of Edge List graph.
Inserting edges, deleting edges, displaying the table, breadth-first search,
and depth first search.
"""
# Edge list representation of graphs
import numpy as np
import matplotlib.pyplot as plt
import math
from scipy.interpolate import interp1d
import graph_AL as gal
import graph_AM as gam

class Edge:
    def __init__(self, source, dest, weight=1):
        self.source = source
        self.dest = dest
        self.weight = weight
        
class Graph:
    # Constructor
    def __init__(self,  vertices, weighted=False, directed = False):
        self.vertices = vertices
        self.el = []
        self.weighted = weighted
        self.directed = directed
        self.representation = 'EL'
        
    def insert_edge(self,source,dest,weight=1):
        self.el.append(Edge(source, dest, weight)) #insert edge with all info
        if not self.directed and source!=dest: #double if not directed
            self.el.append(Edge(dest, source, weight))
    
    def delete_edge(self,source,dest):
        i = 0
        for edge in self.el: #find edges
            if edge.source == source and edge.dest == dest: #if edge and dest correspond, this is the edge we are looking for
                self.el.pop(i) #remove edge
                break;
            i+=1
        if not self.directed:#double if not directed
            i=0
            for edge in self.el:
                if edge.source == dest and edge.dest == source: #destiny as source
                    self.el.pop(i)
                    return True
                i+=1
        return False
    
    def display(self):
        print('[',end='') #all inside brackets
        for i in self.el:
            print('(' + str(i.source) + ','+ str(i.dest) + ',' + str(i.weight) + ')',end='') #each edge inside parentheses
        print(']') 
        return
     
    def draw(self):
        return 
            
    def as_EL(self):
        return self
    
    def as_AM(self):
        #constructor for AM
        g = gam.Graph(self.vertices, weighted=self.weighted,directed = self.directed)
        for edge in self.el: #iterate through edges list
            g.insert_edge(edge.source, edge.dest, edge.weight) #insert corresponding values
        return g
    
    def as_AL(self):
        #constructor fot AL
        g = gal.Graph(self.vertices, weighted=self.weighted,directed = self.directed)
        for edge in self.el: #iterate through edges list
            g.insert_edge(edge.source, edge.dest, edge.weight) #insert corresponding values
        return g
    
    def depth_search(self, start=0):
        stack = [start] #Push startV to stack
        path = [-1] * len(self.el)
        visited = set()
        while (len(stack)>0):
            currentV = stack.pop()
            if (not currentV in visited):
                visited.add(currentV)#Add currentV to visited
                for i in self.el:# for each vertex adjacent to currentV
                    if i.source == currentV:
                        stack.append(i.dest)
                        if (not i.dest in visited):
                            path[i.dest] = currentV
        return path
    
    def breadth_search(self, start=0):
        fqueue = [start] #Push start to fqueue
        path = [-1] * len(self.el) #builds path
        discovered = {start} #Add  start to discovered
        while (len(fqueue)>0):
            currentV = fqueue.pop()
            #for each vertex adjacent to currentV
            for i in self.el:
                if i.source == currentV:
                    if (not i.dest in discovered):
                        fqueue.insert(0,i.dest)
                        discovered.add(i.dest)
                        path[i.dest]=currentV
        return path