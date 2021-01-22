# Cryptocurrency Portfolio Project
#

# Packages
import os
import json
from requests import Request, Session
from datetime import datetime
from prettytable import PrettyTable
from colorama import Fore, Back, Style

# Constants
listings_url = 'https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest'
parameters = {
    'start': '1',
    'limit': '100',
    'convert': 'USD'
}
headers = {
  'Accepts': 'application/json',
  'X-CMC_PRO_API_KEY': '',
}

# Call endpoint and store data
session = Session()
session.headers.update(headers)
request = session.get(listings_url, params=parameters)
print(request) # Testing Response code
results = json.loads(request.text)
data = results['data']

# Create an array and store all data 1 by 1 in it
ticker_url_pairs = {}
for currency in data:
    symbol = currency['symbol']
    url = currency['id']
    ticker_url_pairs[symbol] = url
    print(url)
    print(symbol)
    print(ticker_url_pairs)

# Print stuff
print()
print('MY PORTFOLIO')
print()

# Initialize zero values
portfolio_value = 0.00
last_updated = 0

# Formate table values with a import package
table = PrettyTable(['Asset', 'Amount Owned', 'USD' + ' Value', 'Price', '1h', '24h', '7d'])

# Open portfolio input
with open('portfolio.txt') as inp:
    # Loop through for each line execute this script
    for line in inp:
        # Build URL to request
        ticker, amount = line.split()
        ticker = ticker.upper()
        ticker_url = 'https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/historical/'

        # Execute URL request
        parameters = {
            'slug': ticker,
            'convert': 'USD',
        }

        request = session.get(ticker_url, params=parameters)
        results = json.loads(request.text)

        print(results)
        # Store all data in variables to manipulate
        currency = results['data'][0]
        rank = currency['rank']
        name = currency['name']
        last_updated = currency['last_updated']
        symbol = currency['symbol']
        quotes = currency['quotes'][convert]
        hour_change = quotes['percent_change_1h']
        day_change = quotes['percent_change_24h']
        week_change = quotes['percent_change_7d']
        price = quotes['price']
        value = float(price) * float(amount)

        # Color format terminal based on time changes
        if hour_change > 0:
            hour_change = Back.GREEN + str(hour_change) + '%' + Style.RESET_ALL
        else:
            hour_change = Back.RED + str(hour_change) + '%' + Style.RESET_ALL

        if day_change > 0:
            day_change = Back.GREEN + str(day_change) + '%' + Style.RESET_ALL
        else:
            day_change = Back.RED + str(day_change) + '%' + Style.RESET_ALL

        if week_change > 0:
            week_change = Back.GREEN + str(week_change) + '%' + Style.RESET_ALL
        else:
            week_change = Back.RED + str(week_change) + '%' + Style.RESET_ALL

        # Count portfolio value
        portfolio_value += value

        value_string = '{:,}'.format(round(value,2))

        table.add_row([name + ' (' + symbol + ')',
                       amount,
                       '$' + value_string,
                       '$' + str(price),
                       str(hour_change),
                       str(day_change),
                       str(week_change)])


# Print output
print(table)
print()

portfolio_value_string = '{:,}'.format(round(portfolio_value,2))
last_updated_string = datetime.fromtimestamp(last_updated).strftime('%B %d, %Y at %I:%M%p')

# Print output that was generated
print('Total Portfolio Value: ' + Back.GREEN + '$' + portfolio_value_string + Style.RESET_ALL)
print()
print('API Results Last Updated on ' + last_updated_string)
print()
