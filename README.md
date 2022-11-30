# sidekick-demo-todo-app-java

## Introduction
Sidekick is a remote debugger tool for Java, Spring Boot Application in a non-blocking way. Log point can be attached in the ebugger using moustache expressions. Similarly, tracepoints can be attached to the code. Tracepoints act as debug breakpoint in a non-blocking way. Logpoints act as logger all without restarting the server.

 Install the Sidekick plugin in Intellij IDE from marketplce.
 
 For more refer to the below URL:
 
 ```
 https://docs.runsidekick.com/
 ```



## Running todo app with sidekick agent locally
Todo app is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:

```

git clone https://github.com/runsidekick/sidekick-demo-todo-java-app.git
cd sidekick-demo-todo-app-java
./mvnw package
```
| WARNING: Docker must be installed and running to run tests! <br/>You can skip tests running ```./mvnw package -DskipTests=true```command |
|-------------------------------------------------------------------------------------------------------------------------------------|
```
java -javaagent:sidekick-agent-bootstrap.jar -Dsidekick.apikey=<YOUR-API-KEY> -Dsidekick.agent.application.name=sidekick-demo-todo-app-java -Dsidekick.agent.application.stage=demo -Dsidekick.agent.application.version=1.0.0 -jar target/*.jar
```

Once done, go to the IDE, attach the application and then in the code put the logpoints/tracepoints.
You can then access todo app here: http://localhost:8080/
