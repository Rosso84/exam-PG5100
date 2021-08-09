# Travel Agency, A Fullstack Webapplication for booking systems
This is a fullstack web application that focuses on testing, security, and developement of the backend side of a enterprise system. Currently, this repository focuses on Java, targeting frameworks like Spring and Java EE.

For building GUIs, the repository
Quizgame-SAP-FullStack-Webapp-in-ReactJS' (https://github.com/Rosso84/Quizgame-SAP-FullStack-Webapp-in-ReactJS) should be used. It relies on knowledge of JavaScript and Single-Page-Applications and focuses more on frontend side of a webapp.
This enterprise application uses JSF as frontend simply because I am still learning and following the materials of my former teachers materials (which can be viewed here: https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/doc/intro/main.md). Eventually I will later on switch the current frontend with ReactJS.

Note: some of the codes used here has legally been reused from teachers repository.

## Requirements
JDK 11 (download it from https://adoptopenjdk.net/, do not use the JDK from Oracle)

An IDE (recommended IntelliJ IDEA Ultimate Edition)

Maven 3.x

Docker

## How to install and run
Ones you have maven installed open up terminal and run

>mvn clean install

You can run it locally by navigating to frontend/src/test/java/org/studentnr
and run LocalApplicationRunner.class

or using maven command once you are inside root of frontend (travelagency/frontend/):

>mvn spring-boot:run

The application will then be available at http://localhost:8080.

## Docker
The application has a Dockerfile set up ready for deployment to cloud services such as Kubernetes or container instances/registries and has been tested at Heroku.

## Usefull docker commands
View available built/images in your machine:
>Docker ps
>
>Docker images

Removing images:
>Docker volume prune
>
>Docker system prune -a








