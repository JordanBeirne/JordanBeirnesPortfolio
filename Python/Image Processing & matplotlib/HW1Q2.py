import numpy as np
from matplotlib import pyplot as plt

original_values = [5, 3, 5, 4, 4, 4, 10, 4, 5]

#a)
pixels = np.array(original_values)
plt.figure(figsize=(16, 4))
plt.subplot(1,3,1)
plt.xlabel("Grayscale value")
plt.ylabel("Amount")
plt.hist(pixels, bins=range(0, 15), color='gray', edgecolor='black')

#b)
original_min, original_max = 3, 10
new_min, new_max = 2, 40
m = (new_max - new_min) / (original_max - original_min)
# y = m(x-a) + c
def stretchValue(value):
    y = [0] * len(value)
    for i in range(len(value)):
        y[i] = m * (value[i] - original_min) + new_min
    return y

#original_values = np.arange(original_min, original_max + 2)
new_values = stretchValue(original_values)
print("Original values:")
print(original_values)
print("Scaled values: ")
print(new_values)

plt.figure(figsize=(16,4))
plt.subplot(1,3,1)
plt.hist(new_values, label = "Mapping", bins=20)
plt.xlabel("Grayscale value")
plt.ylabel("Amount")
#plt.xticks(range(2, 42, 2))
plt.ylim(0,5)

plt.figure(figsize=(16,4))
plt.subplot(1,3,1)
plt.plot(original_values, new_values, marker = 'o')