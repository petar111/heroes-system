import matplotlib as mpl
import matplotlib.pyplot as plt

from matplotlib.patches import Polygon
from mpl_toolkits.mplot3d import Axes3D

import numpy as np

print('Matplotlib version: ', mpl.__version__)

plt.style.use('classic')
mpl.rcParams['font.family'] = 'serif'

np.random.seed(1000)
y = np.random.standard_normal(20)

x = np.arange(len(y))

### simple plot - no index (x) needed
# plt.plot(x, y)
# plt.show()

# plt.plot(y)
# plt.show()

# plt.plot(y.cumsum())
# plt.show()

# plt.plot(y.cumsum())
# plt.grid(False)
# plt.axis('equal')
# plt.show()

### some adjustments to plot like axis label
# plt.figure(figsize=(10, 6))
# plt.plot(y.cumsum(), 'b', lw=1.5) #line
# plt.plot(y.cumsum(), 'ro') #red dots
# plt.xlabel('index')
# plt.ylabel('value')
# plt.title('A Simple Plot')
# plt.show()

y = np.random.standard_normal((20, 2)).cumsum(axis=0)

### case with two data in one plot
# plt.figure(figsize=(10, 6))
# plt.plot(y, lw=1.5)
# plt.plot(y, 'ro')
# plt.xlabel('index')
# plt.ylabel('value')
# plt.title('A Simple Plot')
# plt.show()

y[:, 0] = y[:, 0] * 100

### case with two data in one plot - one plot data is barely visible
# plt.figure(figsize=(10, 6))
# plt.plot(y[:, 0], lw=1.5, label='1st')
# plt.plot(y[:, 1], lw=1.5, label='2nd')
# plt.plot(y, 'ro')
# plt.legend(loc=0)
# plt.xlabel('index')
# plt.ylabel('value')
# plt.title('A Simple Plot')
# plt.show()

### case with two y-axis
# fig, ax1 = plt.subplots()
# plt.plot(y[:, 0], 'b', lw=1.5, label='1st')
# plt.plot(y[:, 0], 'ro')
# plt.legend(loc=8)
# plt.xlabel('index')
# plt.ylabel('value 1st')
# plt.title('A Simple Plot')
# ax2 = ax1.twinx()
# plt.plot(y[:, 1], 'g', lw=1.5, label='2nd')
# plt.plot(y[:, 1], 'ro')
# plt.legend(loc=0)
# plt.ylabel('value 2nd')
# plt.show()

### case with subplots
# plt.figure(figsize=(10, 6))
# plt.subplot(211)
# plt.plot(y[:, 0], lw=1.5, label='1st')
# plt.plot(y[:, 0], 'ro')
# plt.legend(loc=0)
# plt.ylabel('value')
# plt.title('A Simple Plot')
# plt.subplot(212)
# plt.plot(y[:, 1], 'g', lw=1.5, label='2nd')
# plt.plot(y[:, 1], 'ro')
# plt.legend(loc=0)
# plt.xlabel('index')
# plt.ylabel('value')
# plt.show()

### case with subplots - line and bar
# plt.figure(figsize=(10, 6))
# plt.subplot(121)
# plt.plot(y[:, 0], lw=1.5, label='1st')
# plt.plot(y[:, 0], 'ro')
# plt.legend(loc=0)
# plt.xlabel('index')
# plt.ylabel('value')
# plt.title('1st Data Set')
# plt.subplot(122)
# plt.bar(np.arange(len(y)), y[:, 1], width=0.5,
# color='g', label='2nd')
# plt.legend(loc=0)
# plt.xlabel('index')
# plt.title('2nd Data Set')
# plt.show()

y = np.random.standard_normal((1000, 2))

### case - scatter plot
# plt.figure(figsize=(10, 6))
# plt.plot(y[:, 0], y[:, 1], 'ro')
# plt.xlabel('1st')
# plt.ylabel('2nd')
# plt.title('Scatter Plot')
# plt.show()

### case - scatter plot
# plt.figure(figsize=(10, 6))
# plt.scatter(y[:, 0], y[:, 1], marker='o')
# plt.xlabel('1st')
# plt.ylabel('2nd')
# plt.title('Scatter Plot')
# plt.show()

c = np.random.randint(0, 10, len(y))

### case - multi-colored scatter plot
# plt.figure(figsize=(10, 6))
# plt.scatter(y[:, 0], y[:, 1],c=c,cmap='coolwarm',marker='o')
# plt.colorbar()
# plt.xlabel('1st')
# plt.ylabel('2nd')
# plt.title('Scatter Plot')
# plt.show()

### case - histogram
# plt.figure(figsize=(10, 6))
# plt.hist(y, label=['1st', '2nd'], bins=25)
# plt.legend(loc=0)
# plt.xlabel('value')
# plt.ylabel('frequency')
# plt.title('Histogram')
# plt.show()

### case - histogram stacked
# plt.figure(figsize=(10, 6))
# plt.hist(y, label=['1st', '2nd'], color=['b', 'g'],
# stacked=True, bins=20, alpha=0.5)
# plt.legend(loc=0)
# plt.xlabel('value')
# plt.ylabel('frequency')
# plt.title('Histogram')
# plt.show()

### case - boxplot
# fig, ax = plt.subplots(figsize=(10, 6))
# plt.boxplot(y)
# plt.setp(ax, xticklabels=['1st', '2nd'])
# plt.xlabel('data set')
# plt.ylabel('value')
# plt.title('Boxplot')
# plt.show()

### case - defining a function and integral
# def func(x):
#     return 0.5 * np.exp(x) + 1
# a, b = 0.5, 1.5
# x = np.linspace(0, 2)
# y = func(x)
# Ix = np.linspace(a, b)
# Iy = func(Ix)
# verts = [(a, 0)] + list(zip(Ix, Iy)) + [(b, 0)]
#
# fig, ax = plt.subplots(figsize=(10, 6))
# plt.plot(x, y, 'b', linewidth=2)
# plt.ylim(bottom=0)
# poly = Polygon(verts, facecolor='0.7', edgecolor='0.5')
# ax.add_patch(poly)
# plt.text(0.5 * (a + b), 1, r'$\int_a^b f(x)\mathrm{d}x$',
#     horizontalalignment='center', fontsize=20)
# plt.figtext(0.9, 0.075, '$x$')
# plt.figtext(0.075, 0.9, '$f(x)$')
# ax.set_xticks((a, b))
# ax.set_xticklabels(('$a$', '$b$'))
# ax.set_yticks([func(a), func(b)])
# ax.set_yticklabels(('$f(a)$', '$f(b)$'))
# plt.show()




strike = np.linspace(50, 150, 24)
ttm = np.linspace(0.5, 2.5, 24)

strike, ttm = np.meshgrid(strike, ttm)

iv = (strike - 100) ** 2 / (100 * strike) / ttm

### case - 3D plot
# fig = plt.figure(figsize=(10, 6))
# ax = fig.add_subplot(111, projection='3d')
# surf = ax.plot(strike, iv, ttm, zdir='z',
#      c='b')
# ax.set_xlabel('strike')
# ax.set_ylabel('time-to-maturity')
# ax.set_zlabel('implied volatility')
# plt.show()

### case - 3D plot - scatter
# fig = plt.figure(figsize=(10, 6))
# ax = fig.add_subplot(111, projection='3d')
# ax.view_init(30, 60)
# ax.scatter(strike, ttm, iv, zdir='z', s=25,
#     c='b', marker='^')
# ax.set_xlabel('strike')
# ax.set_ylabel('time-to-maturity')
# ax.set_zlabel('implied volatility')
# plt.show()
