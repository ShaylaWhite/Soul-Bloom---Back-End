
# Soul Bloom - Backend
![Screenshot](Screenshot%202023-10-29%20211137.png)


Welcome to the Soul Bloom backend repository. This backend serves as the heart of the Soul Bloom application, providing the necessary API endpoints to support the virtual self-care garden experience. Here, you will find a detailed guide on the functionality, user stories, and API endpoints that the backend supports.

## Table of Contents

- [Introduction](#introduction)
- [Functionality](#functionality)
- [User Stories](#user-stories)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Contributing](#contributing)
- [License](#license)

  

## Introduction

Soul Bloom is a self-care and gardening application that allows users to create and nurture their virtual gardens, growing beautiful digital flowers to provide a sense of relaxation and achievement. This backend provides the necessary API endpoints to facilitate user registration, authentication, and the management of user gardens and flowers.

## Tools and Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- H2 Database
- JavaDoc
- Git and GitHub
- Spring Profiles
- Lucidchart: Lucidchart is a cloud-based diagramming tool that offers a wide range of diagram templates, including ERDs. It provides an intuitive interface for creating and collaborating on diagrams.
- Website: Lucidchart
- MockMvc Test Folder
  
## Functionality

The Soul Bloom backend includes the following key functionality:

- User registration and login with secure password hashing.
- Authentication using JSON Web Tokens (JWT).
- Retrieving a user's garden and associated flowers.
- Adding flowers to a user's garden, including providing a self-care type and description.
- Deleting flowers from a user's garden.

## User Stories

1. As a user, I want to register for an account with a unique username and a secure password.

2. As a user, I want to log in with my username and password to access my garden.

3. As a user, I want to have a personal garden that is displayed upon logging in.

4. As a user, I want to add flowers to my garden as a form of self-care.

5. As a user, I want to provide a self-care type and description for each flower I plant.

6. As a user, I want to delete a specific flower from my garden.

# Soul Bloom API Endpoints

## User Authentication

### Create a User
- **Endpoint**: POST http://localhost:9092/auth/users/register/
- **Request body**: JSON representing a User object
- **Description**: Use this endpoint to create a new user.

### Login User
- **Endpoint**: POST http://localhost:9092/auth/users/login/
- **Request body**: JSON containing login credentials
- **Description**: Use this endpoint for user authentication and login.

## User Management

### Create a User
- **Endpoint**: POST http://localhost:8080/api/users/
- **Request body**: JSON representing a User object
- **Description**: Use this endpoint to create a new user.

### Get All Users
- **Endpoint**: GET http://localhost:8080/api/users/
- **Description**: Use this endpoint to retrieve a list of all users.

### Get User by ID
- **Endpoint**: GET http://localhost:8080/api/users/{userId}
- **Replace {userId} with the actual ID of the user you want to retrieve.
- **Description**: Use this endpoint to retrieve a user by their ID.

### Update User by ID
- **Endpoint**: PUT http://localhost:8080/api/users/{userId}
- **Replace {userId} with the actual ID of the user you want to update.
- **Request body**: JSON representing the updated User object.
- **Description**: Use this endpoint to update a user's information by their ID.

### Delete User by ID
- **Endpoint**: DELETE http://localhost:8080/api/users/{userId}
- **Replace {userId} with the actual ID of the user you want to delete.
- **Description**: Use this endpoint to delete a user by their ID.

### Add a Flower to a User's Garden
- **Endpoint**: POST http://localhost:8080/api/users/flowers
- **Request body**: JSON representing a Flower object.
- **Description**: Use this endpoint to add a flower to a user's garden.

### Delete a Flower from a User's Garden by Flower ID
- **Endpoint**: DELETE http://localhost:8080/api/users/flowers/{flowerId}
- **Replace {flowerId} with the actual ID of the flower you want to delete from the user's garden.
- **Description**: Use this endpoint to delete a flower from a user's garden by Flower ID.

### Water a User's Garden by Garden ID
- **Endpoint**: PUT http://localhost:8080/api/users/water-garden/{gardenId}
- **Replace {gardenId} with the actual ID of the user's garden you want to water.
- **Description**: Use this endpoint to water a user's garden.


## Entity-Relationship Diagram (ERD)

### User (Table)

- **UserID** (Primary Key)
- Username
- Password (hashed)
- GardenID (Foreign Key, links to the Garden table)

### Garden (Table)

- **GardenID** (Primary Key)
- UserID (Foreign Key, links to the User table)
- LastWatered (Timestamp)

### Flower (Table)

- **FlowerID** (Primary Key)
- GardenID (Foreign Key, links to the Garden table)
- SelfCareType
- Description

## Entity-Relationship Diagram (ERD) Table

![Screenshot](https://github.com/ShaylaWhite/Soul-Bloom---Back-End/raw/main/Screenshot%202023-10-18%20123041.png)


**Entity-Relationship Diagram (ERD) Relationships**
- The User table is linked to the Garden table through the GardenID foreign key.

- The Garden table is linked to the Flower table through the GardenID foreign key.

- The (PK) indicates the primary key, and (FK) indicates a foreign key relationship.

Request Type	URL	Functionality	Access
POST	/auth/users/login/	User login	Public
GET	/api/categories/	Get all categories	Private
POST	/auth/users/register/	User registration	Public
GET	/api/users/{userId}	Get user profile by user ID	Private
PUT	/api/users/{userId}	Update user profile by user ID	Private
DELETE	/api/users/{userId}	Delete user by user ID	Private
GET	/api/users/gardens/{gardenId}	Get user's garden by garden ID	Private
PUT	/api/users/water-garden/{gardenId}	Water user's garden by garden ID	Private
POST	/api/users/create-garden	Create user's garden	Private
POST	/api/users/add-flower	Add a flower to the user's garden	Private
DELETE	/api/users/flowers/{flowerId}	Delete a flower from the user's garden by flower ID	Private
PUT	/api/users/flowers/{flowerId}	Update a flower by flower ID	Private
These are all the endpoints for Soul Bloom, categorized by functionality and access level.
## Dependency Breakdown 

## Getting Started with Dependencies 

To start a new Spring Boot project, you can follow these steps:

1. Go to the [Spring Initializr website](https://start.spring.io/).

2. Configure your project by selecting the desired options such as project type, language, and packaging. You can also add dependencies by searching and selecting them.

3. Once you've configured your project, click the "Generate" button.

4. Download the generated project ZIP file.

5. Extract the contents of the ZIP file to your preferred location on your computer.

6. Open the project in your favorite Integrated Development Environment (IDE).

7. Follow the installation instructions provided in the [Dependencies](#dependencies) section of this README to add the required dependencies to your project.

8. Start coding and building your Spring Boot application!

This README assumes that you've already created a Spring Boot project using the Spring Initializr website. If you're looking for instructions on how to add specific dependencies to your project, please refer to the [Dependencies](#dependencies) section for details.

## Dependencies 

- **Spring Boot Starter Web for RESTful APIs**
    - [Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications)
    -   This dependency provides the necessary libraries and configurations for developing RESTful APIs with Spring Boot.
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      ```

- **Spring Boot Starter Data JPA for database interactions**
    - [Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-jpa-and-spring-data)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
      ```

- **H2 Database for testing (you can replace it with your preferred database)**
    - [Documentation](https://www.h2database.com/html/main.html)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
          <scope>runtime</scope>
      </dependency>
      ```

- **JUnit Jupiter for testing**
    - [Documentation](https://junit.org/junit5/docs/current/user-guide/)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter-api</artifactId>
          <version>5.8.1</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter-engine</artifactId>
          <version>5.8.1</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
      ```

- **Spring Boot Starter Security**
    - [Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
      </dependency>
      ```

- **JSON Web Token (JWT) Dependencies**
    - **jjwt-api**
        - [Documentation](https://github.com/jwtk/jjwt)
        - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

          ```xml
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-api</artifactId>
              <version>0.11.5</version>
          </dependency>
          ```

    - **jjwt-impl**
        - [Documentation](https://github.com/jwtk/jjwt)
        - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

          ```xml
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-impl</artifactId>
              <version>0.11.5</version>
              <scope>runtime</scope>
          </dependency>
          ```

    - **jjwt-jackson**
        - [Documentation](https://github.com/jwtk/jjwt)
        - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

          ```xml
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-jackson</artifactId>
              <version>0.11.5</version>
              <scope>runtime</scope>
          </dependency>
          ```

  ## Challenges Faced
- Mock MVC Challenges: Initially, none of our tests were passing due to issues in our test implementation with Mock MVC. We had to create a mock MVC user to resolve this.



## Getting Started

### Prerequisites

Before setting up the Soul Bloom backend, make sure you have the following installed:

- Node.js: [Install Node.js](https://nodejs.org/)

### Installation

1. Clone this repository:
   ```sh
   git clone https://github.com/your-username/soul-bloom-backend.git
   ```

2. Navigate to the project directory:
   ```sh
   cd soul-bloom-backend
   ```

3. Install dependencies:
   ```sh
   npm install
   ```

4. Configure the backend to connect to your preferred database.

5. Start the backend server:
   ```sh
   npm start
   ```

The backend will be accessible at the specified port (default: 3000) and ready to handle requests from the Soul Bloom frontend.

## Contributing

Contributions to the Soul Bloom backend are welcome! If you'd like to contribute, please follow our [Contribution Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [MIT License](LICENSE).

---

Thank you for using the Soul Bloom backend to power your virtual self-care garden. If you have any questions or need support, please contact us at [your-email@example.com](mailto:your-email@example.com).

Happy gardening and self-care!
