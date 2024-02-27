package com.exampe.kafka;

import com.example.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topics.names.notification}")
    private String notificationTopic;

    public void produceNotification(NotificationRequest notification) {
        log.info("Publishing to {} topic. Payload: {}", notificationTopic, notification);
        kafkaTemplate.send(notificationTopic, notification);
        log.info("Published to {} topic. Payload: {}", notificationTopic, notification);
    }
}
