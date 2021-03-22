# Car Rental Application
`Car Rental Service` is a simple Spring Boot MVC REST project.

## Description
Req: Develop a Car Rental Service

`Rent A Car` is an application which provides the a REST API to perform CRUD operations on customers, cars, rentals etc.

## Usecase

* Register a car with its plate number
* Register availability of a car from date/time, to date/time, rental price per hour
* Search for available cars to rent from date/time, to date/time, maximum rental price per hour
* Register a customer (to rent a car)
* Book a car from date/time, to date/time, customer id, car id

## Technologies
The goal of this application is to get an in-depth feeling for the usage of the `Spring framework`. 
The following subjects were applied inside this project:
* Spring Boot
* H2 in memory database
* JPA ORM (Hibernate impl.)
  * With the usage of the `Bean Validation Framework`

# (Maven) project structure
There are many packages inside the project, in order to keep the code clean:

### src/main/java/...
* `bean`: Contains all form backing beans.
* `configuration`: Contains all configuration classes. In particular messages and security.
* `controller`: Classes annotated with `@RestController`, which contain REST endpoints.
* `exception`: Contains logic to handle exceptions.
* `model`: Contains all JPA entity classes.
* `repository`: Contains all repositories to access the database.
* `service`: Contains all service classes which perform further business logic.

### src/main/resources/...
Contains templates, insert scripts and configuration files.
* `data`: Used for initial test data.
* `application.properties`: Contains the server configuration.
* `messages_xx.properties`: Message files used to internationalize the application.

# Domain model (JPA entity classes)
The main entity class is _Rental.java_. This class contains several unidirectional `@ManyToOne` mappings to the other entities. It was decided to do unidirectional mapping because any sort of dependency to other classes is done through this class only. Thus, all other classes do not need an extra reference to this class.

# Messages and internationalization
Many fields of form backing beans and entity classes contain validation steps done with the `Java Bean Validation Framework`. To make the application accessable and user friendly for several regions, corresponding `messages_xx.properties` files can be created. Until now the only supported language is English, however, adapting the properties file to another locale prevents from hardcoding strings inside the bean validation annotations.

# Install
#### Download the repository
```
$ git clone https://github.com/kumarbaburaavi/tutorial.git
```
#### Run CarRental ReST service with maven build tool 
Application run with DEV profile which is default and it connects with H2 in-memory database.
```
$ cd urlshortener
$ mvn clean test                         # Run the testcases and code coverage
$ mvn clean package -DskipTests=true    # Compile and Build the package
$ mvn spring-boot:run                    # Run the spring boot application.

Note: All the generated artifcats shall be found in target folder 
target/
|-- classes
|   |-- META-INF
|   |-- application.properties
|   `-- com
|-- generated-sources
|   `-- annotations
|-- generated-test-sources
|   `-- test-annotations
|-- jacoco.exec
|-- maven-status
|   `-- maven-compiler-plugin
|-- site
|   `-- jacoco
|-- surefire-reports
`-- test-classes
    `-- com
```
API Reference for Developer : http://localhost:8080/swagger-ui.html