# Level 2: Week 8 - Web Scraping & APIs

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Extract data from websites using web scraping techniques
- Work with REST APIs and handle HTTP requests
- Parse HTML and XML content
- Implement rate limiting and respectful scraping
- Build applications that integrate with external services

## 📚 Theory

### What is Web Scraping?
Web scraping is the process of extracting data from websites. It involves fetching web pages and parsing their content to extract useful information.

### What are APIs?
Application Programming Interfaces (APIs) allow applications to communicate with each other. REST APIs use HTTP requests to exchange data.

## 🌐 HTTP Fundamentals

### Understanding HTTP Requests
```python
import requests

# Basic GET request
response = requests.get('https://api.github.com/users/octocat')
print(f"Status Code: {response.status_code}")
print(f"Headers: {response.headers}")
print(f"Content: {response.text}")

# POST request with data
data = {'username': 'john_doe', 'email': 'john@example.com'}
response = requests.post('https://httpbin.org/post', json=data)
print(response.json())

# Request with headers
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36',
    'Accept': 'application/json'
}
response = requests.get('https://api.github.com/users/octocat', headers=headers)
```

### HTTP Status Codes
```python
def handle_response(response):
    """Handle different HTTP status codes"""
    if response.status_code == 200:
        return response.json()
    elif response.status_code == 404:
        print("Resource not found")
        return None
    elif response.status_code == 429:
        print("Rate limit exceeded")
        return None
    elif response.status_code >= 500:
        print("Server error")
        return None
    else:
        print(f"Unexpected status code: {response.status_code}")
        return None
```

## 🕷️ Web Scraping with Beautiful Soup

### Basic HTML Parsing
```python
import requests
from bs4 import BeautifulSoup

# Fetch a web page
url = 'https://quotes.toscrape.com/'
response = requests.get(url)
soup = BeautifulSoup(response.text, 'html.parser')

# Find elements by tag
title = soup.find('title')
print(f"Page title: {title.text}")

# Find elements by class
quotes = soup.find_all('div', class_='quote')
for quote in quotes:
    text = quote.find('span', class_='text').text
    author = quote.find('small', class_='author').text
    print(f"Quote: {text}")
    print(f"Author: {author}")
    print("-" * 50)
```

### Advanced HTML Parsing
```python
# Find elements by CSS selectors
quotes = soup.select('div.quote span.text')
authors = soup.select('div.quote small.author')

for quote, author in zip(quotes, authors):
    print(f"'{quote.text}' - {author.text}")

# Find elements by attributes
links = soup.find_all('a', href=True)
for link in links:
    print(f"Link: {link['href']}")

# Extract data from tables
tables = soup.find_all('table')
for table in tables:
    rows = table.find_all('tr')
    for row in rows:
        cells = row.find_all(['td', 'th'])
        for cell in cells:
            print(cell.text.strip(), end='\t')
        print()
```

### Web Scraping Best Practices
```python
import time
import random
from urllib.robotparser import RobotFileParser

class RespectfulScraper:
    def __init__(self, base_url, delay_range=(1, 3)):
        self.base_url = base_url
        self.delay_range = delay_range
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': 'MyBot/1.0 (educational project)'
        })
        
        # Check robots.txt
        self.rp = RobotFileParser()
        self.rp.set_url(f"{base_url}/robots.txt")
        self.rp.read()
    
    def can_fetch(self, url):
        """Check if we can fetch the URL according to robots.txt"""
        return self.rp.can_fetch(self.session.headers['User-Agent'], url)
    
    def get_page(self, url):
        """Fetch a page with rate limiting"""
        if not self.can_fetch(url):
            print(f"Cannot fetch {url} according to robots.txt")
            return None
        
        # Random delay to be respectful
        time.sleep(random.uniform(*self.delay_range))
        
        try:
            response = self.session.get(url)
            response.raise_for_status()
            return response
        except requests.RequestException as e:
            print(f"Error fetching {url}: {e}")
            return None
    
    def scrape_quotes(self, max_pages=5):
        """Scrape quotes from multiple pages"""
        quotes = []
        
        for page in range(1, max_pages + 1):
            url = f"{self.base_url}/page/{page}/"
            response = self.get_page(url)
            
            if not response:
                break
            
            soup = BeautifulSoup(response.text, 'html.parser')
            page_quotes = soup.find_all('div', class_='quote')
            
            if not page_quotes:
                break
            
            for quote in page_quotes:
                text = quote.find('span', class_='text').text
                author = quote.find('small', class_='author').text
                tags = [tag.text for tag in quote.find_all('a', class_='tag')]
                
                quotes.append({
                    'text': text,
                    'author': author,
                    'tags': tags
                })
            
            print(f"Scraped {len(page_quotes)} quotes from page {page}")
        
        return quotes

# Usage
scraper = RespectfulScraper('https://quotes.toscrape.com')
quotes = scraper.scrape_quotes(max_pages=3)
print(f"Total quotes scraped: {len(quotes)}")
```

