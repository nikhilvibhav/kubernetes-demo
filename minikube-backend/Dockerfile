# 1st stage of a 3-stage build - Run gradle build/tests and generate JAR
## pull official jdk base image
FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine AS gradle-builder

## copy contents to app/ directory
COPY . app

## set working directory as app/
WORKDIR app

## run the gradle build
RUN ./gradlew build

# 2nd stage of a 3-stage build - extract the layered JAR created by the spring boot gradle plugin
## pull official jre base image
FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine AS extracter

## set working directory
WORKDIR app

## copy jar to the working directory
#ARG JAR_FILE=build/libs/minikube-backend*.jar
#COPY ${JAR_FILE} application.jar
COPY --from=gradle-builder app/build/libs/*.jar application.jar

## extract jar
RUN java -Djarmode=layertools -jar application.jar extract

# 3rd stage of a 3-stage build - copy the JAR extracts to the final docker container
## pull official jre base image
FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine

## set working directory
WORKDIR app

## copy extracted jar directories to the app directory
COPY --from=extracter app/dependencies ./
COPY --from=extracter app/snapshot-dependencies ./
## ignoring this line because the application does not have any additional files under resources directory
#COPY --from=builder app/resources ./
COPY --from=extracter app/application ./
COPY --from=extracter app/spring-boot-loader ./

## Start app
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
