# 🧰 Task Management Dashboard (Spring Boot REST API)

A lightweight, modular task management application built using **Spring Boot Web MVC** with RESTful architecture. Designed for simplicity, clarity, and future extensibility.

---

## 📄 Project Documentation (PDF)

For a detailed architectural breakdown, validation strategy, and API contract, refer to the [Documentation PDF]([docs/project-documentation.pdf](https://drive.google.com/file/d/1f0wYxHmcKRUKQnD2j3HPtSKGBO9nSCuH/view))


## 🚀 Features

- Create, retrieve, update, and delete tasks via REST endpoints
- Integrated validation using **Jakarta Bean Validation**
- Clean error handling with field-level feedback
- Index-optimized querying on status and due date
- Responsive frontend (if included) using Bootstrap 5 (via CDN)

---

## 🗄️ Database Design

### Entity: `Task`

```plaintext
+-----------------+
|      Task       |
+-----------------+
| id (PK)         |
| name            |
| title           |
| status          |
| dueDate         |
| createdBy       |
| createdAt       |
| updatedBy       |
| updatedAt       |
+-----------------+
