# -*- coding: utf-8 -*-
"""
Created on Sat Dec  6 10:58:09 2025

@author: jorda
"""

import cv2
import numpy as np
c=cv2.imread('circles.png', cv2.IMREAD_GRAYSCALE)
x=np.random.random_sample(c.shape)
c[np.nonzero(x>0.95)]= 0
c[np.nonzero(x<=0.05)] = 1
# Generate a kernel and perform morphology – you need to decide what operation to use below

kernel = np.ones((3, 3), np.uint8)

dilated_image = cv2.dilate(c, kernel, iterations = 1)

closed_image = cv2.erode(dilated_image, kernel, iterations = 1)

result = cv2.hconcat([c, closed_image])

cv2.imshow("Noise Removal", result)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)

