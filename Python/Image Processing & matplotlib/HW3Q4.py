# -*- coding: utf-8 -*-
"""
Created on Sat Dec  6 11:51:05 2025

@author: jorda
"""

import numpy as np

p1 = np.array([0, -4])
p2 = np.array([1, -2])

m = (p2[1] - p1[1]) / (p2[0] - p1[0])
yintercept = p1[1] - m * p1[0]

print("a) Slope-intercept: y = " + str(m) + "x + " + str(yintercept))

a = m
b = -1
c = yintercept

norm = np.hypot(a, b)

r = abs(c) / norm
theta = np.arctan2(b, a)

#print("Hough r =", round(r, 2))
#print("Hough θ =", round(theta, 2))
print("b) (r, θ) = (" + str(round(r, 2)) + ", " + str(round(theta, 2)) + ")")