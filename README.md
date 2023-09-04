# Backend for a Conference Management System
A backend created with Java, Spring Boot, H2 Database and Maven.

## General Information
This project is a backend for a Conference Management System. It handles user register in system, singing up for different event paths if these are still avaliable and unsubscribing from those. The conference supports 3 different thematic paths in parallel. If a user signs up for a path at a certain time, they cannot sign up for another path at the same time. Each lecture has a maximum number of participants (for tests sake, 5 was assumed as the maximum). All data is stored and retrieved from H2 Database. Maven was used for building project, Spring Boot for handling micro services, which can be accessed via REST API. 

## Use Cases
Scenarios that system handles:
- sign up/ log in 
- change e-mail
- view the conference schedule
- after logging in users can see what lectures they had signed up
- sign up for certain path if these have not reached maximum number of participants
- cancel reservation on certain lecture
- display a list of registered users along with their e-mail addresses
- generating comparisons: percentage share in all lectures, percentage share in certain paths.

## Technologies
Project is created with:
| Tool | version |
| ----------- | ----- |
| Java | 11 |
| H2 Database | 2.1.212 |
| Spring Boot | 2.7.0 |
| Maven | 3.8.1 |

## Setup
To run this project, install it locally using mvn:

```
$ cd ../demo
$ mvn clean install
$ mvn spring-boot:run
```

## REST Requests
Here are some examples of testing REST API requests with Postman.
[RestApiRequests.pdf](https://github.com/Kasia-hue/backendCMS/files/12513312/RestApiRequests.pdf)

## Credits
This project was inspired by sii recruitment task.
