# Technology Stack required for this projects is mentioned below
1) Requires MySQL 5.6 or above external Database.
2) JDK & JRE 1.8 above.
3) STS IDE or Eclipse IDE will be helpfull for the project setup & development.
4) POSTMAN API Client is used to invoke the Rest API endpoints that are created in this project.
5) MySQL Workbench v6.3 & above will help for viewing the database schema, tables, data & perform any other operations.
6) Spring Boot Framework is used in this project development. its prefered & recommended to use.
7) This brings Spring Data/JPA into picture too.
8) No external application server is required. Since we're using Spring Boot, this framework provides us with embedded Apache Tomcat.

# In application.properties, initially this value will be - spring.jpa.hibernate.ddl-auto = create
# This is for the creation of tables in database. Later change the value to : update -> spring.jpa.hibernate.ddl-auto = update
# Also view pom.xml for more details.

# Use Postman for invoking the GET, PUT, POST, DELETE API's of this project.
# POSTMAN is a REST API Client wherein you can invoke REST API's of any RESTful Application with expected credentials and params
