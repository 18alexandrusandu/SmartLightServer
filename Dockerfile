FROM openjdk:17

COPY target/devices-0.0.1-SNAPSHOT.jar app.jar
COPY save_devices.json save_devices.json
ENTRYPOINT ["java","-jar","/app.jar"]
