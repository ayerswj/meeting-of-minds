# Level 2: Week 6 - File I/O & Error Handling

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Read from and write to different file types (text, CSV, JSON, binary)
- Handle file operations safely with context managers
- Implement comprehensive error handling and exception management
- Work with file paths and directories
- Create robust applications that handle errors gracefully

## 📚 Theory

### What is File I/O?
File Input/Output (I/O) refers to reading data from files and writing data to files. It's essential for persistent data storage and data processing.

### Why Error Handling?
Error handling ensures your programs can gracefully handle unexpected situations and provide meaningful feedback to users.

## 📁 File Operations Basics

### Opening and Closing Files
```python
# Basic file operations
file = open('example.txt', 'r')  # Open for reading
content = file.read()            # Read entire file
file.close()                     # Always close files

# Better approach with context manager
with open('example.txt', 'r') as file:
    content = file.read()
    # File automatically closes when exiting the block
```

### File Modes
```python
# Different file modes
with open('file.txt', 'r') as f:    # Read (default)
    pass
with open('file.txt', 'w') as f:    # Write (overwrites)
    pass
with open('file.txt', 'a') as f:    # Append
    pass
with open('file.txt', 'r+') as f:   # Read and write
    pass
with open('file.txt', 'b') as f:    # Binary mode
    pass
```

## 📖 Text File Operations

### Reading Text Files
```python
# Reading entire file
with open('sample.txt', 'r', encoding='utf-8') as file:
    content = file.read()
    print(content)

# Reading line by line
with open('sample.txt', 'r', encoding='utf-8') as file:
    for line in file:
        print(line.strip())  # Remove trailing whitespace

# Reading all lines into a list
with open('sample.txt', 'r', encoding='utf-8') as file:
    lines = file.readlines()
    print(lines)

# Reading specific number of characters
with open('sample.txt', 'r', encoding='utf-8') as file:
    chunk = file.read(100)  # Read first 100 characters
    print(chunk)
```

### Writing Text Files
```python
# Writing text to file
with open('output.txt', 'w', encoding='utf-8') as file:
    file.write('Hello, World!\n')
    file.write('This is a new line.\n')

# Writing multiple lines
lines = ['Line 1\n', 'Line 2\n', 'Line 3\n']
with open('output.txt', 'w', encoding='utf-8') as file:
    file.writelines(lines)

# Appending to existing file
with open('output.txt', 'a', encoding='utf-8') as file:
    file.write('This line will be appended.\n')
```

## 📊 CSV File Operations

### Reading CSV Files
```python
import csv

# Reading CSV with csv module
with open('data.csv', 'r', newline='', encoding='utf-8') as file:
    reader = csv.reader(file)
    header = next(reader)  # Skip header row
    for row in reader:
        print(f"Name: {row[0]}, Age: {row[1]}, City: {row[2]}")

# Reading CSV as dictionaries
with open('data.csv', 'r', newline='', encoding='utf-8') as file:
    reader = csv.DictReader(file)
    for row in reader:
        print(f"Name: {row['Name']}, Age: {row['Age']}, City: {row['City']}")

# Reading CSV with pandas (for data analysis)
import pandas as pd
df = pd.read_csv('data.csv')
print(df.head())
```

### Writing CSV Files
```python
import csv

# Writing CSV data
data = [
    ['Name', 'Age', 'City'],
    ['Alice', 25, 'New York'],
    ['Bob', 30, 'Los Angeles'],
    ['Charlie', 35, 'Chicago']
]

with open('output.csv', 'w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    writer.writerows(data)

# Writing CSV with dictionaries
data_dict = [
    {'Name': 'Alice', 'Age': 25, 'City': 'New York'},
    {'Name': 'Bob', 'Age': 30, 'City': 'Los Angeles'},
    {'Name': 'Charlie', 'Age': 35, 'City': 'Chicago'}
]

with open('output_dict.csv', 'w', newline='', encoding='utf-8') as file:
    fieldnames = ['Name', 'Age', 'City']
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()
    writer.writerows(data_dict)
```

## 🔧 JSON File Operations

### Reading JSON Files
```python
import json

# Reading JSON file
with open('data.json', 'r', encoding='utf-8') as file:
    data = json.load(file)
    print(data)

# Reading JSON string
json_string = '{"name": "Alice", "age": 25, "city": "New York"}'
data = json.loads(json_string)
print(data['name'])  # Alice
```

