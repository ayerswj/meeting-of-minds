# Level 3: Week 9 - Data Analysis & Visualization

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Use pandas for data manipulation and analysis
- Create various types of visualizations with matplotlib and seaborn
- Perform data cleaning and preprocessing
- Conduct exploratory data analysis (EDA)
- Generate insights from data

## 📚 Theory

### What is Data Analysis?
Data analysis is the process of inspecting, cleaning, transforming, and modeling data to discover useful information, draw conclusions, and support decision-making.

### Key Libraries
- **pandas**: Data manipulation and analysis
- **matplotlib**: Basic plotting and visualization
- **seaborn**: Statistical data visualization
- **numpy**: Numerical computing (used by pandas)

## 📊 Getting Started with Pandas

### Installing Required Libraries
```bash
pip install pandas matplotlib seaborn numpy
```

### Basic Pandas Operations
```python
import pandas as pd
import numpy as np

# Creating a DataFrame
data = {
    'Name': ['Alice', 'Bob', 'Charlie', 'Diana'],
    'Age': [25, 30, 35, 28],
    'Salary': [50000, 60000, 75000, 55000],
    'Department': ['IT', 'HR', 'IT', 'Marketing']
}

df = pd.DataFrame(data)
print(df)
```

### Reading Data from Files
```python
# Reading CSV files
df = pd.read_csv('data.csv')

# Reading Excel files
df = pd.read_excel('data.xlsx')

# Reading JSON files
df = pd.read_json('data.json')

# Display basic information
print(df.info())
print(df.describe())
```

## 🔍 Data Exploration

### Basic DataFrame Operations
```python
# View first few rows
print(df.head())

# View last few rows
print(df.tail())

# Get column names
print(df.columns)

# Get data types
print(df.dtypes)

# Get shape (rows, columns)
print(df.shape)

# Check for missing values
print(df.isnull().sum())
```

### Data Selection and Filtering
```python
# Select specific columns
names = df['Name']
subset = df[['Name', 'Age']]

# Filter data
young_employees = df[df['Age'] < 30]
it_employees = df[df['Department'] == 'IT']
high_salary = df[df['Salary'] > 60000]

# Multiple conditions
senior_it = df[(df['Age'] > 30) & (df['Department'] == 'IT')]
```

### Data Aggregation
```python
# Group by department and calculate statistics
dept_stats = df.groupby('Department').agg({
    'Age': ['mean', 'min', 'max'],
    'Salary': ['mean', 'sum', 'count']
})

# Calculate summary statistics
print(df.groupby('Department')['Salary'].mean())
print(df.groupby('Department')['Age'].median())
```

## 📈 Data Visualization

### Basic Plots with Matplotlib
```python
import matplotlib.pyplot as plt

# Line plot
plt.plot(df['Age'], df['Salary'], 'o-')
plt.xlabel('Age')
plt.ylabel('Salary')
plt.title('Age vs Salary')
plt.show()

# Bar plot
dept_counts = df['Department'].value_counts()
plt.bar(dept_counts.index, dept_counts.values)
plt.xlabel('Department')
plt.ylabel('Number of Employees')
plt.title('Employees by Department')
plt.show()

# Histogram
plt.hist(df['Age'], bins=10, edgecolor='black')
plt.xlabel('Age')
plt.ylabel('Frequency')
plt.title('Age Distribution')
plt.show()
```

### Advanced Plots with Seaborn
```python
import seaborn as sns

# Set style
sns.set_style("whitegrid")

# Box plot
sns.boxplot(x='Department', y='Salary', data=df)
plt.title('Salary Distribution by Department')
plt.show()

# Violin plot
sns.violinplot(x='Department', y='Age', data=df)
plt.title('Age Distribution by Department')
plt.show()

# Correlation heatmap
correlation_matrix = df[['Age', 'Salary']].corr()
sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm')
plt.title('Correlation Heatmap')
plt.show()

# Pair plot
sns.pairplot(df[['Age', 'Salary']])
plt.show()
```

## 🧹 Data Cleaning

### Handling Missing Values
```python
# Check for missing values
print(df.isnull().sum())

# Remove rows with missing values
df_clean = df.dropna()

# Fill missing values
df['Age'].fillna(df['Age'].mean(), inplace=True)
df['Department'].fillna('Unknown', inplace=True)

# Forward fill
df.fillna(method='ffill', inplace=True)
```

### Data Type Conversion
```python
# Convert data types
df['Age'] = df['Age'].astype(int)
df['Salary'] = df['Salary'].astype(float)

# Convert to datetime
df['Date'] = pd.to_datetime(df['Date'])
```

### Removing Duplicates
```python
# Check for duplicates
print(df.duplicated().sum())

# Remove duplicates
df = df.drop_duplicates()
```

## 🎯 Practice Exercises

### Exercise 1: Sales Data Analysis
Analyze a sales dataset and create visualizations showing trends.

