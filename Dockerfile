FROM maven:3.9.9-eclipse-temurin-23

LABEL MAINTAINER = "ALSIFAN"
LABEL description = "This is VTTP SSF day_13 lecture"
LABEL name = "vttp5a_ssf_day_13"

ARG APP_DIR=/APP_DIR

# directory where your source code will reside
# directory where you copy your project to (in the next step)
WORKDIR ${APP_DIR}

#copy the required files and /or directories  into the image
COPY  pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# package the application using the Run directive
# this will download the dependencies defined in pom xml
# compile and package to jar
#RUN chmod a+x ./mvnw
RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

#ENTRYPOINT SERVER_PORT = ${SERVER_PORT} java -jar target/vtpp5a_ssf_day_13-0.0.1-SNAPSHOT.jar


# Use exec form for ENTRYPOINT
ENTRYPOINT ["java", "-jar", "target/vtpp5a_ssf_day_13-0.0.1-SNAPSHOT.jar"]

# Pass the SERVER_PORT environment variable to the application via command-line arguments
CMD ["--server.port=${SERVER_PORT}"]

# docker cmd command
#1.) docker system prune (to clean )
#2.) docker build -t cihansifan/vttp5-ssf-day_13:v0.0.1 .
#3.) docker image ls (to check is it upload in local)
#4.) docker container ls 
#5.) docker run -p 8085:3000 cihansifan/vttp5-ssf-day_13:v0.0.1
