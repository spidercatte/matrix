# Coding Challenge Application

## Development

### Setup Local Environment

#### Prerequisites
- Ensure that you have Java Development Kit (JDK) installed on your machine. The application is developed and tested with JDK 8 or later.
- Install [Gradle](https://gradle.org/install/) on your machine. The application is built and managed using Gradle.

#### Install Required Libraries and Dependencies
Before building and running the application, make sure to install the necessary libraries and dependencies by running the following Gradle command:
```
./gradlew clean build
```

This command will download the necessary libraries and dependencies, compile the code, and build the project.
```
./gradlew clean build
```

### Run Locally
After setting up the environment, follow these steps to run the Spring Boot REST application locally:

### Run Tests
```
./gradlew test
```

### Run Test Coverage
```
./gradlew jacocoTestReport
```

#### Run the Application
This command will start the Spring Boot application locally.
```
./gradlew bootRun
```

#### Access the Application
Open a web browser and navigate to http://localhost:8080 to access the running application.

#### Test the Endpoints
Use Postman collection (`CodingTest.postman_collection.json`) or curl to test the REST endpoints exposed by the application.

```
curl -v -F 'file=@"/path/matrix.csv"' localhost:8080/api/echo
```

#### Shutdown the Application
To stop the running application, press Ctrl + C in the terminal where the application is running.

## Packaging

### Create a Docker Image
```
./gradlew bootBuildImage

```



