FROM openjdk:8
COPY target/Vaccine-Management-0.0.1-SNAPSHOT.jar vaccine-management.jar
ENTRYPOINT ["java","-jar","/vaccine-management.jar"]
EXPOSE 587