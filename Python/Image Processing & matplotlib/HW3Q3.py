# -*- coding: utf-8 -*-
"""
Created on Sat Dec  6 11:23:35 2025

@author: jorda
"""

import cv2
import numpy as np

img = cv2.imread("morph_text.png")

sobelx = cv2.Sobel(img, cv2.CV_8U, 1, 0, ksize=3)
sobely = cv2.Sobel(img, cv2.CV_8U, 0, 1, ksize=3)

result = cv2.hconcat([img, sobelx, sobely])

cv2.imshow("Original, Sobel X, Sobel Y", result)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)

