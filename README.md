# Vulnerability_Management
Test project for vulnerability management tool.

### Development Environment
 - Spring boot + Gradle + JPA
 - MySQL + Hibernate
 - Thymeleaf + bootstrap
 - Docker based!!!

### Usage
 - Provides a high efficiency within vulnerability management
 - Perform static analysis of known vulnerabilities, trojans, viruses, malware & other malicious threats scanned by following various scanners : Web,application, server, network devices, system, source code.


##### Dockerfile for Docker-compose
```
FROM openjdk:14-jdk
COPY ./test.jar test.jar
ENTRYPOINT ["java","-jar","test.jar"]
CMD ["./gradlew", "bootRun"]
```
