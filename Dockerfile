FROM openjdk:8
ADD target/telegramBot.jar telegramBot.jar
EXPOSE 8081
CMD java -jar telegramBot.jar