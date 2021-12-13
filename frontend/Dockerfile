FROM adoptopenjdk/openjdk11:alpine

COPY target/tripforum-exec.jar .


CMD java -jar tripforum-exec.jar \
    --spring.datasource.username="postgres"  \
    --spring.datasource.password  \
    --spring.datasource.url="jdbc:postgresql://postgresql-host:5432/postgres"