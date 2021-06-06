# My first dockerfile O.o it's probably really shitty
FROM openjdk:11-jdk
MAINTAINER joao.santos.ismart@gmail.com

#/home/lucassantos/Documents/Creditas/Self-Learning/DnA_Self_Learning/pokeDex/build/libs/pokeDex-0.0.1-SNAPSHOT.jar
COPY build/libs/pokeDex-0.0.1-SNAPSHOT.jar /application.jar
CMD ["java", "-jar", "/application.jar"]

