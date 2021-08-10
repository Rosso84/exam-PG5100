FROM adoptopenjdk/openjdk11:alpine

COPY target/my-trip-advisor-exec.jar .


CMD java -jar my-trip-advisor-exec.jar \
    --spring.datasource.username="postgres"  \
    --spring.datasource.password  \
    --spring.datasource.url="jdbc:postgresql://postgresql-host:5432/postgres"