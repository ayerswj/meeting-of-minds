# Level 4: Week 14 - Machine Learning & AI

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Understand machine learning fundamentals and types
- Implement supervised and unsupervised learning algorithms
- Work with popular ML libraries (scikit-learn, TensorFlow)
- Build and evaluate ML models
- Apply ML to real-world problems
- Understand AI ethics and best practices

## 📚 Theory

### What is Machine Learning?
Machine Learning is a subset of AI that enables computers to learn and make predictions from data without being explicitly programmed.

### Types of Machine Learning
- **Supervised Learning**: Learning from labeled data
- **Unsupervised Learning**: Finding patterns in unlabeled data
- **Reinforcement Learning**: Learning through interaction and rewards

## 🔬 Supervised Learning

### Linear Regression
```python
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score
import matplotlib.pyplot as plt

# Generate sample data
np.random.seed(42)
X = np.random.rand(100, 1) * 10
y = 2 * X + 1 + np.random.randn(100, 1) * 0.5

# Split data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train model
model = LinearRegression()
model.fit(X_train, y_train)

# Make predictions
y_pred = model.predict(X_test)

# Evaluate
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)
print(f"MSE: {mse:.4f}")
print(f"R²: {r2:.4f}")
print(f"Slope: {model.coef_[0][0]:.2f}")
print(f"Intercept: {model.intercept_[0]:.2f}")
```

### Classification with Logistic Regression
```python
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.datasets import make_classification

# Generate classification data
X, y = make_classification(n_samples=1000, n_features=2, n_redundant=0, 
                          n_informative=2, random_state=42, n_clusters_per_class=1)

# Split data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train model
clf = LogisticRegression(random_state=42)
clf.fit(X_train, y_train)

# Predictions
y_pred = clf.predict(X_test)

# Evaluate
print("Classification Report:")
print(classification_report(y_test, y_pred))
print("Confusion Matrix:")
print(confusion_matrix(y_test, y_pred))
```

## 🌳 Decision Trees and Random Forests

### Decision Tree Classifier
```python
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.ensemble import RandomForestClassifier

# Decision Tree
dt = DecisionTreeClassifier(max_depth=5, random_state=42)
dt.fit(X_train, y_train)
dt_score = dt.score(X_test, y_test)
print(f"Decision Tree Accuracy: {dt_score:.4f}")

# Random Forest
rf = RandomForestClassifier(n_estimators=100, max_depth=5, random_state=42)
rf.fit(X_train, y_train)
rf_score = rf.score(X_test, y_test)
print(f"Random Forest Accuracy: {rf_score:.4f}")

# Feature importance
feature_importance = rf.feature_importances_
print(f"Feature importance: {feature_importance}")
```

## 🎯 Unsupervised Learning

### K-Means Clustering
```python
from sklearn.cluster import KMeans
from sklearn.datasets import make_blobs

# Generate clustering data
X, _ = make_blobs(n_samples=300, centers=4, cluster_std=0.60, random_state=42)

# K-Means clustering
kmeans = KMeans(n_clusters=4, random_state=42)
kmeans.fit(X)
labels = kmeans.labels_

# Visualize clusters
plt.scatter(X[:, 0], X[:, 1], c=labels, cmap='viridis')
plt.scatter(kmeans.cluster_centers_[:, 0], kmeans.cluster_centers_[:, 1], 
           s=200, c='red', marker='x')
plt.title('K-Means Clustering')
plt.show()
```

### Principal Component Analysis (PCA)
```python
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler

# Standardize data
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Apply PCA
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X_scaled)

print(f"Explained variance ratio: {pca.explained_variance_ratio_}")
print(f"Total explained variance: {sum(pca.explained_variance_ratio_):.4f}")
```

## 🧠 Neural Networks with TensorFlow

### Simple Neural Network
```python
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

# Create model
model = keras.Sequential([
    layers.Dense(64, activation='relu', input_shape=(2,)),
    layers.Dropout(0.2),
    layers.Dense(32, activation='relu'),
    layers.Dropout(0.2),
    layers.Dense(1, activation='sigmoid')
])

# Compile model
model.compile(optimizer='adam',
              loss='binary_crossentropy',
              metrics=['accuracy'])

# Train model
history = model.fit(X_train, y_train, 
                   validation_data=(X_test, y_test),
                   epochs=50, batch_size=32, verbose=0)

# Evaluate
test_loss, test_accuracy = model.evaluate(X_test, y_test, verbose=0)
print(f"Test accuracy: {test_accuracy:.4f}")
```

## 📊 Model Evaluation and Validation

### Cross-Validation
```python
from sklearn.model_selection import cross_val_score, KFold

# K-Fold Cross Validation
kfold = KFold(n_splits=5, shuffle=True, random_state=42)
scores = cross_val_score(rf, X, y, cv=kfold, scoring='accuracy')

print(f"Cross-validation scores: {scores}")
print(f"Mean CV score: {scores.mean():.4f} (+/- {scores.std() * 2:.4f})")
```

