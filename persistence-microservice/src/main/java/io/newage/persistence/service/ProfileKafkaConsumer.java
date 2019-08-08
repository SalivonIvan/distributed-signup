package io.newage.persistence.service;

import io.newage.persistence.domain.Profile;
import io.newage.persistence.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProfileKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(ProfileKafkaConsumer.class);
    private static final String TOPIC = "topic_signup";
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileKafkaConsumer(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @KafkaListener(topics = TOPIC, groupId = "persistence-mic")
    public void consume(Profile profile) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, profile);
        profileRepository.save(profile);
    }
}
