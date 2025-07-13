# Level 4: Week 16 - Capstone Project

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Apply all skills learned throughout the curriculum
- Build a complete, production-ready application
- Work with real-world data and APIs
- Implement advanced features and optimizations
- Deploy and maintain a Python application

## 🏆 Capstone Project: Full-Stack Data Analytics Platform

### Project Overview
Build a comprehensive data analytics platform that demonstrates mastery of all Python programming concepts learned throughout the curriculum.

## 🎯 Project Requirements

### Core Features (Required)
1. **Data Ingestion System**
   - Connect to multiple data sources (APIs, databases, files)
   - Implement data validation and cleaning
   - Handle real-time and batch data processing

2. **Advanced Analytics Engine**
   - Implement machine learning models
   - Create statistical analysis tools
   - Build predictive analytics capabilities

3. **Web Application Interface**
   - RESTful API with FastAPI or Flask
   - Interactive dashboard with Streamlit or Dash
   - User authentication and authorization

4. **Database Management**
   - Design and implement database schema
   - Use both SQL and NoSQL databases
   - Implement data migration and backup systems

5. **System Architecture**
   - Microservices architecture
   - Message queuing for async processing
   - Caching and optimization strategies

## 🏗️ Project Architecture

### System Design
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend API   │    │   Analytics     │
│   (Streamlit)   │◄──►│   (FastAPI)     │◄──►│   Engine        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │                       │
                                ▼                       ▼
                       ┌─────────────────┐    ┌─────────────────┐
                       │   Database      │    │   Cache/Queue   │
                       │   (PostgreSQL)  │    │   (Redis/Rabbit)│
                       └─────────────────┘    └─────────────────┘
```

### Technology Stack
- **Backend**: FastAPI, SQLAlchemy, Celery
- **Frontend**: Streamlit, Plotly, Dash
- **Database**: PostgreSQL, Redis
- **ML/Analytics**: scikit-learn, pandas, numpy
- **Deployment**: Docker, Docker Compose
- **Monitoring**: Prometheus, Grafana

## 📋 Implementation Guide

### Phase 1: Project Setup and Foundation (Days 1-2)

#### 1.1 Project Structure
```python
# project_structure.py
"""
data_analytics_platform/
├── app/
│   ├── __init__.py
│   ├── main.py                 # FastAPI application
│   ├── config.py              # Configuration settings
│   ├── models/                # Database models
│   │   ├── __init__.py
│   │   ├── user.py
│   │   ├── dataset.py
│   │   └── analysis.py
│   ├── api/                   # API routes
│   │   ├── __init__.py
│   │   ├── auth.py
│   │   ├── datasets.py
│   │   └── analytics.py
│   ├── services/              # Business logic
│   │   ├── __init__.py
│   │   ├── data_service.py
│   │   ├── analytics_service.py
│   │   └── ml_service.py
│   ├── utils/                 # Utility functions
│   │   ├── __init__.py
│   │   ├── database.py
│   │   └── helpers.py
│   └── frontend/              # Streamlit dashboard
│       ├── main.py
│       ├── pages/
│       └── components/
├── tests/                     # Test suite
├── docker/                    # Docker configuration
├── requirements.txt
├── docker-compose.yml
└── README.md
"""
```

#### 1.2 Environment Setup
```python
# config.py
from pydantic_settings import BaseSettings
from typing import Optional

class Settings(BaseSettings):
    # Database
    DATABASE_URL: str = "postgresql://user:password@localhost/analytics_db"
    REDIS_URL: str = "redis://localhost:6379"
    
    # API
    API_V1_STR: str = "/api/v1"
    PROJECT_NAME: str = "Data Analytics Platform"
    
    # Security
    SECRET_KEY: str = "your-secret-key-here"
    ACCESS_TOKEN_EXPIRE_MINUTES: int = 30
    
    # External APIs
    OPENWEATHER_API_KEY: Optional[str] = None
    STOCK_API_KEY: Optional[str] = None
    
    class Config:
        env_file = ".env"

settings = Settings()
```

### Phase 2: Database and Models (Days 3-4)

#### 2.1 Database Models
```python
# models/user.py
from sqlalchemy import Column, Integer, String, DateTime, Boolean
from sqlalchemy.ext.declarative import declarative_base
from datetime import datetime

