# -*- coding: utf-8 -*-
"""
Created on Fri Dec  5 10:07:31 2025

@author: jorda
"""

import cv2
import numpy as np

A1 = np.array([[0,0,0,0,0,0,0,0],
      [0,0,1,1,1,1,1,0],
      [0,0,0,1,1,1,1,0],
      [0,1,1,1,1,1,1,0],
      [0,1,1,1,1,1,1,0],
      [0,1,1,1,1,0,0,0],
      [0,1,1,1,1,0,0,0],
      [0,0,0,0,0,0,0,0]], dtype = np.uint8) * 255

B1 = np.array([[0,1,0],
       [1,1,1],
       [0,1,0]], dtype = np.uint8) * 255

eroded_image = cv2.erode(A1, B1, iterations = 1)
dilated_image = cv2.dilate(A1, B1, iterations = 1)
open_image = cv2.dilate(eroded_image, B1, iterations = 1)
closed_image = cv2.erode(dilated_image, B1, iterations = 1)

print("Original A:\n" + str(A1/255) )
print("Original B:\n" + str(B1/ 255) )
print("Eroded: \n" + str(eroded_image / 255))
print("Dilated: \n" + str(dilated_image / 255))
print("Open: \n" + str(open_image / 255))
print("Closed:\n" + str(closed_image/255))