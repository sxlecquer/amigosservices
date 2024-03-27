package com.example.customer.kafka;

import com.example.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate; // the value is of type Object that allows to create and use more produceXXX() methods in the future

    @Value("${topics.names.notification}")
    private String notificationTopic;

    public void produceNotification(NotificationRequest notification) {
        log.info("Publishing to '{}' topic. Payload: {}", notificationTopic, notification);

        Message<NotificationRequest> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, notificationTopic)
                .build();
        kafkaTemplate.send(message);

        log.info("Published to '{}' topic. Payload: {}", notificationTopic, notification);
    }
}
