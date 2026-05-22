# -*- coding: utf-8 -*-
"""
Created on Wed Oct 29 15:48:01 2025

@author: jorda
"""

import numpy as np

original = np.array([2, 3, 4, 1])

answer = np.conjugate(np.fft.fft(original))

print("Before DFT: ", original)
print("\nAfter DFT: ", answer)