# SpendLens AI - API Plan

## Authentication APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login user |
| GET | /api/auth/me | Get logged-in user details |

## Expense APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/expenses | Add new expense |
| GET | /api/expenses | Get all expenses of logged-in user |
| GET | /api/expenses/{id} | Get one expense |
| PUT | /api/expenses/{id} | Update expense |
| DELETE | /api/expenses/{id} | Delete expense |

## Receipt APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/receipts/process | Process OCR text and predict category |
| POST | /api/receipts/save | Save scanned receipt as expense |

## Analytics APIs

| Method | Endpoint | Purpose |
|---|---|---|
| GET | /api/analytics/monthly-summary | Get current month summary |
| GET | /api/analytics/category-summary | Get category-wise spending |
| GET | /api/analytics/recent-expenses | Get recent expenses |
| GET | /api/analytics/monthly-prediction | Get monthly spending prediction |

## ML Service APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /predict-category | Predict expense category |
| POST | /predict-monthly-spending | Predict monthly spending |