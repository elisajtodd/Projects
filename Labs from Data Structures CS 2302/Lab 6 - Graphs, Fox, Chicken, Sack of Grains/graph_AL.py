"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 6 - Adjacency List Graph
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/15/2019
Program: implementation of Adjacency List graph.
Inserting edges, deleting edges, displaying the table, breadth-first search,
and depth first search.
"""
# Adjacency list representation of graphs
import numpy as np
import matplotlib.pyplot as plt
import math
from scipy.interpolate import interp1d
import graph_AM as gam
import graph_EL as gel

class Edge:
    def __init__(self, dest, weight=1):
        self.dest = dest
        self.weight = weight
        
class Graph:
    # Constructor
    def __init__(self, vertices, weighted=False, directed = False):
        self.al = [[] for i in range(vertices)]
        self.weighted = weighted
        self.directed = directed
        self.representation = 'AL'
        
    def insert_edge(self,source,dest,weight=1):
        if source >= len(self.al) or dest>=len(self.al) or source <0 or dest<0:
            print('Error, vertex number out of range')
        elif weight!=1 and not self.weighted:
            print('Error, inserting weighted edge to unweighted graph')
        else:
            self.al[source].append(Edge(dest,weight)) 
            if not self.directed and source!= dest:
                self.al[dest].append(Edge(source,weight))
    
    def delete_edge_(self,source,dest):
        i = 0
        for edge in self.al[source]: 
            if edge.dest == dest:
                self.al[source].pop(i) #deletion
                return True
            i+=1    
        return False
    
    def delete_edge(self,source,dest):
        if source >= len(self.al) or dest>=len(self.al) or source <0 or dest<0:
            print('Error, vertex number out of range')
        else:
            deleted = self.delete_edge_(source,dest)
            if not self.directed:
                deleted = self.delete_edge_(dest,source)
        if not deleted:        
            print('Error, edge to delete not found')      
            
    def display(self):
        print('[',end='')
        for i in range(len(self.al)):
            print('[',end='')
            for edge in self.al[i]:
                print('('+str(edge.dest)+','+str(edge.weight)+')',end='')
            print(']',end=' ')    
        print(']')   
     
    def draw(self):
        scale = 30
        fig, ax = plt.subplots()
        for i in range(len(self.al)):
            for edge in self.al[i]:
                d,w = edge.dest, edge.weight
                if self.directed or d>i:
                    x = np.linspace(i*scale,d*scale)
                    x0 = np.linspace(i*scale,d*scale,num=5)
                    diff = np.abs(d-i)
                    if diff == 1:
                        y0 = [0,0,0,0,0]
                    else:
                        y0 = [0,-6*diff,-8*diff,-6*diff,0]
                    f = interp1d(x0, y0, kind='cubic')
                    y = f(x)
                    s = np.sign(i-d)
                    ax.plot(x,s*y,linewidth=1,color='k')
                    if self.directed:
                        xd = [x0[2]+2*s,x0[2],x0[2]+2*s]
                        yd = [y0[2]-1,y0[2],y0[2]+1]
                        yd = [y*s for y in yd]
                        ax.plot(xd,yd,linewidth=1,color='k')
                    if self.weighted:
                        xd = [x0[2]+2*s,x0[2],x0[2]+2*s]
                        yd = [y0[2]-1,y0[2],y0[2]+1]
                        yd = [y*s for y in yd]
                        ax.text(xd[2]-s*2,yd[2]+3*s, str(w), size=12,ha="center", va="center")
            ax.plot([i*scale,i*scale],[0,0],linewidth=1,color='k')        
            ax.text(i*scale,0, str(i), size=20,ha="center", va="center",
             bbox=dict(facecolor='w',boxstyle="circle"))
        ax.axis('off') 
        ax.set_aspect(1.0)
            
    def out_degree(G,v):
        cnt = 0
        for i in G[v]:
            cnt += 1
        return 1
    
    def in_degree(G,v):
        cnt = 0
        for i in G:
            for j in i:
                if j[1] == v:
                    cnt+= 1
        if G.directed:
            return cnt
        return cnt/2
    
    def as_EL(self):
        #constructor for EL graph
        g = gel.Graph(len(self.al), weighted=self.weighted,directed = self.directed)
        for i in range(len(self.al)): #iterates through np
            for j in range(len(self.al[i])): #list inside np
                g.insert_edge(i,self.al[i][j].dest, self.al[i][j].weight)#inserts corresponding values
        return g
    
    def as_AM(self):
        #constructor for AM graph
        g = gam.Graph(len(self.al), weighted=self.weighted,directed = self.directed)
        for i in range(len(self.al)): #iterates through np array
            for j in range(len(self.al[i])): #lists inside np array
                g.insert_edge(i,self.al[i][j].dest, self.al[i][j].weight) #inserts corresponding values
        return g
    
    def as_AL(self):
        return self #self already al
    
    def depth_search(self, start=0):
        stack = [start] #Push startV to stack
        path = [-1] * len(self.al)
        visited = set()
        while (len(stack)>0):
            currentV = stack.pop()
            if (not currentV in visited):
                visited.add(currentV)#Add currentV to visited
                for i in self.al[currentV]:# for each vertex adjacent to currentV
                    stack.append(i.dest)
                    if (not i.dest in visited):
                        path[i.dest] = currentV #values on path
        return path
    
    def breadth_search(self, start=0):
        fqueue = [start] #Push start to fqueue
        path = [-1] * len(self.al) #path to return
        discovered = {start} #Add  start to discovered
        while (len(fqueue)>0):
            currentV = fqueue.pop()
            #for each vertex adjacent to currentV
            for i in self.al[currentV]:
                if (not i.dest in discovered): #if adj not waiting
                    fqueue.insert(0,i.dest) #follow adj
                    discovered.add(i.dest)  #add queue
                    path[i.dest]=currentV   #add path
        return path