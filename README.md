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
$ https://github.com/kumarbaburaavi/carrental.git
```
#### Run CarRental ReST service with maven build tool 
Application run with DEV profile which is default and it connects with H2 in-memory database.
```
$ cd carrental
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
* API Reference for Developer : http://localhost:8080/swagger-ui.html
* Design Ref: https://github.com/kumarbaburaavi/carrental/blob/main/Design.png

## Usage

Request Method | URI | Body (JSON) | Description |  
:---: | :--- | :---: | :--- |
POST | http://localhost:8080/car-rental/api/v1/create-car | { "car": "[http...]" }| Create a car | 
POST | http://localhost:8080/car-rental/api/v1/create-customer | { "customer": "[http...]" }| Create a customer  | 
POST | http://localhost:8080/car-rental/api/v1/book-rental | { "Book": "[http...]" } | Book car rental |
POST | http://localhost:8080/car-rental/api/v1/register-car-availability | { "Free Pool": "[http...]" } | Register cars in availability pool | 
GET | http://localhost:8080/car-rental/api/v1/car-availability/{from}/{to} | - | Find cars availability between the dates | 
GET | http://localhost:8080/car-rental/api/v1/mileage-greater-than/{mileage} | - | Find and return cars above given mileage| 
GET | http://localhost:8080//car-rental/api/v1/running-rentals | - | Find and return cars which are in rental| 
PUT | http://localhost:8080/car-rental/api/v1/finish-rental/{customerId}/{km}/{hours}/{returnDate} |  { "rental": "[http...]" } | Finish the rental | 
DELETE | http://localhost:8080/car-rental/api/v1/delete-car/{registrationNr} | - | Remove car | 
DELETE | http://localhost:8080/car-rental/api/v1/delete-customer/{customerId} | - | Remove customer | 
