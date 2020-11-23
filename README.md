# International Airports

With this web application we can search for every airline or airport from whole World. 

----------------------------------------------------
 
 This app was build with MAVEN which is a great tool for management.
 Also, all data was handled by Spring Framework, Spring Security, in usage with Thymeleaf engine.
 
 Vizit the below link for more information about Spring + Thymeleaf.
https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html
     
Build the Project
--------
 
 To build this project you will need Maven. You can get it at:
 
     http://maven.apache.org
     
1. Change directory to your project root folder in command line.

2. Clean compilation products:     
 
        mvn clean
     
3. If you are looking to package the project, then you should run:     
     
        mvn package
     
4. Compile:
 
        mvn compile
     
5. Execute:
 
        mvn exec:java -Dexec.mainClass=com.international.airports.AirportsApplication

 Once started, the application should be available at:
 
     http://localhost:1234/app/
    