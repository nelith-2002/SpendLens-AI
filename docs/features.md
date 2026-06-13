# SpendLens AI - Feature Plan

## Project Title

SpendLens AI: Mobile Expense Tracking using Receipt OCR, Text Classification, and Spending Forecasting

## Project Overview

SpendLens AI is an AI-based mobile expense tracking application that allows users to scan receipts, extract receipt text using OCR, automatically identify key receipt details, classify expenses into categories using machine learning, and predict monthly spending based on expense history.

## Main Technologies

- Flutter
- Spring Boot
- PostgreSQL
- Python FastAPI
- Google ML Kit OCR
- scikit-learn

## Core Features

### User Management
- User registration
- User login
- JWT-based authentication

### Expense Management
- Add expense manually
- View expense history
- Edit expense
- Delete expense
- Filter expenses by date/category

### Receipt OCR
- Capture or upload receipt image
- Extract receipt text using Google ML Kit
- Show extracted text to user

### Receipt Processing
- Extract shop name
- Extract receipt date
- Extract total amount
- Allow user to review and edit extracted details

### Machine Learning
- Predict expense category using TF-IDF + Logistic Regression
- Predict monthly spending using Linear Regression

### Analytics
- Monthly total spending
- Category-wise spending summary
- Recent expenses
- Monthly spending forecast