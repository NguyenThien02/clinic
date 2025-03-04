FROM openjdk:17-jdk-slim
COPY target/clinic-0.0.1-SNAPSHOT.jar clinic-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/clinic-0.0.1-SNAPSHOT.jar"]
# Tạo thư mục trong container
RUN mkdir -p /uploads

# Copy thư mục ảnh vào container
COPY uploads /uploads
