How to build?
--------
Run `gradle build`

Find the executable jar file in `build/libs`

How to test
----
Run `gradle test`
To get jacoco code coverage report, 
run `gradle jacocoTestReport`
then `open build/jacoco/test/html/index.html` 
to open jacoco test report

How to run
------
Run `gradle bootRun`
Invoke api using endpoints found in swagger documentation http://localhost:8080/swagger-ui.html


Actuator endpoints
----
http://localhost:8090/actuator/
