# Notification Service

## Overview

This project is a Spring Boot-based Notification Service designed to handle various types of notifications, primarily focusing on email. It provides a robust and extensible platform for other microservices to send notifications to users, ensuring reliable delivery and centralized management of notification events.

## Features

*   **RESTful API**: Exposes endpoints for other services to trigger notifications.
*   **Email Notifications**: Supports sending email notifications using Spring Mail.
*   **Notification Status Tracking**: Records and tracks the status of each sent notification (e.g., SUCCEEDED, FAILED).
*   **Data Persistence**: Stores notification details in a relational database (MySQL).
*   **Validation**: Ensures the integrity of incoming notification requests.
*   **Inter-service Communication**: Utilizes OpenFeign for declarative HTTP clients, enabling seamless integration with other services.

## Technologies Used

*   **Spring Boot**: Framework for building stand-alone, production-grade Spring applications.
*   **Spring Data JPA**: Simplifies data access and persistence with Hibernate as the JPA provider.
*   **MySQL**: Relational database for storing notification data.
*   **Spring Mail**: For sending email messages.
*   **Spring Web**: Provides capabilities for building web applications and RESTful APIs.
*   **Spring Cloud OpenFeign**: Declarative REST client for easy integration with other microservices.
*   **Lombok**: Reduces boilerplate code (getters, setters, constructors, etc.).
*   **Maven/Gradle**: Build automation tool (Gradle in this case).
*   **Java 17**: Programming language version.

## Architecture

The application follows a standard Spring Boot microservice architecture:

*   **Controller Layer (`web`)**: Handles incoming HTTP requests, maps them to DTOs, and delegates to the service layer. It exposes the RESTful API.
*   **Service Layer (`service`)**: Contains the core business logic. It orchestrates the creation, sending, and status updates of notifications.
*   **Repository Layer (`repository`)**: Interacts with the database using Spring Data JPA for CRUD operations on `Notification` entities.
*   **Model Layer (`model`)**: Defines the data structures for notifications, including `Notification`, `NotificationStatus`, and `NotificationType`.
*   **DTOs (`web.dto`)**: Data Transfer Objects used for request and response payloads, ensuring clear contract between the service and its clients.
*   **Mapper (`web.mapper`)**: Converts between DTOs and model entities.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

*   Java Development Kit (JDK) 17 or higher
*   Gradle (usually bundled with Spring Boot projects, `./gradlew`)
*   MySQL Database
*   An IDE like IntelliJ IDEA or Eclipse (optional)

### Database Setup

1.  **Create a MySQL Database**: Create a new schema for the notification service, e.g., `notification_db`.

    ```sql
    CREATE DATABASE notification_db;
    ```

2.  **Update `application.yaml`**: Configure your database connection details in `src/main/resources/application.yaml` (or `application-prod.yaml` for production profile):

    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/notification_db?createDatabaseIfNotExist=true
        username: your_mysql_username
        password: your_mysql_password
        driver-class-name: com.mysql.cj.jdbc.Driver
      jpa:
        hibernate:
          ddl-auto: update # or create, create-drop if you want Hibernate to manage schema
        show-sql: true
        properties:
          hibernate:
            format_sql: true
    ```

### Configuration

*   **Email Settings**: Configure your email server details in `application.yaml` for `spring.mail` properties (host, port, username, password, etc.).

### Building and Running

1.  **Clone the repository**:

    ```bash
    git clone <repository-url>
    cd notification-service
    ```

2.  **Build the project** using Gradle:

    ```bash
    ./gradlew clean build
    ```

3.  **Run the application**:

    ```bash
    java -jar build/libs/notification-service-0.0.1-SNAPSHOT.jar
    ```

    Alternatively, you can run it directly via Gradle:

    ```bash
    ./gradlew bootRun
    ```

    The application will typically start on `http://localhost:8080`.

## API Endpoints

### Notifications

*   **`POST /api/notifications`**
    *   **Description**: Sends a new notification.
    *   **Request Body**: `NotificationRequest` DTO containing `subject`, `body`, `contactInfo`, `type`, `userId`.
    *   **Example**: (Assuming email notification)

    ```json
    {
        "subject": "Welcome to our service!",
        "body": "Thank you for registering. We are excited to have you.",
        "contactInfo": "user@example.com",
        "type": "EMAIL",
        "userId": "123"
    }
    ```
    *   **Response**: `NotificationResponse` DTO with details of the sent notification, including its status.

## Contributing

Feel free to fork the repository, open issues, and submit pull requests.

## License

This project is licensed under the [LICENSE](LICENSE) file.
