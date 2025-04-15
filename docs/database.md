# 🗄️ Database Structure Documentation — Cashtrack Project

## 🎯 Objective

Define, model, and implement the relational database structure of the **Cashtrack** project, ensuring efficient, secure, and organized storage of users, financial transactions, and user-specific settings.

## 📌 Data Model

### 🧩 Core Entities

1. **Users** (`users`)
2. **Transactions** (`transactions`)
3. **Categories** (`categories`)
4. **Settings** (`settings`)

## 🗃️ Table Structures

### 🔐 `users`

| Field         | Type        | Description                                |
|---------------|-------------|---------------------------------------------|
| id            | UUID (PK)   | Unique identifier                           |
| name          | String      | User's name                                 |
| email         | String      | Unique email (indexed)                      |
| password_hash | String      | Encrypted password                          |
| created_at    | Timestamp   | Creation date                               |
| updated_at    | Timestamp   | Last update date                            |

### 💸 `transactions`

| Field         | Type         | Description                                |
|---------------|--------------|---------------------------------------------|
| id            | UUID (PK)    | Transaction identifier                      |
| user_id       | UUID (FK)    | References the user                         |
| category_id   | UUID (FK)    | References the transaction's category       |
| type          | ENUM         | Type: `income` or `expense`                 |
| amount        | Decimal      | Transaction amount                          |
| description   | String       | Optional description                        |
| date          | Date         | Transaction date                            |
| created_at    | Timestamp    | Creation date                               |

### 🏷️ `categories`

| Field      | Type        | Description                                  |
|------------|-------------|-----------------------------------------------|
| id         | UUID (PK)   | Unique category identifier                    |
| name       | String      | Category name                                 |
| icon       | String      | Associated UI icon name                       |
| user_id    | UUID (FK)   | Owner of the category (nullable/global)       |

### ⚙️ `settings`

| Field         | Type        | Description                              |
|---------------|-------------|-------------------------------------------|
| id            | UUID (PK)   | Identifier                               |
| user_id       | UUID (FK)   | References the user                      |
| theme         | String      | Selected theme (light/dark)              |
| language      | String      | Language (e.g., "pt-BR", "en-US")         |
| notifications | Boolean     | Notifications on/off                     |

## 🔁 Relationships

- `users` 1 — N `transactions`
- `users` 1 — N `settings`
- `users` 1 — N `categories`
- `categories` 1 — N `transactions`

## ⚙️ Indexing and Optimizations

- Index on `users.email` for fast login
- Index on `transactions.user_id` for dashboard queries
- Compound index (`user_id`, `type`, `date`) for optimized filtering

## 🔒 Data Integrity and Security Rules

- **Foreign keys** with `ON DELETE CASCADE`
- **Validations** via ORM (non-null, types, enum)
- **Protection against SQL Injection** using Sequelize ORM
- **Password encryption** using bcrypt