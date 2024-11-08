# Simple Product REST API

## Table of Contents
- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Endpoints](#endpoints)
  - [Create Product](#1-create-product)
  - [Get Product by ID](#2-get-product-by-id)
  - [Get All Products (with Pagination and Sorting)](#3-get-all-products-with-pagination-and-sorting)
  - [Update Product](#4-update-product)
  - [Delete Product](#5-delete-product)
- [Exception Handling](#exception-handling)
- [Notes](#notes)
- [Contributing](#contributing)

## Project Description

This project implements a simple Spring Boot REST API to manage a list of products. Each product has an id, name, description, price, and stock quantity. The application supports all basic CRUD operations, along with validation, pagination, and sorting.

### Key Features:
- **CRUD Operations:** Create, Read, Update, Delete products.
- **Validation:** Ensures no product can be created with empty fields or negative values.
- **Pagination and Sorting:** Supports retrieving all products with pagination and sorting.
- **In-memory Database:** Uses an in-memory H2 database for data storage.
- **Exception Handling:** Custom exception handling for validation and internal errors.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Maven**
- **H2 Database**
- **Spring Data JPA**
- **Spring Web**
- **Lombok** for getter/setter and constructor generation

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/AhmedMohammed3/simple-product-restapi.git
    ```
1. **Navigate to the project directory:**

   ```bash
   cd simple-product-restapi
    ```
1. **Build and run the application using Maven:**

   ```bash
   mvn clean spring-boot:run
    ```
1. Access the application:
   The API will be accessible at http://localhost:8080.

## Endpoints

### 1. **Create Product**
   - **POST** `/api/products`
   - **Request Body:**
   ```json
   {
     "name": "Product A",
     "description": "A sample product description",
     "price": 10.0,
     "stockQuantity": 50
   }
  ```
  - **Response:**
  ```json
  {
    "id": 1,
    "name": "Product A",
    "description": "A sample product description",
    "price": 10.0,
    "stockQuantity": 50
  }
  ```
  - **Validation Errors:**
  ```json
  {
    "message": "Validation failed: Name is required"
  }
  ```
### 2. **Get Product by ID**
   - **GET** `/api/products/{id}`
  - **Response:**
  ```json
  {
    "id": 1,
    "name": "Product A",
    "description": "A sample product description",
    "price": 10.0,
    "stockQuantity": 50
  }
  ```
### 3. **Get All Products (with Pagination and Sorting)**
   - **GET** `/api/products?page=0&size=5&sort=name,asc`
  - **Response:**
  ```json
  {
    "content": [
      {
        "id": 1,
        "name": "Product A",
        "description": "A sample product description",
        "price": 10.0,
        "stockQuantity": 50
      },
      {
        "id": 2,
        "name": "Product B",
        "description": "Another product",
        "price": 20.0,
        "stockQuantity": 100
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 5,
      "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
      }
    },
    "totalPages": 1,
    "totalElements": 2
  }
  ```
### 4. **Update Product**
   - **PUT** `/api/products/{id}`
   - **Request Body:**
   ```json
  {
    "name": "Updated Product A",
    "description": "Updated description",
    "price": 15.0,
    "stockQuantity": 60
  }
  ```
  - **Response:**
  ```json
  {
    "id": 1,
    "name": "Updated Product A",
    "description": "Updated description",
    "price": 15.0,
    "stockQuantity": 60
  }
  ```
### 5. **Delete Product**
   - **DELETE** `/api/products/{id}`
   - **Response:**
     - **Status Code:** `204 No Content`
     - **Response Body:** Empty (No content is returned in the response body when the product is successfully deleted. The response only includes the `204 No Content` status).
## Exception Handling

- **Validation Errors:** If the user sends an invalid request (e.g., missing required fields or sending negative values), the server will respond with a `400 Bad Request` error and a detailed message describing the validation failure.
- **Internal Server Errors:** If there's any internal issue, a `500 Internal Server Error` will be returned.

### Example of a Validation Error Response:
```json
{
  "message": "Validation failed: Name is required"
}
```
## Notes

- **In-memory Database:** This project uses an in-memory H2 database for data storage, meaning that all data will be lost when the application stops.
- **Sorting & Pagination:** You can sort and paginate the results of the "Get All Products" endpoint using query parameters (`page`, `size`, `sort`).
- **Exception Handling:** Custom exception handling is added to provide better error messages.

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -am 'Add feature'`)
4. Push to the branch (`git push origin feature-name`)
5. Open a pull request
