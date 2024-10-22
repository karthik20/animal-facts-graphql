FROM azul/zulu-openjdk-alpine:21
RUN mkdir /opt/app 
COPY final/libs/*SNAPSHOT.jar /opt/app/app.jar
RUN <<EOF
ls /opt/app
EOF
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]