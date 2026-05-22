import cv2
import numpy as np

img = cv2.imread('iris.png')

b,g,r = cv2.split(img)

merged_img = cv2.merge([b,g,r])

cv2.imshow("merged", merged_img)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)

merged_img[:, :, 1] = 0

cv2.imshow("merged", merged_img)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)

red_merge = cv2.merge([r,r,r])

cv2.imshow("merged", red_merge)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.waitKey(1)