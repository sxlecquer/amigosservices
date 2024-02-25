package com.example.notification.rabbitmq;

import com.example.notification.NotificationApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consume(NotificationApplication.Person person) {
        log.info("Notification data consumed from queue: {}", person);
    }
}
