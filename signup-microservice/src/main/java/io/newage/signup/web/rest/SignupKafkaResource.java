package io.newage.signup.web.rest;

import io.newage.signup.service.SignupKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/signup-kafka")
public class SignupKafkaResource {

    private final Logger log = LoggerFactory.getLogger(SignupKafkaResource.class);

    private SignupKafkaProducer kafkaProducer;

    public SignupKafkaResource(SignupKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
