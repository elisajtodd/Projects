"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 6 - Graphs
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 11/15/2019
Program: Solves the fox, chicken, grains crossing the river problem. Implementing
graphs on three versions of it with two search algorithms (breadth first and depth first)
This program computes the valid vertices that the problem can use in function validV().
Fills the graph with the possible edges in fillGraph().
Finds the path to traverse the graph (solve the problem) in findPath()
Interprets this path into words (instructions) in function interpret()
Uses breadth-first and depth-first search algorithms found in each graph's class
Shows running times.
"""

import graph_AL as gal
import graph_EL as gel
import graph_AM as gam
import time

#finds valid vertices for the problem
def validV():
    valid = set()
    for i in range(16): #16 possible vertices
        #if chicken and fox arent left together alone or chicken and grain arent left together alone
        if not(((i//8)%2 == (i//4)%2 and (i//4)%2 != i%2) or ((i//4)%2 == (i//2)%2 and (i//4)%2 != i%2)):
            valid.add(i) #adds valid vertices
    return valid

def fillGraph(g, v):
    for i in v:
        if i%2 == 0: #person moves from right to left (fills this vertices)
            if i+1 in v: #checks if moving the person alone is possible
                g.insert_edge(i,i+1)
            if (i//2)%2 == 0 and i+3 in v: #checks if moving with the sack is possible
                g.insert_edge(i,i+3)
            if (i//4)%2 == 0 and i+5 in v: #checks if moving with the chicken is possible
                g.insert_edge(i,i+5)
            if (i//8)%2 == 0 and i+9 in v: #checks if moving with the fox is possible
                g.insert_edge(i,i+9)
                
def findPath(path, end):
    if path[end] < 0: #arrived to beginning
        return [end]
    return findPath(path, path[end]) + [end] #follow next index on path

def interpret(way):
    cnt = 1
    for i in range(1,len(way)):
        if way[i]%2 != 0: #means going to the right
            if (way[i]//2)%2 != 0 and (way[i-1]//2)%2 == 0: #sack of grain moved
                print(cnt, "- Cross with the sack of grain to the right side.")
            elif (way[i]//4)%2 != 0 and (way[i-1]//4)%2 == 0: #chicken moved
                print(cnt, "- Cross with the chicken to the right side.")
            elif (way[i]//8)%2 != 0 and (way[i-1]//8)%2 == 0: #fox moved
                print(cnt, "- Cross with the fox to the right side.")
            else:
                print(cnt, "- Cross alone to the right") #only the person moves
        else: #goig to the left
            if (way[i]//2)%2 == 0 and (way[i-1]//2)%2 != 0: #sack of grain moved
                print(cnt, "- Return with the sack of grain to the left side.")
            elif (way[i]//4)%2 == 0 and (way[i-1]//4)%2 != 0: #chicken moved
                print(cnt, "- Return with the chicken to the left side.")
            elif (way[i]//8)%2 == 0 and (way[i-1]//8)%2 != 0: #fox moved
                print(cnt, "- Return with the fox to the left side.")
            else:
                print(cnt, "- Return alone to the left") #only the person moves
        cnt += 1

stay = True #variable to stay on the menu
while(stay):
    print("\nPROBLEM: You have a fox, a chicken and a sack of grain. You must cross a river with only one of them at a time. If you leave the fox with the chicken he will eat it; if you leave the chicken with the grain he will eat it. How can you get all three across safely?" )
    option = input("Choose the algorithm to solve this problem:\n\t1)Adjacency List Breadth-first\n\t2)Adjacncy List Depth-first\n\t3)Adjacency Matrix Breadth-first\n\t4)Adjacency Matrix Depth-first\n\t5)Edge List Breadth-first\n\t6)Edge List Depth-first\n\t7)Exit\n:")
    #fox, chicken, grain, person
    #invalid:   [1,1,0,0](12) and [0,0,1,1](3)
    #           [0,1,1,0](6) and [1,0,0,1](9)
    #           [0,0,0,1](1) and [1,1,1,0] (14)
    
    valid = validV() #valid inputs
    bfirst = {"1","3","5"} #options for breadth first
    dfirst = {"2","4","6"} #options for depth first
    if (option in bfirst or option in dfirst):
        if (option in bfirst):
            if (option=="1"): #AL Breadth-first
                print("======READING AL BREADTH-FIRST======")
                g = gal.Graph(16)
            elif (option=="3"): #AM Breadth-first
                print("======READING AM BREADTH-FIRST======")
                g = gam.Graph(16)
            else: #EL Breadth-first
                print("======READING EL BREADTH-FIRST======")
                g = gel.Graph(16)
            start = time.time()
            fillGraph(g, valid) #call to fill graph that was chosen
            timeGraph = time.time()-start
            g.display() #show graph
            
            start = time.time()
            path = g.breadth_search() #call to search
            timePath = time.time()-start
        else:
            if (option=="2"): #AL Depth-first
                print("======READING AL DEPTH-FIRST======")
                g = gal.Graph(16)
            elif (option=="4"): #AM Depth-first
                print("======READING AM DEPTH-FIRST======")
                g = gam.Graph(16)
            else: #EL Depth-first
                print("======READING EL DEPTH-FIRST======")
                g = gel.Graph(16)
            start = time.time()
            fillGraph(g, valid) #call to fill graph that was chosen
            timeGraph = time.time()-start
            g.display() #show graph

            start = time.time()
            path = g.depth_search() #call to search
            timePath = time.time()-start
    
        print("PATH:\n", path)
        start = time.time()
        connect = findPath(path,15) #call to find the path from 0 to 15
        timeFind = time.time()-start
        print("Follow:\n", connect,"\n")
        interpret(connect)
        
        #show results
        print("\nTIME TO MAKE GRAPH: ", timeGraph)
        print("TIME TO MAKE PATHS: ",   timePath)
        print("TIME TO FIND SHORTEST PATH: ", timeFind)

    #exit loop
    elif (option=="7"):
        stay = False
        
    else:
        print("Please choose a number between 1 and 7.")