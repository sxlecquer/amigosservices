package com.example.notification.kafka;

import com.example.clients.notification.NotificationRequest;
import com.example.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @Value("${topics.names.notification}")
    private String topic;

    @KafkaListener(topics = "${topics.names.notification}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(NotificationRequest notificationRequest) {
        log.info("Notification data consumed from '{}' topic: {}", topic, notificationRequest);
        notificationService.send(notificationRequest);
    }
}

