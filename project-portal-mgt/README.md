Project Portal Management
A portal which facilitates you with ease, flexibility & robustness for managing your projects.

# Tools & technology stack required for this projects is mentioned below :-
1) An External database was used namely: MySQL v5.6(any latest MySQL version will do)
2) JDK 1.8 and JRE 1.8 above required at minimal.
3) STS/Eclipse IDE will be helpful for the project setup & development.
4) POSTMAN API client is used to invoke the REST API end-points that are created in this project.
5) MySQL workbench v6.3 & above will help you to view the database schema, tables, data & perform any other operations.
6) Spring boot framework is used primarily fpr all API development which brings Spring Data/JPA in picture as well.
7) Embedded Apache tomcat server is used which is special feature provided by Spring boot itself.
8) Using Embedded Server avoids the need of adding a server explicitly & externally which makes development more easier.

# Few Helpful Tips
In file: 'application.properties' the value for the property: spring.jpa.hibernate.ddl-auto = "create" for the first time
The above mentioned tip is just for the creation of tables in your database. Later you can change the value from "create" -> "update"
If the above mentioned property is not updated, it will result in creating new tables in database each time you start your server.
Also view pom.xml for more detailed documentation & explanation regarding all used dependencies.
Use Postman API client for invoking the various end-points created in this project.

# License: [MIT](https://choosealicense.com/licenses/mit/)