## 🔌 Working with APIs

### REST API Basics
```python
import requests
import json

class APIClient:
    def __init__(self, base_url, api_key=None):
        self.base_url = base_url.rstrip('/')
        self.session = requests.Session()
        
        if api_key:
            self.session.headers.update({
                'Authorization': f'Bearer {api_key}'
            })
        
        self.session.headers.update({
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        })
    
    def get(self, endpoint, params=None):
        """Make a GET request"""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        response = self.session.get(url, params=params)
        response.raise_for_status()
        return response.json()
    
    def post(self, endpoint, data=None):
        """Make a POST request"""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        response = self.session.post(url, json=data)
        response.raise_for_status()
        return response.json()
    
    def put(self, endpoint, data=None):
        """Make a PUT request"""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        response = self.session.put(url, json=data)
        response.raise_for_status()
        return response.json()
    
    def delete(self, endpoint):
        """Make a DELETE request"""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        response = self.session.delete(url)
        response.raise_for_status()
        return response.json()

# Example usage with JSONPlaceholder API
api = APIClient('https://jsonplaceholder.typicode.com')

# Get all posts
posts = api.get('/posts')
print(f"Found {len(posts)} posts")

# Get a specific post
post = api.get('/posts/1')
print(f"Post title: {post['title']}")

# Create a new post
new_post = {
    'title': 'My New Post',
    'body': 'This is the content of my new post',
    'userId': 1
}
created_post = api.post('/posts', new_post)
print(f"Created post with ID: {created_post['id']}")
```

### Working with Public APIs
```python
import requests
from datetime import datetime

class WeatherAPI:
    def __init__(self, api_key):
        self.api_key = api_key
        self.base_url = "http://api.openweathermap.org/data/2.5"
    
    def get_current_weather(self, city, country_code=None):
        """Get current weather for a city"""
        location = f"{city},{country_code}" if country_code else city
        
        params = {
            'q': location,
            'appid': self.api_key,
            'units': 'metric'
        }
        
        response = requests.get(f"{self.base_url}/weather", params=params)
        response.raise_for_status()
        
        data = response.json()
        return {
            'city': data['name'],
            'country': data['sys']['country'],
            'temperature': data['main']['temp'],
            'feels_like': data['main']['feels_like'],
            'humidity': data['main']['humidity'],
            'description': data['weather'][0]['description'],
            'wind_speed': data['wind']['speed'],
            'timestamp': datetime.fromtimestamp(data['dt'])
        }
    
    def get_forecast(self, city, country_code=None):
        """Get 5-day forecast for a city"""
        location = f"{city},{country_code}" if country_code else city
        
        params = {
            'q': location,
            'appid': self.api_key,
            'units': 'metric'
        }
        
        response = requests.get(f"{self.base_url}/forecast", params=params)
        response.raise_for_status()
        
        data = response.json()
        forecasts = []
        
        for item in data['list']:
            forecast = {
                'datetime': datetime.fromtimestamp(item['dt']),
                'temperature': item['main']['temp'],
                'description': item['weather'][0]['description'],
                'humidity': item['main']['humidity']
            }
            forecasts.append(forecast)
        
        return forecasts

# Usage (requires API key from openweathermap.org)
# weather_api = WeatherAPI('your_api_key_here')
# current = weather_api.get_current_weather('London', 'UK')
# print(f"Current temperature in London: {current['temperature']}°C")
```

## 📊 Data Extraction and Processing