### Writing JSON Files
```python
import json

# Writing JSON data
data = {
    'name': 'Alice',
    'age': 25,
    'city': 'New York',
    'hobbies': ['reading', 'swimming', 'coding']
}

with open('output.json', 'w', encoding='utf-8') as file:
    json.dump(data, file, indent=2, ensure_ascii=False)

# Writing JSON with custom formatting
with open('output_pretty.json', 'w', encoding='utf-8') as file:
    json.dump(data, file, indent=4, sort_keys=True, ensure_ascii=False)

# Converting to JSON string
json_string = json.dumps(data, indent=2)
print(json_string)
```

## 🔍 Binary File Operations

### Reading Binary Files
```python
# Reading binary file (e.g., image)
with open('image.jpg', 'rb') as file:
    binary_data = file.read()
    print(f"File size: {len(binary_data)} bytes")

# Reading binary file in chunks
with open('large_file.bin', 'rb') as file:
    chunk_size = 1024  # 1KB chunks
    while True:
        chunk = file.read(chunk_size)
        if not chunk:
            break
        # Process chunk
        print(f"Read {len(chunk)} bytes")
```

### Writing Binary Files
```python
# Writing binary data
binary_data = b'\x00\x01\x02\x03\x04\x05'
with open('output.bin', 'wb') as file:
    file.write(binary_data)

# Copying binary file
with open('source.bin', 'rb') as source:
    with open('copy.bin', 'wb') as destination:
        destination.write(source.read())
```

## 🛡️ Error Handling

### Basic Exception Handling
```python
# Basic try-except
try:
    with open('nonexistent.txt', 'r') as file:
        content = file.read()
except FileNotFoundError:
    print("File not found!")
except PermissionError:
    print("Permission denied!")
except Exception as e:
    print(f"An error occurred: {e}")
```

### Advanced Exception Handling
```python
def read_file_safely(filename):
    """Safely read a file with comprehensive error handling"""
    try:
        with open(filename, 'r', encoding='utf-8') as file:
            return file.read()
    except FileNotFoundError:
        print(f"Error: File '{filename}' not found")
        return None
    except PermissionError:
        print(f"Error: Permission denied to read '{filename}'")
        return None
    except UnicodeDecodeError:
        print(f"Error: Unable to decode file '{filename}' with UTF-8")
        return None
    except Exception as e:
        print(f"Unexpected error reading '{filename}': {e}")
        return None
    finally:
        print(f"Attempted to read file: {filename}")

# Usage
content = read_file_safely('example.txt')
if content:
    print("File read successfully")
```

### Custom Exceptions
```python
class FileProcessingError(Exception):
    """Custom exception for file processing errors"""
    pass

class InvalidFileFormatError(FileProcessingError):
    """Raised when file format is invalid"""
    pass

class FileSizeError(FileProcessingError):
    """Raised when file is too large"""
    pass

def process_file(filename, max_size=1024*1024):  # 1MB limit
    try:
        with open(filename, 'r') as file:
            content = file.read()
            
        if len(content) > max_size:
            raise FileSizeError(f"File size {len(content)} exceeds limit {max_size}")
            
        if not content.strip():
            raise InvalidFileFormatError("File is empty")
            
        return content
        
    except FileNotFoundError:
        raise FileProcessingError(f"File '{filename}' not found")
    except Exception as e:
        raise FileProcessingError(f"Error processing file: {e}")

# Usage
try:
    content = process_file('large_file.txt')
    print("File processed successfully")
except FileProcessingError as e:
    print(f"File processing failed: {e}")
```

## 📂 File and Directory Operations

### Working with Paths
```python
import os
from pathlib import Path

# Using os.path (traditional)
file_path = os.path.join('folder', 'subfolder', 'file.txt')
print(os.path.exists(file_path))
print(os.path.isfile(file_path))
print(os.path.isdir(file_path))
print(os.path.getsize(file_path))

# Using pathlib (modern approach)
file_path = Path('folder') / 'subfolder' / 'file.txt'
print(file_path.exists())
print(file_path.is_file())
print(file_path.is_dir())
print(file_path.stat().st_size)
```

### Directory Operations
```python
import os
import shutil
from pathlib import Path

# Creating directories
os.makedirs('new_folder/subfolder', exist_ok=True)
Path('another_folder').mkdir(parents=True, exist_ok=True)

# Listing directory contents
for item in os.listdir('.'):
    print(item)

# Walking through directory tree
for root, dirs, files in os.walk('.'):
    print(f"Directory: {root}")
    for file in files:
        print(f"  File: {file}")

# Copying files and directories
shutil.copy('source.txt', 'destination.txt')
shutil.copytree('source_folder', 'destination_folder')

# Moving files
shutil.move('old_name.txt', 'new_name.txt')

# Removing files and directories
os.remove('file_to_delete.txt')
shutil.rmtree('folder_to_delete')
```