### Exercise 2: Customer Segmentation
Use clustering techniques to segment customers based on their behavior.

### Exercise 3: Time Series Analysis
Analyze time series data and create forecasting models.

## 🚀 Mini-Project: Employee Performance Analysis

```python
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Create sample employee data
np.random.seed(42)
n_employees = 100

data = {
    'Employee_ID': range(1, n_employees + 1),
    'Name': [f'Employee_{i}' for i in range(1, n_employees + 1)],
    'Age': np.random.normal(35, 10, n_employees).astype(int),
    'Department': np.random.choice(['IT', 'HR', 'Marketing', 'Sales'], n_employees),
    'Years_Experience': np.random.normal(8, 3, n_employees).astype(int),
    'Salary': np.random.normal(60000, 15000, n_employees),
    'Performance_Score': np.random.normal(75, 15, n_employees),
    'Projects_Completed': np.random.poisson(5, n_employees)
}

df = pd.DataFrame(data)

# Data exploration
print("Dataset Shape:", df.shape)
print("\nFirst few rows:")
print(df.head())
print("\nData types:")
print(df.dtypes)
print("\nSummary statistics:")
print(df.describe())

# Create visualizations
fig, axes = plt.subplots(2, 2, figsize=(15, 10))

# 1. Salary distribution by department
sns.boxplot(x='Department', y='Salary', data=df, ax=axes[0,0])
axes[0,0].set_title('Salary Distribution by Department')

# 2. Age vs Performance correlation
sns.scatterplot(x='Age', y='Performance_Score', data=df, ax=axes[0,1])
axes[0,1].set_title('Age vs Performance Score')

# 3. Experience vs Salary
sns.scatterplot(x='Years_Experience', y='Salary', data=df, ax=axes[1,0])
axes[1,0].set_title('Years of Experience vs Salary')

# 4. Performance score distribution
sns.histplot(df['Performance_Score'], bins=20, ax=axes[1,1])
axes[1,1].set_title('Performance Score Distribution')

plt.tight_layout()
plt.show()

# Statistical analysis
print("\nDepartment-wise analysis:")
dept_analysis = df.groupby('Department').agg({
    'Salary': ['mean', 'std'],
    'Performance_Score': ['mean', 'std'],
    'Projects_Completed': ['mean', 'sum']
}).round(2)
print(dept_analysis)

# Correlation analysis
correlation_matrix = df[['Age', 'Years_Experience', 'Salary', 'Performance_Score', 'Projects_Completed']].corr()
plt.figure(figsize=(10, 8))
sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm', center=0)
plt.title('Correlation Matrix')
plt.show()
```

## 🎯 Advanced Concepts

### Pivot Tables
```python
# Create pivot table
pivot_table = df.pivot_table(
    values='Salary',
    index='Department',
    columns='Years_Experience',
    aggfunc='mean'
)
print(pivot_table)
```

### Time Series Analysis
```python
# Create time series data
dates = pd.date_range('2023-01-01', periods=100, freq='D')
time_series_data = pd.DataFrame({
    'Date': dates,
    'Sales': np.random.normal(1000, 200, 100).cumsum()
})

# Plot time series
plt.figure(figsize=(12, 6))
plt.plot(time_series_data['Date'], time_series_data['Sales'])
plt.title('Sales Over Time')
plt.xlabel('Date')
plt.ylabel('Sales')
plt.xticks(rotation=45)
plt.show()
```

### Statistical Testing
```python
from scipy import stats

# T-test between two departments
it_salaries = df[df['Department'] == 'IT']['Salary']
hr_salaries = df[df['Department'] == 'HR']['Salary']

t_stat, p_value = stats.ttest_ind(it_salaries, hr_salaries)
print(f"T-statistic: {t_stat:.4f}")
print(f"P-value: {p_value:.4f}")
```

## 📖 Additional Resources

- [Pandas Documentation](https://pandas.pydata.org/docs/)
- [Matplotlib Tutorial](https://matplotlib.org/stable/tutorials/index.html)
- [Seaborn Gallery](https://seaborn.pydata.org/examples/index.html)
- [DataCamp Data Analysis Course](https://www.datacamp.com/courses/intro-to-python-for-data-science)

## 🎯 Weekly Challenge

Create a comprehensive data analysis project:
1. Find a real dataset (Kaggle, UCI, etc.)
2. Perform complete EDA
3. Create at least 5 different visualizations
4. Generate insights and recommendations
5. Present findings in a Jupyter notebook

## ✅ Checklist

- [ ] Can create and manipulate DataFrames
- [ ] Know how to read different file formats
- [ ] Can perform data filtering and aggregation
- [ ] Can create basic plots with matplotlib
- [ ] Can create advanced visualizations with seaborn
- [ ] Know how to handle missing data
- [ ] Can perform statistical analysis
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 10 - Database Programming](./../week10/README.md)