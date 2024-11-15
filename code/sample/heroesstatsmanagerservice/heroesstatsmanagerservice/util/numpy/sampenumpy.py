import numpy as np
import random
import pandas as pd

b = np.array([2, 4, 5, 6])

print(type(b))

m = np.array([[1,1,1,1,1],
              [2,2,2,2,2],
              [3,3,3,3,3]])


print(m[1,1])
print(m.sum())
print(m.sum(axis=0))
print(m.sum(axis=1))

print(m[:,1])

c = np.zeros((2, 3), dtype='i', order='C')
print(c)

e = np.eye(8)
print(e)


g = np.linspace(5, 15, 12)
print(g)


print('size',g.size)
print('itemsize',g.itemsize)
print('ndim',g.ndim)
print('shape',g.shape)
print('dtype',g.dtype)
print('nbytes',g.nbytes)

rs = g.reshape(2, 6)

print(rs)

h = np.array([1, 2 ,3])

hs = np.stack((h, h ** 2))
print(hs)

hh = np.hstack((h, h * 3))
print(hh)


hv = np.vstack((h, h * 3))
print(hv)

hf = hv.flatten()
print(hf)

print(hf[hf > 8])

print(np.where(hf > 7, 'more than 7', 'less than 7'))

dt1 = np.dtype([('Name', 'S10'), ('Age', 'i4'),
('Height', 'f'), ('Children/Pets', 'i4', 2)])

dt2 = np.dtype({'names': ['Name', 'Age', 'Height', 'Children/Pets'],
'formats':'O int float int,int'.split()})

s2 = np.array([('Smith', 45, 1.83, (0, 1)),
('Jones', 53, 1.72, (2, 2))], dtype=dt2)

print(type(s2))

print(s2['Name'])
print(s2['Height'].mean())

print(s2[0])


np.random.seed(100)
r1 = np.arange(12).reshape((4, 3))
s1 = np.arange(12).reshape((4, 3)) * 0.5

print(r1 + s1)

print(2 * (r1 + s1) - 4)

def f(x):
    return 3 * x + 5


print(f(r1))

