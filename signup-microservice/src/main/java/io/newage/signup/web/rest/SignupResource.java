package io.newage.signup.web.rest;

import io.newage.signup.service.SignupKafkaProducer;
import io.newage.signup.web.rest.model.SignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/signup")
public class SignupResource {

    private SignupKafkaProducer kafkaProducer;

    public SignupResource(SignupKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public void signup(@Valid @RequestBody SignupRequest request) {
        log.debug("REST request to send to Kafka topic the message : {}", request);
        this.kafkaProducer.sendMessage(request);
    }
}