### Extracting Structured Data
```python
import pandas as pd
from bs4 import BeautifulSoup
import requests

class DataExtractor:
    def __init__(self):
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'
        })
    
    def extract_table_data(self, url, table_selector='table'):
        """Extract data from HTML tables"""
        response = self.session.get(url)
        soup = BeautifulSoup(response.text, 'html.parser')
        
        tables = soup.select(table_selector)
        all_data = []
        
        for table in tables:
            rows = table.find_all('tr')
            table_data = []
            
            for row in rows:
                cells = row.find_all(['td', 'th'])
                row_data = [cell.get_text(strip=True) for cell in cells]
                if row_data:  # Skip empty rows
                    table_data.append(row_data)
            
            if table_data:
                # Use first row as header
                df = pd.DataFrame(table_data[1:], columns=table_data[0])
                all_data.append(df)
        
        return all_data
    
    def extract_list_data(self, url, item_selector, fields):
        """Extract data from list items"""
        response = self.session.get(url)
        soup = BeautifulSoup(response.text, 'html.parser')
        
        items = soup.select(item_selector)
        data = []
        
        for item in items:
            item_data = {}
            for field_name, selector in fields.items():
                element = item.select_one(selector)
                item_data[field_name] = element.get_text(strip=True) if element else None
            data.append(item_data)
        
        return pd.DataFrame(data)

# Example: Extract product data from an e-commerce site
def extract_products():
    extractor = DataExtractor()
    
    # Define the selectors for product information
    fields = {
        'name': 'h3.product-title',
        'price': 'span.price',
        'rating': 'span.rating',
        'description': 'p.description'
    }
    
    # Extract data (replace with actual URL and selectors)
    # products_df = extractor.extract_list_data(
    #     'https://example-store.com/products',
    #     'div.product-item',
    #     fields
    # )
    
    # return products_df
    pass
```

## 🔄 Asynchronous Web Scraping

### Using asyncio for Concurrent Scraping
```python
import asyncio
import aiohttp
from bs4 import BeautifulSoup
import time

class AsyncScraper:
    def __init__(self, delay=1):
        self.delay = delay
        self.session = None
    
    async def __aenter__(self):
        self.session = aiohttp.ClientSession()
        return self
    
    async def __aexit__(self, exc_type, exc_val, exc_tb):
        if self.session:
            await self.session.close()
    
    async def fetch_page(self, url):
        """Fetch a single page asynchronously"""
        try:
            async with self.session.get(url) as response:
                if response.status == 200:
                    content = await response.text()
                    return content
                else:
                    print(f"Error fetching {url}: {response.status}")
                    return None
        except Exception as e:
            print(f"Exception fetching {url}: {e}")
            return None
    
    async def scrape_urls(self, urls):
        """Scrape multiple URLs concurrently"""
        tasks = []
        for url in urls:
            task = asyncio.create_task(self.fetch_page(url))
            tasks.append(task)
            await asyncio.sleep(self.delay)  # Rate limiting
        
        results = await asyncio.gather(*tasks, return_exceptions=True)
        return results
    
    def parse_content(self, html_content):
        """Parse HTML content and extract data"""
        if not html_content:
            return None
        
        soup = BeautifulSoup(html_content, 'html.parser')
        
        # Extract title
        title = soup.find('title')
        title_text = title.text if title else 'No title'
        
        # Extract links
        links = soup.find_all('a', href=True)
        link_urls = [link['href'] for link in links]
        
        # Extract text content
        text_content = soup.get_text()
        
        return {
            'title': title_text,
            'links': link_urls,
            'text_length': len(text_content),
            'link_count': len(link_urls)
        }

# Usage
async def main():
    urls = [
        'https://quotes.toscrape.com/page/1/',
        'https://quotes.toscrape.com/page/2/',
        'https://quotes.toscrape.com/page/3/'
    ]
    
    async with AsyncScraper(delay=0.5) as scraper:
        start_time = time.time()
        results = await scraper.scrape_urls(urls)
        
        for i, result in enumerate(results):
            if isinstance(result, str):
                parsed = scraper.parse_content(result)
                print(f"Page {i+1}: {parsed['title']} - {parsed['link_count']} links")
            else:
                print(f"Page {i+1}: Error")
        
        end_time = time.time()
        print(f"Total time: {end_time - start_time:.2f} seconds")

# Run the async scraper
# asyncio.run(main())
```

## 🎯 Practice Exercises

### Exercise 1: News Scraper
Create a news scraper that:
- Extracts headlines and summaries from a news website
- Stores data in a structured format
- Implements rate limiting and error handling

### Exercise 2: API Integration
Build an application that:
- Integrates with multiple APIs (weather, news, etc.)
- Combines data from different sources
- Provides a unified interface

### Exercise 3: E-commerce Price Tracker
Create a price tracking system that:
- Monitors product prices across multiple sites
- Stores historical price data
- Sends notifications for price changes

## 🚀 Mini-Project: Job Market Analyzer

