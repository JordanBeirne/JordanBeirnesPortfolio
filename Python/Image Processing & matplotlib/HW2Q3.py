# -*- coding: utf-8 -*-
"""
Created on Wed Oct 29 14:49:04 2025

@author: jorda
"""
import cv2
import numpy as np

image_original = cv2.imread("myImage.png")
img = image_original.copy()

noise_amount = .20/2 #20% noise amount = 10% salt 10% pepper

#add noise to img
for i in range(img.shape[0]):
    for k in range(img.shape[1]):
        random = np.random.rand()
        if random < noise_amount:
            img[i][k] = [255, 255, 255]
        elif random > 1 - noise_amount:
            img[i][k] = [0, 0, 0]

box_filter_img = cv2.blur(img, (5,5))
gauss_filter_img = cv2.GaussianBlur(img, (5,5), sigmaX = 0)
median_filter_img = cv2.medianBlur(img, 3)

img_noise = cv2.hconcat([image_original,img])
cv2.imshow('Before and After Noise', img_noise)
img_filter = cv2.hconcat([box_filter_img, gauss_filter_img])
cv2.imshow('Filters, in order: Box, Gaussian', img_filter)
cv2.imshow('Median Filter', median_filter_img)

cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)