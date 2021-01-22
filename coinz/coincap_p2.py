# Cryptocurrency Alerts Project
#

# Packages
import os
import json
import time
import requests
from datetime import datetime

# Constants
convert = 'USD'
listings_url = 'https://api.coinmarketcap.com/v2/listings/?convert=' + convert
url_end = '?structure=array&convert=' + convert

# URL Request and store data
request = requests.get(listings_url)
results = request.json()
data = results['data']

# Create an array and store each data point in it 
ticker_url_pairs = {}
for currency in data:
    symbol = currency['symbol']
    url = currency['id']
    ticker_url_pairs[symbol] = url

print()
print('ALERTS TRACKING...')
print()

already_hit_symbols = []

# Execute loop through all input
while True:
    with open('alerts.txt') as inp:
        for line in inp:
            # Build URL to request
            ticker, amount = line.split()
            ticker = ticker.upper()
            ticker_url = 'https://api.coinmarketcap.com/v2/ticker/' + str(ticker_url_pairs[ticker]) + '/' + url_end

            # Execute URL request
            request = requests.get(ticker_url)
            results = request.json()

            # Store data in variables
            currency = results['data'][0]
            name = currency['name']
            last_updated = currency['last_updated']
            symbol = currency['symbol']
            quotes = currency['quotes'][convert]
            price = quotes['price']

            # Output unique values for each value
            if float(price) >= float(amount) and symbol not in already_hit_symbols:
                os.system('say ' + name + ' hit ' + amount)
                last_updated_string = datetime.fromtimestamp(last_updated).strftime('%B %d, %Y at %I:%M%p')
                print(name + ' hit ' + amount + ' on ' + last_updated_string)
                already_hit_symbols.append(symbol)

    # Seperate output and wait to begin next cycle
    print('...')
    time.sleep(300)
