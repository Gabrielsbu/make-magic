FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /usr/src/app
COPY "./make-magic-0.0.1-SNAPSHOT.jar" .
ENTRYPOINT ["java", "-jar", "make-magic-0.0.1-SNAPSHOT.jar"]