### Hyperparameter Tuning
```python
from sklearn.model_selection import GridSearchCV

# Define parameter grid
param_grid = {
    'n_estimators': [50, 100, 200],
    'max_depth': [3, 5, 7, None],
    'min_samples_split': [2, 5, 10]
}

# Grid search
grid_search = GridSearchCV(RandomForestClassifier(random_state=42), 
                          param_grid, cv=5, scoring='accuracy')
grid_search.fit(X_train, y_train)

print(f"Best parameters: {grid_search.best_params_}")
print(f"Best score: {grid_search.best_score_:.4f}")
```

## 🚀 Mini-Project: Customer Churn Prediction

```python
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, confusion_matrix
import matplotlib.pyplot as plt
import seaborn as sns

class ChurnPredictor:
    def __init__(self):
        self.model = None
        self.scaler = StandardScaler()
        self.label_encoders = {}
        
    def load_data(self):
        """Load and prepare customer data"""
        # Generate sample customer data
        np.random.seed(42)
        n_customers = 1000
        
        data = {
            'customer_id': range(1, n_customers + 1),
            'tenure': np.random.randint(1, 72, n_customers),
            'monthly_charges': np.random.uniform(30, 150, n_customers),
            'total_charges': np.random.uniform(100, 5000, n_customers),
            'contract_type': np.random.choice(['Month-to-month', 'One year', 'Two year'], n_customers),
            'payment_method': np.random.choice(['Electronic check', 'Mailed check', 'Bank transfer', 'Credit card'], n_customers),
            'internet_service': np.random.choice(['DSL', 'Fiber optic', 'No'], n_customers),
            'online_security': np.random.choice(['Yes', 'No', 'No internet service'], n_customers),
            'tech_support': np.random.choice(['Yes', 'No', 'No internet service'], n_customers),
            'paperless_billing': np.random.choice(['Yes', 'No'], n_customers),
            'streaming_tv': np.random.choice(['Yes', 'No', 'No internet service'], n_customers)
        }
        
        df = pd.DataFrame(data)
        
        # Create churn target (simplified logic)
        churn_prob = (
            (df['tenure'] < 12) * 0.3 +
            (df['monthly_charges'] > 80) * 0.2 +
            (df['contract_type'] == 'Month-to-month') * 0.3 +
            (df['payment_method'] == 'Electronic check') * 0.1
        )
        df['churn'] = np.random.binomial(1, churn_prob)
        
        return df
    
    def preprocess_data(self, df):
        """Preprocess the data for modeling"""
        # Select features
        feature_columns = ['tenure', 'monthly_charges', 'total_charges', 
                          'contract_type', 'payment_method', 'internet_service',
                          'online_security', 'tech_support', 'paperless_billing', 'streaming_tv']
        
        X = df[feature_columns].copy()
        y = df['churn']
        
        # Encode categorical variables
        categorical_columns = ['contract_type', 'payment_method', 'internet_service',
                              'online_security', 'tech_support', 'paperless_billing', 'streaming_tv']
        
        for col in categorical_columns:
            le = LabelEncoder()
            X[col] = le.fit_transform(X[col])
            self.label_encoders[col] = le
        
        # Scale numerical features
        numerical_columns = ['tenure', 'monthly_charges', 'total_charges']
        X[numerical_columns] = self.scaler.fit_transform(X[numerical_columns])
        
        return X, y
    
    def train_model(self, X, y):
        """Train the churn prediction model"""
        # Split data
        X_train, X_test, y_train, y_test = train_test_split(
            X, y, test_size=0.2, random_state=42, stratify=y
        )
        
        # Train Random Forest
        self.model = RandomForestClassifier(
            n_estimators=100,
            max_depth=10,
            random_state=42,
            class_weight='balanced'
        )
        self.model.fit(X_train, y_train)
        
        # Evaluate
        y_pred = self.model.predict(X_test)
        
        print("Model Performance:")
        print(classification_report(y_test, y_pred))
        
        # Feature importance
        feature_importance = pd.DataFrame({
            'feature': X.columns,
            'importance': self.model.feature_importances_
        }).sort_values('importance', ascending=False)
        
        print("\nFeature Importance:")
        print(feature_importance)
        
        return X_test, y_test, y_pred
    
    def predict_churn(self, customer_data):
        """Predict churn for new customer data"""
        if self.model is None:
            raise ValueError("Model not trained yet")
        
        # Preprocess customer data
        customer_df = pd.DataFrame([customer_data])
        
        # Encode categorical variables
        for col, le in self.label_encoders.items():
            if col in customer_df.columns:
                customer_df[col] = le.transform(customer_df[col])
        
        # Scale numerical features
        numerical_columns = ['tenure', 'monthly_charges', 'total_charges']
        customer_df[numerical_columns] = self.scaler.transform(customer_df[numerical_columns])
        
        # Make prediction
        churn_prob = self.model.predict_proba(customer_df)[0][1]
        churn_prediction = self.model.predict(customer_df)[0]
        
        return {
            'churn_probability': churn_prob,
            'churn_prediction': bool(churn_prediction),
            'risk_level': 'High' if churn_prob > 0.7 else 'Medium' if churn_prob > 0.3 else 'Low'
        }
    
    def generate_insights(self, df):
        """Generate business insights from the data"""
        insights = {}
        
        # Churn rate by contract type
        churn_by_contract = df.groupby('contract_type')['churn'].mean()
        insights['churn_by_contract'] = churn_by_contract.to_dict()
        
        # Churn rate by tenure
        df['tenure_group'] = pd.cut(df['tenure'], bins=[0, 12, 24, 48, 100], 
                                   labels=['0-12', '13-24', '25-48', '49+'])
        churn_by_tenure = df.groupby('tenure_group')['churn'].mean()
        insights['churn_by_tenure'] = churn_by_tenure.to_dict()
        
        # Average monthly charges by churn status
        avg_charges = df.groupby('churn')['monthly_charges'].mean()
        insights['avg_charges_by_churn'] = avg_charges.to_dict()
        
        return insights

# Usage
def main():
    predictor = ChurnPredictor()
    
    # Load and prepare data
    print("Loading customer data...")
    df = predictor.load_data()
    
    print(f"Dataset shape: {df.shape}")
    print(f"Churn rate: {df['churn'].mean():.2%}")
    
    # Preprocess data
    print("\nPreprocessing data...")
    X, y = predictor.preprocess_data(df)
    
    # Train model
    print("\nTraining model...")
    X_test, y_test, y_pred = predictor.train_model(X, y)
    
    # Generate insights
    print("\nGenerating insights...")
    insights = predictor.generate_insights(df)
    
    print("\nBusiness Insights:")
    print("Churn rate by contract type:")
    for contract, rate in insights['churn_by_contract'].items():
        print(f"  {contract}: {rate:.2%}")
    
    print("\nChurn rate by tenure:")
    for tenure, rate in insights['churn_by_tenure'].items():
        print(f"  {tenure} months: {rate:.2%}")
    
    # Example prediction
    print("\nExample prediction:")
    sample_customer = {
        'tenure': 6,
        'monthly_charges': 85.0,
        'total_charges': 510.0,
        'contract_type': 'Month-to-month',
        'payment_method': 'Electronic check',
        'internet_service': 'Fiber optic',
        'online_security': 'No',
        'tech_support': 'No',
        'paperless_billing': 'Yes',
        'streaming_tv': 'Yes'
    }
    
    prediction = predictor.predict_churn(sample_customer)
    print(f"Churn probability: {prediction['churn_probability']:.2%}")
    print(f"Churn prediction: {prediction['churn_prediction']}")
    print(f"Risk level: {prediction['risk_level']}")

if __name__ == "__main__":
    main()
```

