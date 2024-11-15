import numpy as np
import random
import pandas as pd

from pylab import plt, mpl



#pandas
data1 = pd.DataFrame([10, 20, 30, 40], columns=['numbers'], index=['a','b','c','d'])
print(data1)

print('Dataframe indices: ' , data1.index)
print('Dataframe columns: ' , data1.columns)

print(data1.loc['c'])

print('SUM: ' , data1.sum())
print('Func: ' , data1.apply(lambda x: x * 2))

print('Func: ' , data1 * 2)


data1['floats'] = (1.5, 2.5, 3.5, 4.5)
print(data1)

data1['bools'] = [True, True, True, False]
print(data1)

data1['names'] = pd.DataFrame(['Yves', 'Sandra', 'Lilli', 'Henry'],
index=['d', 'a', 'b', 'c'])
print(data1)

data1 = data1._append(pd.DataFrame({'numbers': 100, 'floats': 5.75, 'bools':True,
'names': 'Jil'}, index=['y']))
print(data1)

np.random.seed(100)
a = np.random.standard_normal((9, 4))
print(a)

data2 = pd.DataFrame(a)
print(data2)

dates = pd.date_range('2019-1-1', periods=9, freq='ME')
print(dates)

data2.columns = ['No1', 'No2', 'No3', 'No4']
data2.index = dates
print(data2)

print('--------------------------------------------------------')
print(data2.info())
print('--------------------------------------------------------')
print(data2.describe())
print('--------------------------------------------------------')

plt.style.use('seaborn-v0_8-darkgrid')
mpl.rcParams['font.family'] = 'serif'

#data2.cumsum().plot(lw=2.0, figsize=(10, 6))
data2.plot.bar(figsize=(10, 6), rot=15)

series1 = pd.Series(np.linspace(0, 15, 7), name='series')
print(series1)
print(series1.mean())
#plt.show()

data2['Quarter'] = ['Q1', 'Q1', 'Q1', 'Q2', 'Q2', 'Q2', 'Q3', 'Q3', 'Q3']
print(data2)

groups = data2.groupby('Quarter')
print('Groups size: ', groups.size())

print(groups.mean())

print(groups.aggregate(['min', 'max']).round(2))

data2['Odd_Even'] = ['Odd', 'Even', 'Odd', 'Even', 'Odd', 'Even',
'Odd', 'Even', 'Odd']

groups = data2.groupby(['Quarter', 'Odd_Even'])
print(groups.size())


print(groups[['No1', 'No4']].aggregate(['sum', 'mean']))

data = np.random.standard_normal((10, 2))
df = pd.DataFrame(data, columns=['x','y'])

print(df.info())

print(df.head())
print('---------------')
print(df.tail())
print('---------------')
print(df['x'] > 0.5)
print('---------------')
print((df['x'] > 0) & (df['y'] < 0))
print('---------------')
print(df[df['x'] > 0])
print('---------------')
print(df.query('x > 0'))
print('---------------')
print(df.query('x > 0 & y < 0'))
print('---------------')
print('---------------')
print('---------------')
print('---------------')

df1 = pd.DataFrame(['100', '200', '300', '400'],
index=['a', 'b', 'c', 'd'],
columns=['A',])

df2 = pd.DataFrame(['200', '150', '50'],
index=['f', 'b', 'd'],
columns=['B',])

print('---------------')
print(df1.join(df2))
print('---------------')
print(df2.join(df1))
print('---------------LEFT JOIN')
print(df1.join(df2, how='left'))
print('---------------RIGHT JOIN')
print(df1.join(df2, how='right'))
print('---------------INNER JOIN')
print(df1.join(df2, how='inner'))
print('---------------OUTER JOIN')
print(df1.join(df2, how='outer'))

df = pd.DataFrame()
df['A'] = df1['A']
df['B'] = df2['B']

print('---------------')
print(df)
print('---------------')


df = pd.DataFrame({'A': df1['A'], 'B': df2['B']})
print(df)
print('---------------------------------------')
print('---------------------------------------')
print('---------------------------------------')
print()
print('---------------------------------------')