## 🎯 Practice Exercises

### Exercise 1: File Processing
Create a program that:
- Reads a text file and counts words, lines, and characters
- Writes statistics to a new file
- Handles various file errors gracefully

### Exercise 2: CSV Data Processor
Build a CSV processor that:
- Reads CSV files with different formats
- Validates data and handles missing values
- Exports processed data to different formats

### Exercise 3: Configuration Manager
Create a configuration manager that:
- Reads/writes JSON configuration files
- Validates configuration data
- Provides default values for missing settings

## 🚀 Mini-Project: Log File Analyzer

```python
import re
import json
from datetime import datetime
from collections import defaultdict, Counter
from pathlib import Path

class LogAnalyzer:
    def __init__(self, log_file_path):
        self.log_file_path = Path(log_file_path)
        self.log_entries = []
        self.error_patterns = {
            'error': r'ERROR',
            'warning': r'WARN',
            'info': r'INFO',
            'debug': r'DEBUG'
        }
    
    def parse_log_file(self):
        """Parse log file and extract structured data"""
        if not self.log_file_path.exists():
            raise FileNotFoundError(f"Log file not found: {self.log_file_path}")
        
        try:
            with open(self.log_file_path, 'r', encoding='utf-8') as file:
                for line_num, line in enumerate(file, 1):
                    try:
                        entry = self._parse_log_line(line.strip(), line_num)
                        if entry:
                            self.log_entries.append(entry)
                    except Exception as e:
                        print(f"Error parsing line {line_num}: {e}")
                        continue
        except UnicodeDecodeError:
            # Try with different encoding
            with open(self.log_file_path, 'r', encoding='latin-1') as file:
                for line_num, line in enumerate(file, 1):
                    try:
                        entry = self._parse_log_line(line.strip(), line_num)
                        if entry:
                            self.log_entries.append(entry)
                    except Exception as e:
                        print(f"Error parsing line {line_num}: {e}")
                        continue
    
    def _parse_log_line(self, line, line_num):
        """Parse individual log line"""
        if not line:
            return None
        
        # Common log format: [timestamp] level message
        pattern = r'\[(.*?)\]\s+(\w+)\s+(.*)'
        match = re.match(pattern, line)
        
        if match:
            timestamp_str, level, message = match.groups()
            try:
                timestamp = datetime.fromisoformat(timestamp_str.replace('Z', '+00:00'))
            except ValueError:
                timestamp = None
            
            return {
                'line_number': line_num,
                'timestamp': timestamp,
                'level': level.upper(),
                'message': message,
                'raw_line': line
            }
        else:
            # Fallback for non-standard formats
            return {
                'line_number': line_num,
                'timestamp': None,
                'level': 'UNKNOWN',
                'message': line,
                'raw_line': line
            }
    
    def get_statistics(self):
        """Generate comprehensive log statistics"""
        if not self.log_entries:
            return {}
        
        stats = {
            'total_entries': len(self.log_entries),
            'level_distribution': Counter(entry['level'] for entry in self.log_entries),
            'time_range': None,
            'error_summary': defaultdict(list),
            'common_patterns': Counter()
        }
        
        # Calculate time range
        timestamps = [entry['timestamp'] for entry in self.log_entries if entry['timestamp']]
        if timestamps:
            stats['time_range'] = {
                'start': min(timestamps),
                'end': max(timestamps),
                'duration': max(timestamps) - min(timestamps)
            }
        
        # Analyze errors and warnings
        for entry in self.log_entries:
            if entry['level'] in ['ERROR', 'WARN']:
                stats['error_summary'][entry['level']].append({
                    'line': entry['line_number'],
                    'message': entry['message'],
                    'timestamp': entry['timestamp']
                })
        
        # Find common patterns in messages
        for entry in self.log_entries:
            words = entry['message'].split()
            if words:
                stats['common_patterns'][words[0]] += 1
        
        return stats
    
    def export_report(self, output_file):
        """Export analysis report to JSON"""
        stats = self.get_statistics()
        
        # Convert datetime objects to strings for JSON serialization
        if stats['time_range']:
            stats['time_range']['start'] = stats['time_range']['start'].isoformat()
            stats['time_range']['end'] = stats['time_range']['end'].isoformat()
            stats['time_range']['duration'] = str(stats['time_range']['duration'])
        
        # Convert defaultdict to regular dict
        stats['error_summary'] = dict(stats['error_summary'])
        stats['level_distribution'] = dict(stats['level_distribution'])
        stats['common_patterns'] = dict(stats['common_patterns'])
        
        try:
            with open(output_file, 'w', encoding='utf-8') as file:
                json.dump(stats, file, indent=2, ensure_ascii=False)
            print(f"Report exported to {output_file}")
        except Exception as e:
            print(f"Error exporting report: {e}")
    
    def search_logs(self, search_term, level=None):
        """Search logs for specific terms"""
        results = []
        for entry in self.log_entries:
            if level and entry['level'] != level:
                continue
            if search_term.lower() in entry['message'].lower():
                results.append(entry)
        return results
    
    def generate_summary_report(self):
        """Generate a human-readable summary report"""
        stats = self.get_statistics()
        
        report = []
        report.append("=" * 50)
        report.append("LOG FILE ANALYSIS REPORT")
        report.append("=" * 50)
        report.append(f"Total log entries: {stats['total_entries']}")
        
        if stats['time_range']:
            report.append(f"Time range: {stats['time_range']['start']} to {stats['time_range']['end']}")
            report.append(f"Duration: {stats['time_range']['duration']}")
        
        report.append("\nLevel Distribution:")
        for level, count in stats['level_distribution'].items():
            percentage = (count / stats['total_entries']) * 100
            report.append(f"  {level}: {count} ({percentage:.1f}%)")
        
        report.append(f"\nErrors found: {len(stats['error_summary']['ERROR'])}")
        report.append(f"Warnings found: {len(stats['error_summary']['WARN'])}")
        
        if stats['common_patterns']:
            report.append("\nMost common message patterns:")
            for pattern, count in stats['common_patterns'].most_common(5):
                report.append(f"  {pattern}: {count}")
        
        return "\n".join(report)

# Usage example
def main():
    try:
        # Create sample log file for testing
        sample_log = """[2024-01-01T10:00:00] INFO Application started
[2024-01-01T10:01:00] INFO User login successful
[2024-01-01T10:02:00] WARN Database connection slow
[2024-01-01T10:03:00] ERROR Failed to process request
[2024-01-01T10:04:00] INFO Request processed successfully
[2024-01-01T10:05:00] DEBUG Debug information
[2024-01-01T10:06:00] ERROR Database connection failed"""
        
        with open('sample.log', 'w') as f:
            f.write(sample_log)
        
        # Analyze the log file
        analyzer = LogAnalyzer('sample.log')
        analyzer.parse_log_file()
        
        # Generate and display report
        print(analyzer.generate_summary_report())
        
        # Export detailed report
        analyzer.export_report('log_analysis.json')
        
        # Search for specific terms
        errors = analyzer.search_logs('error', level='ERROR')
        print(f"\nFound {len(errors)} error entries")
        
    except Exception as e:
        print(f"Error in main: {e}")

if __name__ == "__main__":
    main()
```

