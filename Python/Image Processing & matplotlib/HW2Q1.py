# -*- coding: utf-8 -*-
"""
Created on Mon Oct 27 13:35:28 2025

@author: jorda
"""

x = [1, 3, 2, 1]
xt = [0, 1, 2, 3]
h = [1, 2, 1]
ht = [-2, -1, 0]

y = [] #2D list to store Tabular method table

#Purpose: populate y[] with products, iterate through each x and h
for i in range(len(h)):
    multResult = []
    for k in range(len(x)):
        multResult.append(h[i] * x[k]) #append members to each row
    y.append(multResult) #append each row to column

print("Matrix:")
for i in range(len(y)):
    print(y[i])

numIterations = len(x) + len(h) - 1 #number of diagonals to check in tabular method
sumResult = [0] * numIterations

#Purpose: find sum of each diagonal, add to final result list
for i in range(numIterations):
    indexSum = 0
    for k in range(len(x)):
        if i - k >= 0 and i - k < len(h):
            #y[n] = summation(-inf to inf) x[r] * h[n - r]
            indexSum += x[k] * h[i - k]
    sumResult[i] = indexSum
print("\ny[t] = " + str(sumResult) + "\n")

yt = sorted(set(xt).union(set(ht)))
print("t = " + str(yt))