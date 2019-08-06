package io.newage.signup.web.rest;

import io.newage.signup.SignupApp;
import io.newage.signup.service.SignupKafkaProducer;
import io.newage.signup.web.rest.model.SignupRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EmbeddedKafka
@SpringBootTest(classes = SignupApp.class)
public class SignupResourceTest {

    private static final String DEFAULT_PASSWORD = "admin";
    private static final String DEFAULT_EMAIL = "admin@gmail.com";
    private static final String SIGNUP_URL = "/api/signup";

    @Autowired
    private SignupKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @Before
    public void setup() {
        SignupResource kafkaResource = new SignupResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    @SuppressWarnings("squid:S00112")
    public void signupOk() throws Exception {
        restMockMvc.perform(post(SIGNUP_URL)
            .contentType(APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(SignupRequest.builder()
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD)
                .build())))
            .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("squid:S00112")
    public void signupBadRequest() throws Exception {
        restMockMvc.perform(post(SIGNUP_URL)
            .contentType(APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(SignupRequest.builder()
                .email("")
                .password(DEFAULT_PASSWORD)
                .build())))
            .andExpect(status().isBadRequest());

        restMockMvc.perform(post(SIGNUP_URL)
            .contentType(APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(SignupRequest.builder()
                .email(DEFAULT_EMAIL)
                .password("")
                .build())))
            .andExpect(status().isBadRequest());

        restMockMvc.perform(post(SIGNUP_URL)
            .contentType(APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(SignupRequest.builder()
                .email(null)
                .password("")
                .build())))
            .andExpect(status().isBadRequest());
    }
}
