
## Overview

This guide should help you to setup the local application's environment for running and testing it locally.
You have to have a working connection with the Internet.
The steps were tested only on MacOS, but it should work on Linux and Windows as well.

## Downloading the project using GIT
Assuming that GIT is installed correctly, in the command line choose a folder in your computer where the project will leave.


Then run the command:

> **git clone https://maikelarabori@bitbucket.org/maikelarabori/spring-boot-demo.git**


A new folder containing all the source code should be created inside your current folder.

*This is the first step, and ALL other steps below depend on this one.*

## Building and executing the application

Assuming that Maven 3.6.x and Java 1.8.x are installed correctly, in the command line go to the project's folder. *Ie.: /some/folder/spring-boot-demo*


Then run the command:


> **mvn spring-boot:run**


The application will run at **http://localhost:8080**

## Testing the application

1. Add a sample user:
> **curl -d '{"username":"user", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/users**

*The users API is just a test API  created to facilitate the tests backed by a in-memory database.*

2. Hit the data elements endpoint:
> **curl --user user:password http://localhost:8080/api/data/elements**

3. Hit the data element groups endpoint:
> **curl --user user:password http://localhost:8080/api/data/element/groups**

## Generating the API documentation

The documentation will be generated every time the Maven goal "package" is run.
So, to test it we can just run:
> **mvn package**

This will run all tests and create an index.html at:
*spring-boot-demo/target/generated-docs/index.html*

Open this file and you will find the REST API documentation.

## Running only the unit and integration tests

Simply run:
> **mvn test**
