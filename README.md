# Travel Forum, A Fullstack Webapplication For Reviewing Travels
This webapplication focuses on testing, security, 
and developement of the backend side of a enterprise system.
The repository focuses on Java, targeting frameworks like Spring and Java EE and was my exam task in august 2021,
where we had 48 hours to to fulfill all the requirements .


[The exam task can be viewed here.](/PG5100%20Enterprise%201%20-%20august%202021.pdf "Exam text")



## Materials used
A lot of the codes in this repository has legally been reused from my teachers materials, which can be viewed here:
https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/intro



## My acheivements 
 In the “backend” module, there are 3 JPA entities: 

- User: having info like name, surname, hashed-password, email, etc

- Item: having info like category, name, description

- Rank: score 1-to-5 (made by a specific user for a specific item), possibly a comment to the ranked item, etc.

There are Spring @Service classses with at least functionalities:

- create a user
- rank an item (a user can only do it once per item)
- leave a comment to a ranked item
- update the rank/comment on an item
- compute the average rank per item
- +more

For testing:
- There are integration tests for each of the @Service classes, using JUnit and @SpringBootTest
annotation. 
  
- There are at least one test for each of the public methods in those services. 

- The calculation of code coverage with JaCoCo is enabled. 
  
- Theres tests with codecoverage of 100% classes, 73% methods and 88% lines


In the “frontend” module, you need to provide at least the following features:

- Homepage: displays all the items, with info summaries, and links to detail page. 
  Each Item has an average score provided in ItemDetail Page.
  
- If a user is logged in, it displays a welcome message.

- Homepage does have a filter system in which a user can filter out items based on 
  category (only added 2 categories Watersport and Tour). It shows all items, or only the 
  items for a specific category.
  
- In the item detail page, a logged-in user can give/update his/her star rank/score and
  possibly leave one single comment (which can be updated). All comments from other users
  is visible (regardless of current user being logged in) and shows on how many
  voters the rank was computed (e.g., “average 4.7 based by 11 users”).

- There is a fully working User login/signup page, based on Spring Security and storing of user info on the SQL
  database. It is possible to logout from any of the pages via a ´logout´ button. When a
  login/signup fails, it shows an error message. A unique email address is used as  UserID and MUST be a valid email format. 
  Password must be at least 6 digits 
  
- User account details showing basic info like email, name and surname etc, and also how
  many votes the user has casted.
  
- When running in the application in development mode, the application has
  some fake data already present in the database (3 users, 15 items and each item ranked by all three users).
  
Extras: 
- Added some poor style and design
- Added the possibilty to purchase and book a trip. You can also view your booked trips in your account page.



## Requirements To Run Application
JDK 11 (download it from https://adoptopenjdk.net/, do not use the JDK from Oracle)

An IDE (recommended IntelliJ IDEA Ultimate Edition)

Maven 3.x

Docker

## How to install and run
Ones you have maven installed open up terminal and run in root

>mvn clean install

You can run it locally by navigating to frontend/src/test/java/org/studentnr
and run LocalApplicationRunner.class

or using maven command once you are inside root of frontend (travelagency/frontend/):

>mvn spring-boot:run

The application will then be available at http://localhost:8080.

## Docker / Heroku
The application has a Dockerfile set up ready for deployment to cloud services such as Kubernetes or container instances/registries or Heroku

## Usefull docker commands
View available built/images in your machine:
>Docker ps
>
>Docker images

Removing images:
>Docker volume prune
>
>Docker system prune -a








