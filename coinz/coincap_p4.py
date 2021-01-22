# Future Value of Top 100 Cryptocurrencies
#

# Packages imported
import math
import json
import locale
import requests
from prettytable import PrettyTable

# Handles the language settings for the program
locale.setlocale(locale.LC_ALL, 'en_US.UTF-8')

# Constants
global_url = 'https://api.coinmarketcap.com/v2/global'
ticker_url = 'https://api.coinmarketcap.com/v2/ticker/?structure=array'

# Execute URL Request
request = requests.get(global_url)
results = request.json()

# Store data
data = results['data']
global_cap = int(data['quotes']['USD']['total_market_cap'])

# Format Data with PrettyTable package
table = PrettyTable(['Name', 'Ticker', '% of total global cap', 'Current', '7.7T (Gold)', '36.8T (Narrow Money)', '73T (World Stock Markets)', '90.4T (Broad Money)', '217T (Real Estate)', '544T (Derivatives)'])

# Execute second URL Request
request = requests.get(ticker_url)
results = request.json()
data = results['data']

# Loop through all data objects
for currency in data:

    # Extract variables from the JSON
    name = currency['name']
    ticker = currency['symbol']
    percentage_of_global_cap = float(currency['quotes']['USD']['market_cap']) / float(global_cap)
    current_price = round(float(currency['quotes']['USD']['price']),2)
    available_supply = float(currency['total_supply'])

    # Perform Calculations to predict different market cap prices.
    trillion7price = round(7700000000000 * percentage_of_global_cap / available_supply,2)
    trillion36price = round(36000000000000 * percentage_of_global_cap / available_supply,2)
    trillion73price = round(73000000000000 * percentage_of_global_cap / available_supply,2)
    trillion90price = round(90400000000000 * percentage_of_global_cap / available_supply,2)
    trillion217price = round(217000000000000 * percentage_of_global_cap / available_supply,2)
    trillion544price = round(544000000000000 * percentage_of_global_cap / available_supply,2)

    # Format calculations
    percentage_of_global_cap_string = str(round(percentage_of_global_cap*100,2)) + '%'
    current_price_string = '$' + str(current_price)
    trillion7price_string = '$' + locale.format('%.2f',trillion7price,True)
    trillion36price_string = '$' + locale.format('%.2f',trillion36price,True)
    trillion73price_string = '$' + locale.format('%.2f',trillion73price,True)
    trillion90price_string = '$' + locale.format('%.2f',trillion90price,True)
    trillion217price_string = '$' + locale.format('%.2f',trillion217price,True)
    trillion544price_string = '$' + locale.format('%.2f',trillion544price,True)

    # Insert values into PrettyTable we declared
    table.add_row([name,
                   ticker,
                   percentage_of_global_cap_string,
                   current_price_string,
                   trillion7price_string,
                   trillion36price_string,
                   trillion73price_string,
                   trillion90price_string,
                   trillion217price_string,
                   trillion544price_string])

# Print Table for each data object
print()
print(table)
print()
