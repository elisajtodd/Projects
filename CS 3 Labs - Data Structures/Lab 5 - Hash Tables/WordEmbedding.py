# -*- coding: utf-8 -*-
"""
COURSE: CS 2302 Data Structures
AUTHOR: Elisa Jimenez Todd
ASSIGNMENT: Lab 4 - WordEmbedding
INSTRUCTOR: Olac Fuentes
TA: Anindita Nath
DATE: 10/25/2019
Program: -------------------
"""
import numpy as np

class WordEmbedding(object):
    #constructor
    def __init__(self, word, embedding):
        self.word = word    #string for the word
        self.emb = np.array(embedding, dtype=np.float32)    #np array for embeddings