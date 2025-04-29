# 1: Prerequisite
- Cài đặt JDK 17+
- Install Maven 3.5+
- Install Docker, Docker Compose
- Install MySQL 8.0+

# 2: Technical Stacks
  - Java 17
  - Maven 3.5+
  - Spring Boot 3.4.1
  - Spring Web
  - Spring Data JPA
  - Lombok
  - MySQL Driver
  - Validation
  - Springdoc OpenAPI (Swagger UI)

# 3. Build & Run Application
## Clone dự án
```
git clone git@github.com:NguyenThien02/clinic.git
```

## Build và chạy toàn bộ hệ thống (backend + database)
- Di chuyển vào thư mục dự án
  ```
  cd ./clinic
  ```
- Build và chạy ứng dụng
  ```
  docker-compose up --build
  ```
## Truy cập ứng dụng 
- Backend API: http://localhost:8080/v3/api-docs

- Swagger UI: http://localhost:8080/swagger-ui/index.html
