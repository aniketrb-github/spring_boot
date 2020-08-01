`# Project Portal Management`
- A portal which facilitates you with ease, flexibility & robustness for managing your projects.
- A mini-project for the usage & demonstration of spring-boot in an effective way.
- Spring M.V.C is the design pattern most primarily used in this project to start with.
- Also segregation concerns are induced by creating packages for layers like Controller, Service, Data Access Objects(DAO), Entities, Exceptions, Utilities & View Objects(VO)
- Java OOPS concepts as well as the Exception Handling with high level of segregation, modularity & separation of concerns are being implemented.
- Moreover, Spring-Data-JPA usage is also demonstrated in this mini-project.
- Also, 'messages_en_IN.properties' is used for maintaining the common constant message at a place & reusing them in the entire application.
- Logging of messages at various levels(WARN/INFO/ERROR) etc. are also being used using the 'logback.xml'

`# System Requirements`
1) Java 8: You can download it from here: [Java-8 Download Link](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- After successful installation, execute "java -version" on your command prompt which should display the current version of java.

2) Integrated Development Environment for Java development:
- Download the latest Spring tool suite version according to your Operating system available from here: [STS Download Link](https://spring.io/tools)

3) Git: If you don't have git installed, Here's the link: [Download Link for Git](https://git-scm.com/downloads)
- After successful installation, execute "git –version" on your command prompt to check the current installed version.

4) MySQL Installer: [Download link for MySQL Installer](https://dev.mysql.com/downloads/installer/)
- Once you download it, you get to choose the tools/software's you require to download from the dashboard MySQL installer provides you.
- You should then select appropriate settings & get your MySQL Server as well as the MySQL Workbench via the installer.

`# Tools & technology stack required for this projects is mentioned below :-`
1) An External database was used namely: MySQL v5.6(any latest MySQL version will do)
2) JDK 1.8 and JRE 1.8 above required at minimal.
3) Spring tool suite(STS)/Eclipse IDE will be helpful for the project setup & development.
4) POSTMAN API client is used to invoke the REST API end-points that are created in this project.
5) MySQL workbench v6.3 & above will help you to view the database schema, tables, data & perform any other operations.
6) Spring boot framework is used primarily fpr all API development which brings Spring Data/JPA in picture as well.
7) Embedded Apache tomcat server is used which is special feature provided by Spring boot itself.
8) Using Embedded Server avoids the need of adding a server explicitly, wherein we all had to externally download it & add it earlier.

`# Few Helpful Tips`
1) In file: 'application.properties' the value for the property: spring.jpa.hibernate.ddl-auto = "create" for the first time
2) The above mentioned tip is just for the creation of tables in your database. Later you can change the value from "create" -> "update"
3) If the above mentioned property is not updated, it will result in creating new tables in database each time you start your server.
4) Also, view pom.xml for more detailed documentation & explanation regarding all used dependencies.
5) Use Postman API client for invoking the various end-points created in this project.

`# Usage`
Modify the configuration settings to suit your preferences as per your needs & requirements.

`# Contributing`
- Pull requests are welcome.
- For major changes, please open an issue first to discuss what you would like to change.
- Also, Please ensure to update tests appropriately.

`# LICENSE:`  
[MIT LICENSE](https://choosealicense.com/licenses/mit/)
