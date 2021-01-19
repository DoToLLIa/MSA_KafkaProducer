package ru.neoflex.msa.training.kafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class Application {

    @KafkaListener(topics = "bankAccountTopic")
    public void msgListener(String msg) {
        System.out.println("Listener: " + msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
