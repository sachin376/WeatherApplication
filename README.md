Prerequisite
Git, maven and tomcat should be installed on your machine.


Step to run the application

1 Open terminal
2 Make dir to checkout the code :  $(some_dir)
3 Go inside the dir :
            cd $(some_dir)
4 Checkout the project in this dir from git hub repo (https://github.com/sachin376/WeatherApplication.git):
            git init
            git pull https://github.com/sachin376/WeatherApplication.git
5 Build and run the test cases :
            mvn clean install
6 Run the application :
            mvn tomcat:run
7 Open the browser and hit : http://localhost:8080/WeatherApplication

***********************************************************************************

In case above URL won't work please go through the below steps
*******
Step 1
*******
First or add an user with administrator access right for Tomcat. To add Tomcat user, edit this file
%TOMCAT_PATH%/conf/tomcat-users.xml
Assuming  user name:admin and password:admin

*******
Step 2
*******
In Maven side, you need to add the same user authentication information in “%MAVEN_PATH%/conf/settings.xml“
<server>
    <id>tomcat</id>
    <username>admin</username>
    <password>admin</password>
</server>

*******
Step 3
*******
Declare the tomcat server name in the pom.xml file same as the tomcat server id mentioned in step 2 of mvn settings.
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>tomcat-maven-plugin</artifactId>
    <configuration>
    <url>http://127.0.0.1:8080/manager/html</url>
    <server>tomcat</server>
    </configuration>
</plugin>

**********Technology stack in the project**************
Spring MVC
Spring AOP

Spring REST client
jackson for REST client

spring test
junit
mockito

jquery client side validaion
slf4j over log4j12 for logging
Maven for build

maven-compiler-plugin
tomcat-maven-plugin
