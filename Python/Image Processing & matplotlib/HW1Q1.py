import cv2
import numpy as np

f = 0.01 #focal point = 1cm or 0.01 meters
distance = 20.0
X, Y, Z = 4.0, 2.0, distance

print("Focal point: " + str(f))
print("Distance (Z): " + str(distance))
print("Coordinates X, Y, Z: " + str(X) + ", " + str(Y) + ", " + str(Z) + "\n")
u = f * X / Z
v = f * Y / Z 

print("u = fX/Z")
print("u = " + str(f) + " * " + str(X) + " / " + str(Z))
print("u = " + str(u) + "\n")

print("v = fY/Z")
print("v = " + str(f) + " * " + str(Y) + " / " + str(Z))
print("v = " + str(v))
print("Coordinates of pixel = (" + str(u * 1000) + "mm, " + str(v * 1000) + "mm)")