Base = declarative_base()

class User(Base):
    __tablename__ = "users"
    
    id = Column(Integer, primary_key=True, index=True)
    email = Column(String, unique=True, index=True)
    username = Column(String, unique=True, index=True)
    hashed_password = Column(String)
    is_active = Column(Boolean, default=True)
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

# models/dataset.py
class Dataset(Base):
    __tablename__ = "datasets"
    
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    description = Column(String)
    source_type = Column(String)  # 'api', 'file', 'database'
    source_config = Column(String)  # JSON configuration
    schema = Column(String)  # JSON schema
    created_by = Column(Integer, ForeignKey("users.id"))
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)
```

#### 2.2 Database Connection
```python
# utils/database.py
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from app.config import settings

engine = create_engine(settings.DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()
```

### Phase 3: API Development (Days 5-7)

#### 3.1 FastAPI Application
```python
# main.py
from fastapi import FastAPI, Depends, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session
from app.config import settings
from app.utils.database import get_db
from app.api import auth, datasets, analytics

app = FastAPI(
    title=settings.PROJECT_NAME,
    openapi_url=f"{settings.API_V1_STR}/openapi.json"
)

# CORS middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Include routers
app.include_router(auth.router, prefix=settings.API_V1_STR)
app.include_router(datasets.router, prefix=settings.API_V1_STR)
app.include_router(analytics.router, prefix=settings.API_V1_STR)

@app.get("/")
def read_root():
    return {"message": "Data Analytics Platform API"}

@app.get("/health")
def health_check():
    return {"status": "healthy"}
```

#### 3.2 API Routes
```python
# api/datasets.py
from fastapi import APIRouter, Depends, HTTPException, UploadFile, File
from sqlalchemy.orm import Session
from typing import List
from app.utils.database import get_db
from app.models.dataset import Dataset
from app.services.data_service import DataService
from app.schemas.dataset import DatasetCreate, DatasetResponse

router = APIRouter(prefix="/datasets", tags=["datasets"])

@router.post("/", response_model=DatasetResponse)
def create_dataset(
    dataset: DatasetCreate,
    db: Session = Depends(get_db)
):
    """Create a new dataset"""
    data_service = DataService(db)
    return data_service.create_dataset(dataset)

@router.get("/", response_model=List[DatasetResponse])
def get_datasets(
    skip: int = 0,
    limit: int = 100,
    db: Session = Depends(get_db)
):
    """Get all datasets"""
    data_service = DataService(db)
    return data_service.get_datasets(skip=skip, limit=limit)

@router.post("/upload")
def upload_file(
    file: UploadFile = File(...),
    db: Session = Depends(get_db)
):
    """Upload a data file"""
    data_service = DataService(db)
    return data_service.process_uploaded_file(file)
```

### Phase 4: Analytics Engine (Days 8-10)

#### 4.1 Data Processing Service
```python
# services/data_service.py
import pandas as pd
import numpy as np
from sqlalchemy.orm import Session
from app.models.dataset import Dataset
from app.schemas.dataset import DatasetCreate
import json

class DataService:
    def __init__(self, db: Session):
        self.db = db
    
    def create_dataset(self, dataset: DatasetCreate) -> Dataset:
        """Create a new dataset"""
        db_dataset = Dataset(**dataset.dict())
        self.db.add(db_dataset)
        self.db.commit()
        self.db.refresh(db_dataset)
        return db_dataset
    
    def process_uploaded_file(self, file) -> dict:
        """Process uploaded data file"""
        try:
            # Read file based on extension
            if file.filename.endswith('.csv'):
                df = pd.read_csv(file.file)
            elif file.filename.endswith('.xlsx'):
                df = pd.read_excel(file.file)
            else:
                raise ValueError("Unsupported file format")
            
            # Basic data analysis
            analysis = {
                "rows": len(df),
                "columns": len(df.columns),
                "missing_values": df.isnull().sum().to_dict(),
                "data_types": df.dtypes.astype(str).to_dict(),
                "summary_stats": df.describe().to_dict()
            }
            
            return {
                "filename": file.filename,
                "analysis": analysis,
                "preview": df.head().to_dict()
            }
        except Exception as e:
            raise HTTPException(status_code=400, detail=str(e))
```

#### 4.2 Analytics Service
```python
# services/analytics_service.py
import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA
from sklearn.linear_model import LinearRegression
import plotly.express as px
import plotly.graph_objects as go
from typing import Dict, Any

class AnalyticsService:
    def __init__(self):
        self.scaler = StandardScaler()
    
    def perform_clustering(self, data: pd.DataFrame, n_clusters: int = 3) -> Dict[str, Any]:
        """Perform K-means clustering"""
        # Prepare data
        numeric_data = data.select_dtypes(include=[np.number])
        scaled_data = self.scaler.fit_transform(numeric_data)
        
        # Perform clustering
        kmeans = KMeans(n_clusters=n_clusters, random_state=42)
        clusters = kmeans.fit_predict(scaled_data)
        
        # Add cluster labels to data
        data_with_clusters = data.copy()
        data_with_clusters['cluster'] = clusters
        
        # Create visualization
        if len(numeric_data.columns) >= 2:
            fig = px.scatter(
                data_with_clusters,
                x=numeric_data.columns[0],
                y=numeric_data.columns[1],
                color='cluster',
                title=f'K-means Clustering (k={n_clusters})'
            )
        else:
            fig = None
        
        return {
            "clusters": clusters.tolist(),
            "cluster_centers": kmeans.cluster_centers_.tolist(),
            "inertia": kmeans.inertia_,
            "visualization": fig.to_dict() if fig else None
        }
    
    def perform_regression(self, data: pd.DataFrame, target: str, features: list) -> Dict[str, Any]:
        """Perform linear regression"""
        X = data[features]
        y = data[target]
        
        # Fit model
        model = LinearRegression()
        model.fit(X, y)
        
        # Make predictions
        y_pred = model.predict(X)
        
        # Calculate metrics
        from sklearn.metrics import r2_score, mean_squared_error
        r2 = r2_score(y, y_pred)
        mse = mean_squared_error(y, y_pred)
        
        return {
            "coefficients": dict(zip(features, model.coef_)),
            "intercept": model.intercept_,
            "r2_score": r2,
            "mse": mse,
            "predictions": y_pred.tolist()
        }
    
    def perform_pca(self, data: pd.DataFrame, n_components: int = 2) -> Dict[str, Any]:
        """Perform Principal Component Analysis"""
        numeric_data = data.select_dtypes(include=[np.number])
        scaled_data = self.scaler.fit_transform(numeric_data)
        
        # Perform PCA
        pca = PCA(n_components=n_components)
        pca_result = pca.fit_transform(scaled_data)
        
        # Create visualization
        if n_components >= 2:
            fig = px.scatter(
                x=pca_result[:, 0],
                y=pca_result[:, 1],
                title=f'PCA - First {n_components} Components'
            )
        else:
            fig = None
        
        return {
            "pca_result": pca_result.tolist(),
            "explained_variance_ratio": pca.explained_variance_ratio_.tolist(),
            "components": pca.components_.tolist(),
            "visualization": fig.to_dict() if fig else None
        }
```

### Phase 5: Frontend Dashboard (Days 11-13)

#### 5.1 Streamlit Dashboard
```python
# frontend/main.py
import streamlit as st
import pandas as pd
import plotly.express as px
import plotly.graph_objects as go
import requests
from typing import Dict, Any

# Page configuration
st.set_page_config(
    page_title="Data Analytics Platform",
    page_icon="📊",
    layout="wide"
)

# Sidebar
st.sidebar.title("Navigation")
page = st.sidebar.selectbox(
    "Choose a page",
    ["Dashboard", "Data Upload", "Analytics", "Reports"]
)

# API base URL
API_BASE_URL = "http://localhost:8000/api/v1"

def main():
    if page == "Dashboard":
        show_dashboard()
    elif page == "Data Upload":
        show_data_upload()
    elif page == "Analytics":
        show_analytics()
    elif page == "Reports":
        show_reports()

def show_dashboard():
    st.title("📊 Data Analytics Platform Dashboard")
    
    # Overview metrics
    col1, col2, col3, col4 = st.columns(4)
    
    with col1:
        st.metric("Total Datasets", "15")
    with col2:
        st.metric("Active Users", "42")
    with col3:
        st.metric("Analyses Run", "156")
    with col4:
        st.metric("Storage Used", "2.3 GB")
    
    # Recent activity
    st.subheader("Recent Activity")
    
    # Sample data for demonstration
    activity_data = pd.DataFrame({
        'Time': pd.date_range('2024-01-01', periods=10, freq='H'),
        'User': ['Alice', 'Bob', 'Charlie', 'Diana', 'Eve'] * 2,
        'Action': ['Uploaded dataset', 'Ran analysis', 'Created report', 'Exported data', 'Shared dashboard'] * 2
    })
    
    st.dataframe(activity_data)

def show_data_upload():
    st.title("📁 Data Upload")
    
    uploaded_file = st.file_uploader(
        "Choose a file",
        type=['csv', 'xlsx', 'json']
    )
    
    if uploaded_file is not None:
        st.success(f"File uploaded: {uploaded_file.name}")
        
        # Process file
        try:
            if uploaded_file.name.endswith('.csv'):
                df = pd.read_csv(uploaded_file)
            elif uploaded_file.name.endswith('.xlsx'):
                df = pd.read_excel(uploaded_file)
            elif uploaded_file.name.endswith('.json'):
                df = pd.read_json(uploaded_file)
            
            st.subheader("Data Preview")
            st.dataframe(df.head())
            
            st.subheader("Data Summary")
            st.write(f"Rows: {len(df)}")
            st.write(f"Columns: {len(df.columns)}")
            st.write(f"Missing values: {df.isnull().sum().sum()}")
            
        except Exception as e:
            st.error(f"Error processing file: {str(e)}")

def show_analytics():
    st.title("🔬 Analytics")
    
    # Sample data for demonstration
    np.random.seed(42)
    data = pd.DataFrame({
        'x': np.random.normal(0, 1, 100),
        'y': np.random.normal(0, 1, 100),
        'category': np.random.choice(['A', 'B', 'C'], 100)
    })
    
    # Clustering analysis
    st.subheader("Clustering Analysis")
    
    n_clusters = st.slider("Number of clusters", 2, 5, 3)
    
    if st.button("Run Clustering"):
        from sklearn.cluster import KMeans
        
        kmeans = KMeans(n_clusters=n_clusters, random_state=42)
        data['cluster'] = kmeans.fit_predict(data[['x', 'y']])
        
        fig = px.scatter(
            data,
            x='x',
            y='y',
            color='cluster',
            title=f'K-means Clustering (k={n_clusters})'
        )
        st.plotly_chart(fig)
    
    # Regression analysis
    st.subheader("Regression Analysis")
    
    if st.button("Run Regression"):
        from sklearn.linear_model import LinearRegression
        
        model = LinearRegression()
        model.fit(data[['x']], data['y'])
        data['predicted'] = model.predict(data[['x']])
        
        fig = px.scatter(
            data,
            x='x',
            y='y',
            title='Linear Regression'
        )
        fig.add_scatter(
            x=data['x'],
            y=data['predicted'],
            mode='lines',
            name='Prediction'
        )
        st.plotly_chart(fig)

def show_reports():
    st.title("📋 Reports")
    
    # Generate sample report
    st.subheader("Monthly Analytics Report")
    
    # Sample metrics
    metrics = {
        "Total Datasets": 15,
        "Data Quality Score": 94.2,
        "Processing Time": "2.3s avg",
        "User Satisfaction": 4.8
    }
    
    for metric, value in metrics.items():
        st.metric(metric, value)
    
    # Sample chart
    chart_data = pd.DataFrame({
        'Month': ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
        'Datasets': [5, 8, 12, 15, 18],
        'Analyses': [20, 35, 45, 60, 75]
    })
    
    fig = px.line(
        chart_data,
        x='Month',
        y=['Datasets', 'Analyses'],
        title='Growth Over Time'
    )
    st.plotly_chart(fig)

if __name__ == "__main__":
    main()
```

### Phase 6: Deployment and Testing (Days 14-16)

#### 6.1 Docker Configuration
```yaml
# docker-compose.yml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8000:8000"
    environment:
      - DATABASE_URL=postgresql://user:password@db:5432/analytics_db
      - REDIS_URL=redis://redis:6379
    depends_on:
      - db
      - redis
    volumes:
      - ./app:/app/app
      - ./uploads:/app/uploads

  frontend:
    build: ./frontend
    ports:
      - "8501:8501"
    depends_on:
      - app
    volumes:
      - ./frontend:/app

  db:
    image: postgres:13
    environment:
      - POSTGRES_USER=user
      - POSTGRES_PASSWORD=password
      - POSTGRES_DB=analytics_db
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  redis:
    image: redis:6
    ports:
      - "6379:6379"

  celery:
    build: .
    command: celery -A app.celery_app worker --loglevel=info
    environment:
      - DATABASE_URL=postgresql://user:password@db:5432/analytics_db
      - REDIS_URL=redis://redis:6379
    depends_on:
      - db
      - redis

volumes:
  postgres_data:
```

#### 6.2 Testing Suite
```python
# tests/test_api.py
import pytest
from fastapi.testclient import TestClient
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from app.main import app
from app.utils.database import get_db
from app.models import Base

# Test database
SQLALCHEMY_DATABASE_URL = "sqlite:///./test.db"
engine = create_engine(SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False})
TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

