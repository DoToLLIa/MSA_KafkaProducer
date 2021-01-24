package ru.neoflex.msa.training.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@RestController
public class KafkaProducerController {

    @Value("${application.bankAccountGenerator.url}")
    private String BANK_ACCOUNT_GENERATOR_URL;

    @Autowired
    private KafkaTemplate<String, BankAccount> kafkaTemplate;

    @GetMapping
    public void sendToTopic() {
        RestTemplate restTemplate = new RestTemplate();
        BankAccount bankAccount = restTemplate.getForObject("http://" + BANK_ACCOUNT_GENERATOR_URL, BankAccount.class);
        System.out.println(bankAccount);
        String msgId = UUID.randomUUID().toString();
        kafkaTemplate.send("bankAccountTopic", msgId, bankAccount);
    }
}
