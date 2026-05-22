import cv2
from matplotlib import pyplot as plt
import numpy as np

img = cv2.imread('color_sunset.png', 1)
gray_image = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
#cv2.imshow("New", gray_image)
#cv2.waitKey(0)
#cv2.destroyAllWindows()
#cv2.waitKey(1)

grayscale_hist = cv2.calcHist([gray_image], [0], None, [256], [0, 256])

plt.hist(grayscale_hist)

rescaled_values = cv2.equalizeHist(gray_image)
rescaled_hist = cv2.calcHist([rescaled_values], [0], None, [256], [0, 256])
plt.figure(figsize = (16, 4))
plt.subplot(1, 2, 1)
plt.hist(rescaled_hist)
image_stack = np.hstack((gray_image, rescaled_values))
cv2.imshow("New", image_stack)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)
