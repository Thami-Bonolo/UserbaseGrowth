#import matplotlib.pyplot as plt
import plotext as plt
import numpy as np

DatesAsValues = []
values = [] #number of site visits

with open("GraphData.txt") as file:
    for line in file:
        DatesAsValue = str(line[0:5])
        DatesAsValues.append(DatesAsValue)
        value = int(line[11:])
        values.append(value)

plt.title("Userbase Growth")
plt.xlabel("Dates")
plt.ylabel("userbase")
plt.bar(DatesAsValues, values)
plt.show()