
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

## API Endpoints

1. User Registration:
   - **API Endpoint:** POST /auth/register
   - **Functionality:** Register a new user with a unique username and a secure password.
   - **Access:** Public

2. User Login:
   - **API Endpoint:** POST /auth/login
   - **Functionality:** Log in and obtain an authentication token with a username and password.
   - **Access:** Public

3. Retrieve User's Garden:
   - **API Endpoint:** GET /api/garden
   - **Functionality:** Retrieves information about the user's garden, including the list of flowers they've planted and any maintenance information.
   - **Access:** Private (Requires Authentication)

4. Add Flower to Garden:
   - **API Endpoint:** POST /api/garden/flowers
   - **Functionality:** Allows users to add a flower to their garden, providing a self-care type and description for the flower.
   - **Access:** Private (Requires Authentication)

5. Delete Flower from Garden:
   - **API Endpoint:** DELETE /api/garden/flowers/{flowerId}
   - **Functionality:** Removes a specific flower from the user's garden.
   - **Access:** Private (Requires Authentication)
  
## Entity-Relationship Diagram (ERD)

### User (Table)
- UserID (Primary Key)
- Username
- Password (hashed)
- GardenID (Foreign Key, links to the Garden table)

### Garden (Table)
- GardenID (Primary Key)
- UserID (Foreign Key, links to the User table)
- LastWatered (Timestamp)

### Flower (Table)
- FlowerID (Primary Key)
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
