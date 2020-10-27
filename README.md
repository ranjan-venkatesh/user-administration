# user-administration

## Overview 
• The application offers CRUD operations on a database of users.

• Each User has following fields

  ◦ First Name
  
  ◦ Last Name
  
  ◦ Email
  
  ◦ Password
  
  ◦ DateOfBirth
  
• The API also provides a functionality to authorize and authenticate the users for every RESTful endpoint. For testing, you may use following :
  
  ◦ Existing ADMIN Credentials : {username:admin; password:password}
  
  ◦ Existing USER Credentials : {username:user@web.de; password:pass}
  
  ◦ Please note that above example ADMIN credential is created only for simplicity. However, the format of of the field must be that of email.

## REST API
| Request Type 	| Description (Authorized)        	| URL                               	| Request Body                                                                                                                                                                                       	|
|--------------	|---------------------------------	|-----------------------------------	|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| GET          	| Get all users (Admin)           	| http://localhost:8080/<br>users   	| Invoke with<br>ADMIN credentials                                                                                                                                                                   	|
| GET<Id>      	| Get user with Id (User)         	| http://localhost:8080/<br>users/1 	| Invoke with USER<br>specific credentials                                                                                                                                                           	|
| POST         	| Insert a new user(All)          	| http://localhost:8080/<br>users   	| {<br>"firstName":"Max",<br>"lastName":"Mueller",<br>"email" :<br>"max.mueller@mail.de",<br>"password":"maxie123",<br>"dateOfBirth":"12-09-<br>1998"<br>}                                           	|
| PUT<Id>      	| Update a user with Id<br>(User) 	| http://localhost:8080/<br>users/1 	| Invoke with User<br>specific credentials<br>{<br>"firstName":"Max",<br>"lastName":"Mueller",<br>"email" :<br>"max.mueller@mail.de",<br>"password":"max123",<br>"dateOfBirth":"12-09-<br>1998"<br>} 	|

## How to Run the Program
### Requirements 

1. Java version >= 1.8

2. Maven (install on debian with "sudo apt install maven")(Optionally you can download the Jar and use directly)

3. Wildfly Server (ZIP can be downloaded. Also create a user and run the server)

### Steps

0. Open "<root_directory>/POM.xml” and update properties to the values that of your local Wildfly server (credentials of user).

1. Open root directory and run command "mvn install" on local terminal

2. If the execution succeeds, there will be a war file generated in the path "<root_directory>/target" and it is auto-deployed on running server.

3. If deployment is successful, visit “ http://localhost:8080/application/swagger-ui.html” to access the swagger tool. Else, one can also use rest clients to hit the APIs.