```python
import requests
from bs4 import BeautifulSoup
import pandas as pd
import json
from datetime import datetime
import time
from collections import Counter

class JobMarketAnalyzer:
    def __init__(self):
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'
        })
        self.jobs_data = []
    
    def scrape_indeed_jobs(self, job_title, location, max_pages=3):
        """Scrape job listings from Indeed (example structure)"""
        base_url = "https://www.indeed.com/jobs"
        
        for page in range(max_pages):
            params = {
                'q': job_title,
                'l': location,
                'start': page * 10
            }
            
            try:
                response = self.session.get(base_url, params=params)
                response.raise_for_status()
                
                soup = BeautifulSoup(response.text, 'html.parser')
                
                # Find job listings (adjust selectors based on actual site structure)
                job_cards = soup.find_all('div', class_='job_seen_beacon')
                
                for card in job_cards:
                    try:
                        job_data = self._extract_job_data(card)
                        if job_data:
                            self.jobs_data.append(job_data)
                    except Exception as e:
                        print(f"Error extracting job data: {e}")
                        continue
                
                print(f"Scraped {len(job_cards)} jobs from page {page + 1}")
                time.sleep(2)  # Be respectful
                
            except Exception as e:
                print(f"Error scraping page {page + 1}: {e}")
                break
    
    def _extract_job_data(self, job_card):
        """Extract job information from a job card"""
        try:
            # Extract job title
            title_elem = job_card.find('h2', class_='jobTitle')
            title = title_elem.get_text(strip=True) if title_elem else 'N/A'
            
            # Extract company name
            company_elem = job_card.find('span', class_='companyName')
            company = company_elem.get_text(strip=True) if company_elem else 'N/A'
            
            # Extract location
            location_elem = job_card.find('div', class_='companyLocation')
            location = location_elem.get_text(strip=True) if location_elem else 'N/A'
            
            # Extract salary (if available)
            salary_elem = job_card.find('div', class_='salary-snippet')
            salary = salary_elem.get_text(strip=True) if salary_elem else 'N/A'
            
            # Extract job description snippet
            desc_elem = job_card.find('div', class_='job-snippet')
            description = desc_elem.get_text(strip=True) if desc_elem else 'N/A'
            
            return {
                'title': title,
                'company': company,
                'location': location,
                'salary': salary,
                'description': description,
                'scraped_at': datetime.now().isoformat()
            }
        except Exception as e:
            print(f"Error extracting job data: {e}")
            return None
    
    def analyze_jobs(self):
        """Analyze the collected job data"""
        if not self.jobs_data:
            return {}
        
        df = pd.DataFrame(self.jobs_data)
        
        analysis = {
            'total_jobs': len(df),
            'unique_companies': df['company'].nunique(),
            'locations': df['location'].value_counts().to_dict(),
            'top_companies': df['company'].value_counts().head(10).to_dict(),
            'salary_analysis': self._analyze_salaries(df),
            'skill_analysis': self._analyze_skills(df),
            'scraped_at': datetime.now().isoformat()
        }
        
        return analysis
    
    def _analyze_salaries(self, df):
        """Analyze salary information"""
        # Extract numeric salary values (simplified)
        salary_data = []
        for salary in df['salary']:
            if salary != 'N/A':
                # Extract numbers from salary string
                import re
                numbers = re.findall(r'\d+', salary)
                if numbers:
                    salary_data.extend([int(num) for num in numbers])
        
        if salary_data:
            return {
                'average_salary': sum(salary_data) / len(salary_data),
                'min_salary': min(salary_data),
                'max_salary': max(salary_data),
                'salary_count': len(salary_data)
            }
        else:
            return {'average_salary': 0, 'min_salary': 0, 'max_salary': 0, 'salary_count': 0}
    
    def _analyze_skills(self, df):
        """Analyze required skills from job descriptions"""
        skills_keywords = [
            'python', 'java', 'javascript', 'sql', 'aws', 'docker', 'kubernetes',
            'react', 'angular', 'vue', 'node.js', 'machine learning', 'ai',
            'data science', 'devops', 'agile', 'scrum'
        ]
        
        skill_counts = Counter()
        
        for description in df['description']:
            description_lower = description.lower()
            for skill in skills_keywords:
                if skill in description_lower:
                    skill_counts[skill] += 1
        
        return dict(skill_counts.most_common(10))
    
    def export_data(self, filename):
        """Export job data to JSON"""
        analysis = self.analyze_jobs()
        
        with open(filename, 'w', encoding='utf-8') as f:
            json.dump(analysis, f, indent=2, ensure_ascii=False)
        
        print(f"Analysis exported to {filename}")
    
    def generate_report(self):
        """Generate a human-readable report"""
        analysis = self.analyze_jobs()
        
        report = []
        report.append("=" * 60)
        report.append("JOB MARKET ANALYSIS REPORT")
        report.append("=" * 60)
        report.append(f"Total jobs analyzed: {analysis['total_jobs']}")
        report.append(f"Unique companies: {analysis['unique_companies']}")
        
        report.append(f"\nTop 5 Locations:")
        for location, count in list(analysis['locations'].items())[:5]:
            report.append(f"  {location}: {count} jobs")
        
        report.append(f"\nTop 5 Companies:")
        for company, count in list(analysis['top_companies'].items())[:5]:
            report.append(f"  {company}: {count} jobs")
        
        salary_info = analysis['salary_analysis']
        if salary_info['salary_count'] > 0:
            report.append(f"\nSalary Analysis:")
            report.append(f"  Average: ${salary_info['average_salary']:,.0f}")
            report.append(f"  Range: ${salary_info['min_salary']:,} - ${salary_info['max_salary']:,}")
        
        report.append(f"\nTop 5 Required Skills:")
        for skill, count in list(analysis['skill_analysis'].items())[:5]:
            report.append(f"  {skill}: {count} mentions")
        
        return "\n".join(report)

# Usage example
def main():
    analyzer = JobMarketAnalyzer()
    
    # Scrape Python developer jobs in New York
    print("Scraping job data...")
    analyzer.scrape_indeed_jobs('Python Developer', 'New York, NY', max_pages=2)
    
    # Generate and display report
    print("\n" + analyzer.generate_report())
    
    # Export data
    analyzer.export_data('job_analysis.json')

# Note: This is a simplified example. Real implementation would need to:
# 1. Handle actual website structure and selectors
# 2. Implement proper rate limiting
# 3. Handle pagination correctly
# 4. Respect robots.txt and terms of service
# 5. Add error handling and retry logic

if __name__ == "__main__":
    # main()  # Uncomment to run (requires proper setup)
    pass
```

