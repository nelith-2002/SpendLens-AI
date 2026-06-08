# SpendLens AI - System Architecture

## Project Name

SpendLens AI

## Full Title

SpendLens AI: Mobile Expense Tracking using Receipt OCR, Text Classification, and Spending Forecasting

## High-Level Architecture

The system contains four main parts:

1. Flutter Mobile App
2. Spring Boot Backend API
3. PostgreSQL Database
4. Python FastAPI Machine Learning Service

## Main Flow

1. The user scans a receipt using the Flutter mobile app.
2. Google ML Kit extracts text from the receipt image.
3. The extracted OCR text is sent to the Spring Boot backend.
4. The backend cleans the receipt text and extracts important fields such as shop name, date, and total amount.
5. The backend sends the cleaned receipt text to the Python FastAPI ML service.
6. The ML service predicts the expense category using a TF-IDF and Logistic Regression model.
7. The backend saves the final expense record in PostgreSQL.
8. The Flutter app displays expense history, analytics, and monthly spending prediction.

## Architecture Diagram

Flutter Mobile App
    |
    | REST API Requests
    v
Spring Boot Backend API
    |
    | Save / Fetch Data
    v
PostgreSQL Database

Spring Boot Backend API
    |
    | Prediction Requests
    v
Python FastAPI ML Service
    |
    | ML Prediction Response
    v
Spring Boot Backend API

## ML Components

### 1. OCR

Google ML Kit is used in the Flutter app to extract text from receipt images.

### 2. Receipt Text Cleaning and Field Extraction

The Spring Boot backend processes OCR text to identify:

- Shop name
- Receipt date
- Total amount

### 3. Expense Category Classification

The ML service uses:

- TF-IDF Vectorization
- Logistic Regression

Input:

Receipt text

Output:

Predicted expense category

### 4. Monthly Spending Prediction

The ML service uses Linear Regression to predict monthly spending based on user expense history.

## Main Technologies

### Mobile App

- Flutter
- Dart
- Google ML Kit

### Backend

- Java
- Spring Boot
- Spring Security
- JWT Authentication

### Database

- PostgreSQL

### ML Service

- Python
- FastAPI
- scikit-learn
- pandas
- joblib