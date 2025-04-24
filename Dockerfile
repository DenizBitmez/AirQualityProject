FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# Dockerfile for React (Frontend)
FROM node:16

WORKDIR /app
COPY . /app

RUN npm install
RUN npm run build

CMD ["npm", "start"]

EXPOSE 3000