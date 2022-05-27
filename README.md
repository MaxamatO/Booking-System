# Booking System

Spring Boot API that lets you add hotels, assign hotel rooms to them and add clients
to hotel rooms. Basic functions of a booking system. 

I'm willing to implement new features such as: deleting entities, adding payment credentials, email verification,
roles for users (admin, hotel owner, customer). 

### Built with

* [Spring Boot 2](https://spring.io/projects/spring-boot)
* [Spring Framework](https://spring.io/projects/spring-framework)

## Requirements
* Java 17
* Maven 3
* postgresql 

## Running the application locally
There are several ways to run a Spring Boot application on your local machine.
One way is to execute the `main` method in the `com.maxamato.bookingsystem.Application` class from your IDE.



#### Alternatively you can use the [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

For that in `application-dev.properties` you need to set parameters corresponding to the ones of your *newly created database*,
to which you'd like to connect.

After you set up everything, just run:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
