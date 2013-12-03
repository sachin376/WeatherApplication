Below are the steps to run the application :

1: checkout the project in ${some_dir} from git hub repo - https://github.com/sachin376/WeatherApplication.git
2: Go to terminal
3: cd ${some_dir}/WeatherApplication
4: mvn clean install
5: tomcat:run
6: Open the browser and hit : http://localhost:8080/WeatherApplication


In case above URL won't work pls go through the below steps
****************
Step 1
****************
First or add an user with administrator access right for Tomcat. To add Tomcat user, edit this file
%TOMCAT_PATH%/conf/tomcat-users.xml
Assuming  user name:admin and password:admin

****************
Step 2
****************
In Maven side, you need to add the same user authentication information in “%MAVEN_PATH%/conf/settings.xml“
<server>
	<id>tomcat</id>
	<username>admin</username>
	<password>admin</password>
</server>

****************
Step 3
****************
Declare the tomcat server name in the pom.xml file same as the tomcat server id mentioned in step 2 of mvn settings.
<plugin>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>tomcat-maven-plugin</artifactId>
	<configuration>
	<url>http://127.0.0.1:8080/manager/html</url>
	<server>tomcat</server>
	</configuration>
</plugin>

*****************************Technology stack in the project********************************
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