## 🎯 Practice Exercises

### Exercise 1: House Price Prediction
Build a model to predict house prices using features like square footage, bedrooms, location, etc.

### Exercise 2: Image Classification
Use a pre-trained model to classify images into different categories.

### Exercise 3: Sentiment Analysis
Create a model to analyze sentiment in text data (positive/negative/neutral).

## 🎯 Advanced Concepts

### Model Deployment
```python
import joblib
import pickle

# Save model
joblib.dump(model, 'churn_model.pkl')

# Load model
loaded_model = joblib.load('churn_model.pkl')
```

### Feature Engineering
```python
# Create interaction features
df['tenure_charges_ratio'] = df['tenure'] / df['monthly_charges']

# Create polynomial features
from sklearn.preprocessing import PolynomialFeatures
poly = PolynomialFeatures(degree=2, include_bias=False)
X_poly = poly.fit_transform(X)
```

## 📖 Additional Resources

- [scikit-learn Documentation](https://scikit-learn.org/)
- [TensorFlow Tutorials](https://www.tensorflow.org/tutorials)
- [Machine Learning Mastery](https://machinelearningmastery.com/)
- [Kaggle Courses](https://www.kaggle.com/learn)

## 🎯 Weekly Challenge

Create a **Multi-Model ML Pipeline** that:
1. Implements multiple algorithms (Random Forest, SVM, Neural Network)
2. Uses ensemble methods to combine predictions
3. Implements automated feature selection
4. Provides model interpretability tools
5. Includes automated hyperparameter tuning
6. Deploys the best model as a web service

## ✅ Checklist

- [ ] Can implement supervised learning algorithms
- [ ] Can work with unsupervised learning techniques
- [ ] Can build and evaluate ML models
- [ ] Can use TensorFlow for neural networks
- [ ] Can perform cross-validation and hyperparameter tuning
- [ ] Can handle feature engineering and preprocessing
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 15 - DevOps & Deployment](./../week15/README.md)