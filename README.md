# junit-ticket-booking
JUnit Ticket Booking project is Complete Springboot Maven project with JPA and Rest API. 
It exposes Flight and Ticket controller for booking flight. 

Idea is to create a project and write Junit Test Cases. 

# H2 Database 
H2 DB Can be access with URL
http://localhost:8181/h2-console
username : sa

# application.properties
it containes details for h2 database and other spring-jpa properties. 

# schema.sql and data.sql
src/main/resource - containers 
schema.sql - Defined Tables used in the project
data.sql - some insert Scripts run at the start of the project

# src/test/java
All test-cases are written inside src/test/java
Separate database configuration is defined under src/test/java/resource folder.

# Build and Run

Project can be run with Command : mvn clean install 
This will also run all the test cases

To Start the application Command : 
mvn spring-boot:run

# Postman Collection
Postman collection contains API endpoints. 
