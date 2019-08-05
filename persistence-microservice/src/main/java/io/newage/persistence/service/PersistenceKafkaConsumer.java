package io.newage.persistence.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PersistenceKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(PersistenceKafkaConsumer.class);
    private static final String TOPIC = "topic_persistence";

    @KafkaListener(topics = "topic_persistence", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
