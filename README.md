 ## Employee Management System  ##

- This project is an Employee Management System that allows you to perform CRUD operations on employees, retrieve the nth level manager, implement pagination and sorting, and send emails to managers upon new employee addition.

# Prerequisites
1. Java JDK(version 8 or above)
2. Maven
3. MongoDB

##  Steps ##

1. I used MongoDB a NOSQL database, so first install it and set it up either by setting up MongoDB compass for GUI or MongoDB shell for CLI to see the data. And create a database named myDB.

2. create project structure from Spring initializer with dependency Spring Web, Spring Data MongoDB.

3. Create project structure- entity package for spring data hibernate, repository, controller for RestApi endpoint, and service layer to implement logic. The project structure is based on Spring MVC.

4. first I perform all the crud operations, then add the method in the controller that handled the usecase of nth level manager, after that integrate the email feature.

5. I configure the application.properties file inside resource so that my application connects with database and third party api like smtp host for email.

# TO run #

1. first run the MongoDB by using (mongod command inside bin) which runs locally at port 27017.

2. Now run the EmployeeAppApplication file that is present in src file of project structure, it runs the application at port 8080 default.

3. now we can perform all the operation in postman to test our api or we can also integrate swaggerUI