## 🎯 Advanced Concepts

### File Locking
```python
import fcntl
import time

def write_with_lock(filename, data):
    """Write data to file with exclusive lock"""
    with open(filename, 'w') as file:
        try:
            fcntl.flock(file.fileno(), fcntl.LOCK_EX)  # Exclusive lock
            file.write(data)
            time.sleep(1)  # Simulate processing time
        finally:
            fcntl.flock(file.fileno(), fcntl.LOCK_UN)  # Release lock
```

### Memory-Mapped Files
```python
import mmap

def process_large_file(filename):
    """Process large file using memory mapping"""
    with open(filename, 'r+b') as file:
        with mmap.mmap(file.fileno(), 0) as mm:
            # Access file content as if it's in memory
            print(f"File size: {len(mm)} bytes")
            print(f"First 100 bytes: {mm[:100]}")
```

## 📖 Additional Resources

- [Python File I/O](https://docs.python.org/3/tutorial/inputoutput.html#reading-and-writing-files)
- [Python Exception Handling](https://docs.python.org/3/tutorial/errors.html)
- [pathlib Documentation](https://docs.python.org/3/library/pathlib.html)
- [CSV Module](https://docs.python.org/3/library/csv.html)
- [JSON Module](https://docs.python.org/3/library/json.html)

## 🎯 Weekly Challenge

Create a **File Backup System** that:
1. Monitors a directory for file changes
2. Creates timestamped backups of modified files
3. Implements compression for backup files
4. Maintains a backup log with metadata
5. Provides restore functionality
6. Handles various file types and errors gracefully

## ✅ Checklist

- [ ] Can read from and write to text, CSV, and JSON files
- [ ] Understand file modes and encoding
- [ ] Can use context managers for safe file operations
- [ ] Can implement comprehensive error handling
- [ ] Can work with file paths and directories
- [ ] Can handle binary files
- [ ] Can create custom exceptions
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 7 - Modules & Packages](./../week7/README.md)