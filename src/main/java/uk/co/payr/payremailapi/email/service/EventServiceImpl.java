package uk.co.payr.payrusersapi.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uk.co.payr.payrusersapi.user.config.KafkaConfigProps;
import uk.co.payr.payrusersapi.user.model.event.NotificationEvent;
import uk.co.payr.payrusersapi.user.model.event.OrderError;
import uk.co.payr.payrusersapi.user.model.UserEvent;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements  EventService {

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void sendOrderError(OrderError orderError) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicOrdersError(), mapper.writeValueAsString(orderError));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotification(NotificationEvent notificationEvent) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicNotification(), mapper.writeValueAsString(notificationEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}