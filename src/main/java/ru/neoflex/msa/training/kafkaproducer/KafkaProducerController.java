package ru.neoflex.msa.training.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class KafkaProducerController {

    private static final String HOST = "10.20.30.15";
    private static final String PORT = "8080";
    private static final String PATH = "";

    @Autowired
    private KafkaTemplate<String, BankAccount> kafkaTemplate;

    @GetMapping
    public void sendToTopic() {
        RestTemplate restTemplate = new RestTemplate();
        BankAccount bankAccount = restTemplate.getForObject("http://" + HOST + ":" + PORT + PATH  + "/", BankAccount.class);
        System.out.println(bankAccount);
        String msgId = UUID.randomUUID().toString();
        kafkaTemplate.send("bankAccountTopic", msgId, bankAccount);
    }
}
