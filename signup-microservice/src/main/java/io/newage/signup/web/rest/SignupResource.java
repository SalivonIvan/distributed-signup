package io.newage.signup.web.rest;

import io.newage.signup.service.SignupKafkaProducer;
import io.newage.signup.web.rest.model.SignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/signup")
public class SignupResource {

    private SignupKafkaProducer kafkaProducer;

    public SignupResource(SignupKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {
        log.debug("REST request to send to Kafka topic the message : {}", request);
        request.setUuid(UUID.randomUUID().toString());
        this.kafkaProducer.sendMessage(request);
        return ResponseEntity.ok(request.getUuid());
    }
}
