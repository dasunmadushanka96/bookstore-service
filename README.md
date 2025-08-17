📄 README.md

# 📚 Book Store Service

A Spring Boot REST API to manage books, authors, customers, and purchases.  
Implements CRUD operations, input validation, exception handling, DTO mapping, testing, logging, and API documentation.

---
## Features

- Manage **Authors**
- Manage **Books**
- Manage **Customers**
- Manage **Purchases**
- Request new books
- Inventory management
- Integration with H2 Database
- RESTful API endpoints for all entities
- Unit and Integration testing with JUnit & Mockito

---

## Entity Rules

### **Author**
1. An author can be created and can exist without any books related to him.  
2. An author can also be created with one or many books attached to him.  

### **Book**
3. A book can be created and can exist without being attached to any author.  
4. A book can be created with a new author.  
5. A book can later be attached to an existing author.  

### **Customer**
6. A customer can be created.  
7. A customer can purchase any books from the existing books.  
8. A customer can be listed with all the books he/she purchased.  

### **Requested Book**
9. A requested book can be created (for books that are not yet available in the inventory).  

---

## New Functionalities
- **Inventory Management** (track available stock of each book)  
- **Requested Book** (customers can request unavailable books)  

---

## Testing
- **Unit Tests**: Service, Repository, Controller using JUnit and Mockito.  
- **Integration Tests**: Full end-to-end testing with Spring Boot Test.  

---
## Validation`
- **Swagger UI** for API documentation
- **Logging** with SLF4J
- **Testing**:
  - Unit tests (repository, service, controller)
  - Integration tests (end-to-end flow)
- **Code Quality**:


---

## 🏗 Tech Stack

- **Java 21**  
- **Spring Boot 3.x** (Web, Data JPA, Validation)  
- **Maven** (Build & Dependency Management)  
- **H2** (Database)  
- **Swagger-UI / OpenAPI** (API documentation)  
- **Mockito & JUnit 5** (Testing)  
- **Lombok** (Boilerplate reduction)  

---

## 📂 Project DB Structure




---

## ⚙️ Getting Started

# Swagger URL
http://localhost:8080/swagger-ui/index.html

# H2 database URL
http://localhost:8080/h2-console

### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/dasunmadushanka96/bookstore-service.git
cd bookstore-service

2️⃣ Build & Run

Using Maven:

mvn clean install
mvn spring-boot:run

Or run the generated JAR:

java -jar target/bookstore-service-0.0.1-SNAPSHOT.jar


---

🌐 API Documentation

Once the application is running, open:

http://localhost:8080/swagger-ui/index.html


---

🛠 Example API Endpoints

Books

GET /books → Get all books

GET /books/{id} → Get a book by ID

POST /books → Create a new book

PUT /books/{id} → Update book details

DELETE /books/{id} → Delete a book


Authors

GET /authors

POST /authors

PUT /authors/{id}

DELETE /authors/{id}


Customers

GET /customers

POST /customers

PUT /customers/{id}

DELETE /customers/{id}


Purchased Books

GET /purchased-books

POST /purchased-books



---

🧪 Running Tests

Unit Tests

mvn test

Integration Tests

mvn verify

Code Coverage

After running tests:

target/site/jacoco/index.html


---

📌 Assumptions & Notes

Entities have been designed with meaningful relationships as per the requirements.

Input validation is applied where applicable.

Custom exceptions are returned with proper HTTP status codes.

H2 database is used by default; switch to MySQL via application.properties if needed.



---

🏆 Expected Outcomes

Service runs from source code or JAR.

Supports GET, POST, PUT, DELETE requests.

Data is stored and retrievable in the database.

API is documented via Swagger.

Tests verify both unit-level and full integration scenario


