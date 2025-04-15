# Cashtrack - Back-End Architecture and Component Mapping

## 🎯 Objective

As a back-end developer,  
We want to map and document all components of the back-end  
So that we ensure a **modular**, **secure**, and **scalable** architecture, facilitating maintenance and integration with the front-end.

## 🧩 Component Identification

### ✅ Core Modules
- **Authentication Module**
  - Handles user login, registration, and JWT token management.
- **User Module**
  - Manages user profile and preferences.
- **Transactions Module**
  - Responsible for managing expenses and income entries.
- **Balance Module**
  - Calculates and returns user financial balance and summaries.

### 🗄️ Database Entities & Relationships
- **User**
  - Fields: `id`, `name`, `email`, `password`, `created_at`
  - Relationships: One-to-many with `Income` and `Expense`
- **Expense**
  - Fields: `id`, `amount`, `category`, `description`, `date`, `user_id`
- **Income**
  - Fields: `id`, `amount`, `source`, `description`, `date`, `user_id`

### 🌐 Exposed APIs (REST Endpoints)
| Endpoint                  | Method | Description                          |
|--------------------------|--------|--------------------------------------|
| `/api/auth/login`        | POST   | Authenticate user and return token   |
| `/api/auth/register`     | POST   | Create new user                      |
| `/api/users/me`          | GET    | Return authenticated user data       |
| `/api/expenses`          | GET    | List all user expenses               |
| `/api/expenses`          | POST   | Add a new expense                    |
| `/api/incomes`           | GET    | List all user incomes                |
| `/api/incomes`           | POST   | Add a new income                     |
| `/api/balance`           | GET    | Return calculated user balance       |

## 🏗️ Architecture Definition

### 🧱 Architecture Pattern
- **Clean Architecture**
  - Domain-centric
  - Decouples business logic from infrastructure and framework dependencies
  - Enhances testability and maintainability


### 🛡️ Authentication and Authorization
- Strategy: **JWT (JSON Web Tokens)**
- Token includes user ID and expiration time
- Secured with HTTP-only cookies or Bearer tokens in headers

## 📚 Documentation

### 🔍 Module Description
- **Authentication**: Handles login, token creation and validation
- **User**: Provides access to personal user data and preferences
- **Transaction Modules (Expense/Income)**: CRUD operations for financial records
- **Balance**: Aggregates transaction data for analytics and visualization

### 🔗 Component Communication
- Controllers receive requests and delegate to services
- Services access domain logic and repositories
- DTOs (Data Transfer Objects) ensure clean input/output handling

### 📑 Endpoint Specs
- JSON format for all requests and responses
- Status codes: `200 OK`, `201 Created`, `400 Bad Request`, `401 Unauthorized`, etc.
- Input validation and exception handling implemented

## ✅ Next Steps
- Define OpenAPI (Swagger) documentation
- Implement unit and integration tests for each module
- Deploy staging environment for front-end integration

> 📌 This document will evolve as the project progresses and new features are integrated.
