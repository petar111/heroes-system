import numpy as np
import pandas as pd
from pylab import mpl, plt
import json

# For each trading day of the stock, a record of each of the following is posted.
# o Open = the price when the market opened in the morning.
# o Close = the price when the market closed in the afternoon.
# o High = the highest price during that trading day.
# o Low = the lowest price during that trading day.
# o Volume = number of shares of the stock traded that day.
# o Adj Close (Adjusted Close) = a price adjusted to make prices comparable over time.

# financial data obtained after creating free account on https://eodhd.com, obtaining API token <MY_TOKEN> from https://eodhd.com/cp/dashboard
# then executing the following command in Linux bash to obtain the data:
# wget "https://eodhd.com/api/eod/TSLA.US?api_token=<MY_TOKEN>&order=d&fmt=csv&from=2017-08-01" -O TSLA.US.csv;
# symbols TSLA - ticker for Tesla company, exchange: US dollars
tesla_filename='./financial_data/TSLA.US_from_2023-11-15_to_2023-11-14.csv'
exchange_list_filename='./financial_data/exchange_list/exchange_list.json'

data = pd.read_csv(tesla_filename, index_col=0, parse_dates=True)

exchange_list_file = open(exchange_list_filename, 'r')
exchange_list_data = json.load(exchange_list_file)

print('Exchange name first: ', exchange_list_data[0]['Name'])

print('DATA INFO:')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.info())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

print('CHUNK OF THE DATA ON THE TOP:')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.head())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

print('DATA DESCRIPTION - basic statistics:')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.describe().round(2))
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')


print('DATA DESCRIPTION - mean:')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.mean().round(2))
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

print('DATA AGGREGATED: min, mean, std, median, max')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.aggregate(['min',
'mean',
'std',
'median',
'max']
).round(2))
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')


print('DATA DIFFERENCE OVER TIME: First 5 rows')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.diff().head())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

print('DATA DIFFERENCE Mean')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.diff().mean())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

print('DATA DIFFERENCE in % - first 5 rows')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.pct_change().round(3).head())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')


# plot the data change in %
# data.pct_change().mean().plot(kind='bar', figsize=(10, 6))
# plt.show()

rets = np.log(data / data.shift(1))

print('DATA DIFFERENCE - LOG')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(rets.head().round(3))
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')



print('DATA RESAMPLED TO ONE WEEK INTERVAL INSTEAD OF ONE DAY')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.resample('1W', label='right').last().head())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

print('DATA RESAMPLED TO ONE MONTH INTERVAL INSTEAD OF ONE DAY')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.resample('1ME', label='right').last().head())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

sym = 'Open'
data = pd.DataFrame(data[sym]).dropna()

print('')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.tail())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

window = 20

data['min'] = data[sym].rolling(window=window).min()
data['mean'] = data[sym].rolling(window=window).mean()
data['std'] = data[sym].rolling(window=window).std()
data['median'] = data[sym].rolling(window=window).median()
data['max'] = data[sym].rolling(window=window).max()
data['ewma'] = data[sym].ewm(halflife=0.5, min_periods=window).mean()

print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')
print(data.dropna().head())
print('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++')

# plot for the last 200 rows
# ax = data[['min', 'mean', 'max']].iloc[-200:].plot(
#     figsize=(10, 6), style=['g--', 'r--', 'g--'], lw=0.8)
# data[sym].iloc[-200:].plot(ax=ax, lw=2.0)
# plt.show()

# rets.cumsum().apply(np.exp).plot(figsize=(10, 6))
# plt.show()


# performing SMA (Simple Moving Average) analysis
data['SMA1'] = data[sym].rolling(window=60).mean()
data['SMA2'] = data[sym].rolling(window=15).mean()

# data[[sym, 'SMA1', 'SMA2']].plot(figsize=(10, 6))
# plt.show()


data.dropna(inplace=True)
data['positions'] = np.where(data['SMA1'] > data['SMA2'],
1,
-1)

ax = data[[sym, 'SMA1', 'SMA2', 'positions']].plot(figsize=(10, 6),
    secondary_y='positions')
ax.get_legend().set_bbox_to_anchor((0.25, 0.85))
plt.show()

# data.plot(figsize=(10, 12), subplots=True)
# plt.show()