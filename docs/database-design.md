# SpendLens AI - Database Design

## 1. users

Stores registered user details.

| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| full_name | VARCHAR(100) | User's full name |
| email | VARCHAR(150) | Unique email |
| password_hash | VARCHAR(255) | Hashed password |
| created_at | TIMESTAMP | Account created time |
| updated_at | TIMESTAMP | Last updated time |

## 2. expense_categories

Stores available expense categories.

| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| name | VARCHAR(100) | Category name |
| description | VARCHAR(255) | Category description |
| created_at | TIMESTAMP | Created time |

Initial categories:

- Groceries
- Food & Dining
- Transport
- Bills
- Medical
- Shopping
- Education
- Other

## 3. expenses

Stores user expenses.

| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| user_id | BIGINT | Related user |
| category_id | BIGINT | Related category |
| shop_name | VARCHAR(150) | Shop or merchant name |
| amount | DECIMAL(10,2) | Expense amount |
| expense_date | DATE | Date of expense |
| source_type | VARCHAR(30) | MANUAL or RECEIPT_SCAN |
| note | TEXT | Optional note |
| created_at | TIMESTAMP | Created time |
| updated_at | TIMESTAMP | Updated time |

## 4. receipts

Stores receipt OCR and extracted details.

| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| user_id | BIGINT | Related user |
| expense_id | BIGINT | Related expense |
| ocr_text | TEXT | Full OCR extracted text |
| extracted_shop_name | VARCHAR(150) | Extracted shop name |
| extracted_date | DATE | Extracted receipt date |
| extracted_total | DECIMAL(10,2) | Extracted total amount |
| created_at | TIMESTAMP | Created time |

## 5. prediction_logs

Stores ML prediction logs.

| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| user_id | BIGINT | Related user |
| expense_id | BIGINT | Related expense |
| prediction_type | VARCHAR(100) | CATEGORY_CLASSIFICATION or MONTHLY_SPENDING_FORECAST |
| input_data | TEXT | Input given to model |
| predicted_value | VARCHAR(255) | Prediction result |
| created_at | TIMESTAMP | Created time |