package io.newage.signup.service;

import io.newage.signup.web.rest.model.SignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignupKafkaProducer {

    private static final String TOPIC = "topic_signup";

    private KafkaTemplate<String, SignupRequest> kafkaTemplate;

    public SignupKafkaProducer(KafkaTemplate<String, SignupRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(SignupRequest message) {
        log.info("Producing message to {} : {}", TOPIC, message);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
