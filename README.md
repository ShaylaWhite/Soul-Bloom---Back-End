
# Soul Bloom - Backend

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
