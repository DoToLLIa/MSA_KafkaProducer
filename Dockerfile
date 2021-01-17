FROM dotollia/msa_basedockerimage:0.3
ARG PROJECT_VERSION=0.3
RUN mkdir /MSA_KafkaProducer
COPY . /MSA_KafkaProducer
RUN cd /MSA_KafkaProducer && \
    mvn clean package &&  \
    mv /MSA_KafkaProducer/target/KafkaProducer-${PROJECT_VERSION}.jar /KafkaProducer.jar && \
    rm -r /MSA_KafkaProducer
EXPOSE 8080
CMD ["java", "-jar", "/KafkaProducer.jar"]