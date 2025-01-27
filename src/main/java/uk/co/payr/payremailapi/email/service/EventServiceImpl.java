package uk.co.payr.payremailapi.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uk.co.payr.payremailapi.email.config.KafkaConfigProps;
import uk.co.payr.payremailapi.email.model.NotificationNewEvent;
import uk.co.payr.payremailapi.email.model.UserUpdateEvent;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void sendUserUpdate(UserUpdateEvent userUpdateEvent) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicUpdateUser(), mapper.writeValueAsString(userUpdateEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotificationNew(NotificationNewEvent notificationNewEvent) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicNotificationNew(), mapper.writeValueAsString(notificationNewEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}