def override_get_db():
    try:
        db = TestingSessionLocal()
        yield db
    finally:
        db.close()

app.dependency_overrides[get_db] = override_get_db

client = TestClient(app)

def test_read_main():
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"message": "Data Analytics Platform API"}

def test_health_check():
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "healthy"}

def test_create_dataset():
    dataset_data = {
        "name": "Test Dataset",
        "description": "Test description",
        "source_type": "file",
        "source_config": "{}"
    }
    response = client.post("/api/v1/datasets/", json=dataset_data)
    assert response.status_code == 200
    data = response.json()
    assert data["name"] == "Test Dataset"
```

## 🎯 Project Deliverables

### Required Deliverables
1. **Complete Application**
   - Fully functional web application
   - API documentation
   - User interface

2. **Documentation**
   - Technical documentation
   - User manual
   - API reference

3. **Testing**
   - Unit tests (80%+ coverage)
   - Integration tests
   - Performance tests

4. **Deployment**
   - Docker containerization
   - CI/CD pipeline
   - Production deployment guide

### Optional Enhancements
1. **Advanced Features**
   - Real-time data streaming
   - Advanced ML models
   - Custom visualizations

2. **Scalability**
   - Load balancing
   - Database sharding
   - Caching strategies

3. **Security**
   - OAuth2 authentication
   - Role-based access control
   - Data encryption

## 📊 Evaluation Criteria

### Technical Excellence (40%)
- Code quality and organization
- Algorithm efficiency
- Error handling and edge cases
- Performance optimization

### Functionality (30%)
- Feature completeness
- User experience
- Data processing capabilities
- Analytics accuracy

### Documentation (15%)
- Code documentation
- API documentation
- User guides
- Deployment instructions

### Testing (15%)
- Test coverage
- Test quality
- Performance testing
- Security testing

## 🎓 Final Assessment

### Portfolio Review
- Complete all previous weeks' projects
- Demonstrate mastery of all concepts
- Show progression in skill level

### Presentation
- Present your capstone project
- Explain technical decisions
- Demonstrate live functionality
- Answer technical questions

### Code Review
- Peer code review
- Instructor evaluation
- Industry expert feedback

## 🏆 Certification

Upon successful completion of the capstone project, you will receive:
- **Python Programming Expert Certificate**
- **Portfolio of completed projects**
- **Industry-ready skills assessment**
- **Professional development recommendations**

---

**Congratulations! You've completed the comprehensive Python programming curriculum! 🎉**

You are now a Python programming expert with the skills to:
- Build complex applications from scratch
- Work with real-world data and APIs
- Implement advanced algorithms and data structures
- Deploy and maintain production systems
- Contribute to open-source projects
- Pursue advanced specializations

**Next Steps:**
- Continue learning and practicing
- Contribute to open-source projects
- Build your professional network
- Consider advanced specializations (ML, DevOps, etc.)
- Start your programming career!