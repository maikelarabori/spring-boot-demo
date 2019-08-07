
## Overview
This application contains the basic usage of a few Spring modules: Spring Boot, Spring Data JPA, Spring Security, Spring Data REST, Spring REST Docs and Caching.

It contains four protected endpoints, which requires a basic authentication (username/password).
Two of these endpoints defined at _DummyController.java_ are used to simulate external protected endpoints (they require authentication). Even though they are part of the same application you should see them as "external" services. This will allow the simulation of an authenticated REST call using the Spring WebClient component along with the basic authentication feature (providing an username and password).

The other two endpoints exposed will also require authentication for any incoming request. So, your client (Javascrit library or a tool like Postman) will have to provide a valid username and password as well.

For both scenarios above we are testing a basic database (in-memory) authentication using Spring Security rules/definitions in order to expose protected endpoints and authenticate sucessfully.

There is also a caching feature, which is based on Caffeine. This was implemented to simulate the caching of "
external" REST invocations so minimize "external" calls. Remember that these "external" services are simulated/mocked by the dummy endpoints found at _DummyController.java_.

Finaly, the following steps will help you to setup a local environment for running and testing the application locally.
The steps were tested only on MacOS, but it should work on Linux and Windows as well. Of course, you have to have a working connection with the Internet.

## Downloading the project using GIT
Assuming that GIT is installed correctly, in the command line choose a folder in your computer where the project will leave.


Then run the command:

> **git clone https://github.com/maikelarabori/spring-boot-demo.git**


A new folder containing all the source code should be created inside your current folder.

*This is the first step, and ALL other steps below depend on this one.*

## Building and executing the application

Assuming that Maven 3.6.x and Java 1.8.x are installed correctly, in the command line go to the project's folder. *Ie.: /some/folder/spring-boot-demo*


Then run the command:


> **mvn spring-boot:run**


The application will run at **http://localhost:8080**

## Testing the application

The simulation to "external" REST services will require an username/password as it would require in a real world authentication following the same standard. So, the hardcoded username/password able to authenticate to the "external" dummy services is: _user_/_password_. You will see it recommended by the following steps:

**_If you register any username/password different from user/password, you will get access denied when trying to authenticated against the "external" REST endpoints._**

1. Add a sample user:
> **curl -d '{"username":"user", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/users**

*The users API is just a test API  created to facilitate the tests backed by a in-memory database.*

2. Hit the vehicle makes endpoint:
> **curl --user user:password http://localhost:8080/api/vehicles/makes**

3. Hit the vehicle makes groups endpoint:
> **curl --user user:password http://localhost:8080/api/vehicles/makes/groups**

## Generating the REST API documentation

This application uses the Spring REST Docs module.
The documentation will be generated every time the Maven goal "package" is run.
So, to test it we can just run:
> **mvn package**

This will run all tests and generate an index.html, containing the REST API documentation, at:
*spring-boot-demo/target/generated-docs/index.html*

Open this file and you will find the REST API documentation.

## Running only the unit and integration tests

Simply run:
> **mvn test**
