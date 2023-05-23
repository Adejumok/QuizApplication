# QuizApplication
A quiz application using spring boot framework where a user takes a quiz, fills a form containing the user's email address and gets the result of the quiz in his/her mail.

## Table of Contents

- [Project Name](#project-name)
  - [Table of Contents](#table-of-contents)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Project](#running-the-project)
  - [Usage](#usage)
  - [Endpoints](#endpoints)
  - [Contributing](#contributing)
  - [License](#license)
  - [Contact](#contact)

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- [Java](https://www.java.com/) (version 11 or higher)
- [Maven](https://maven.apache.org/) (version 3.9.9 or higher)
- [Postgres](https://www.postgresql.org/) (version 15.2 or higher)

### Installation

1. Clone the repository to your local machine:

```bash
git clone https://github.com/Adejumok/QuizApplication
```

2. Change directory to the project folder:

```bash
cd project-name
```

3. Create a Postgres database and update the database connection details in the `application.properties` file.

4. Build the project using Maven:

```bash
mvn package
```

### Running the Project

To run the project locally, follow these steps:

```bash
java -jar target/project-name-X.X.X.jar
```

The application will be running at `http://localhost:8080`.


## Usage

### Prerequisites

Before using this project, ensure that you have the following software installed on your machine:

- Java 11 or later
- Maven

### Running the Project

To run the project, follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/Adejumok/QuizApplication
```

2. Change to the project directory:

```bash
cd quizApp
```

3. Build the project with Maven:

```bash
mvn clean install
```

4. Start the application with Maven:

```bash
mvn spring-boot:run
```

The application will start and be available at `http://localhost:8080`. You can access the API endpoints using your preferred HTTP client, such as [Postman](https://www.postman.com/) or `curl`.

### API Endpoints

Here are the available API endpoints:

| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET    | /api/quiz{name} | Returns the quiz with . |
| GET    | /api/quiz/{id} | Returns the quiz with the specified ID. |
| POST   | /api/quiz | Adds a new quiz. |
| PUT    | /api/quiz/{id} | Updates the quiz with the specified ID. |
| DELETE | /api/quiz/{id} | Deletes the quiz with the specified ID. |

### Examples

Here are a few examples of how to use this project:

#### Example 1: Getting all quizzes

To get a list of all quizzes, send a `GET` request to `/api/quiz`.

#### Example 2: Adding a new quiz

To add a new quiz, send a `POST` request to `/api/quiz` with a JSON payload containing the quiz data.

```json
{
  "name": "New Quiz",
  "questions": {
                 "text":"The Question",
                 "answers": {},
                 "category": "Intermediate",
                 "correctAnswer": "....."
                 },
  "grade": 95
}
```

#### Example 3: Updating an item

To update an existing item, send a `PUT` request to `/api/items/{id}` with a JSON payload containing the updated item data.

```json
{
  "name": "Updated Quiz",
  "questions": {
                 "text":"The Question",
                 "answers": {},
                 "category": "Intermediate",
                 "correctAnswer": "....."
                 },
  "grade": 95
}
```

## Contributing

If you'd like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Make your changes and commit them with descriptive commit messages.
4. Push your changes to your forked repository.
5. Submit a pull request to the original repository.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

Please feel free to contact me if you have any questions or feedback.

- Email: roseadeyinka01@example.com
- GitHub: https://github.com/Adejumok