## 🎯 Advanced Concepts

### Selenium for Dynamic Content
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

class SeleniumScraper:
    def __init__(self, headless=True):
        options = webdriver.ChromeOptions()
        if headless:
            options.add_argument('--headless')
        
        self.driver = webdriver.Chrome(options=options)
        self.wait = WebDriverWait(self.driver, 10)
    
    def scrape_dynamic_content(self, url):
        """Scrape content that requires JavaScript"""
        try:
            self.driver.get(url)
            
            # Wait for content to load
            self.wait.until(EC.presence_of_element_located((By.TAG_NAME, "body")))
            
            # Scroll to load more content
            self.driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            time.sleep(2)
            
            # Extract data
            page_source = self.driver.page_source
            soup = BeautifulSoup(page_source, 'html.parser')
            
            return soup
            
        except Exception as e:
            print(f"Error scraping {url}: {e}")
            return None
    
    def close(self):
        """Close the browser"""
        self.driver.quit()

# Usage
# scraper = SeleniumScraper()
# content = scraper.scrape_dynamic_content('https://example.com')
# scraper.close()
```

## 📖 Additional Resources

- [Beautiful Soup Documentation](https://www.crummy.com/software/BeautifulSoup/)
- [Requests Library](https://requests.readthedocs.io/)
- [aiohttp Documentation](https://aiohttp.readthedocs.io/)
- [Selenium Documentation](https://selenium-python.readthedocs.io/)
- [Web Scraping Ethics](https://www.scrapehero.com/how-to-prevent-getting-blacklisted-while-scraping/)

## 🎯 Weekly Challenge

Create a **Multi-Source Data Aggregator** that:
1. Scrapes data from multiple websites
2. Integrates with various APIs
3. Combines and normalizes data
4. Provides data visualization
5. Implements caching and rate limiting
6. Generates comprehensive reports

## ✅ Checklist

- [ ] Can make HTTP requests and handle responses
- [ ] Can parse HTML content with Beautiful Soup
- [ ] Can work with REST APIs
- [ ] Understand web scraping ethics and best practices
- [ ] Can implement rate limiting and error handling
- [ ] Can extract structured data from websites
- [ ] Can work with asynchronous requests
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 9 - Data Analysis & Visualization](./../level3/week9/README.md)