package uk.co.payr.payremailapi.email.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic updateUserTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopicUpdateUser())
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newUserTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopicEmailRegister())
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic notificationNewTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopicNotificationNew())
                .partitions(10)
                .replicas(1)
                .build();
    }
}
