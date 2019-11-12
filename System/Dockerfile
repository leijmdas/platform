FROM anapsix/alpine-java

 ARG JAR_FILE

 ADD target/${JAR_FILE} app.jar

 EXPOSE 89

 ENTRYPOINT ["java","-jar","/app.jar"]