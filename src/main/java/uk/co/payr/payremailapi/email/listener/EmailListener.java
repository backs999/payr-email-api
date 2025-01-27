package uk.co.payr.payremailapi.email.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import uk.co.payr.payremailapi.api.UserControllerApi;
import uk.co.payr.payremailapi.email.model.EmailNewUserEvent;
import uk.co.payr.payremailapi.email.service.EmailService;
import uk.co.payr.payremailapi.email.service.EventService;
import uk.co.payr.payremailapi.invoker.ApiException;

@Component
@RequiredArgsConstructor
@Flogger
public class EmailListener {

    private final ObjectMapper mapper;
    private final EmailService emailService;

    @KafkaListener(topics = "${payr.kafka.topic-email-register}", groupId = "payr-email-api")
    public void listens(final String incomingEmailEvent) {
        try {
            final var event = mapper.readValue(incomingEmailEvent, EmailNewUserEvent.class);
            log.atInfo().log("Email new user received for " + event.getUserId());
            // send email
            emailService.send(event.getUserId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}