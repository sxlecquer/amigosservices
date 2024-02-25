package com.example.notification;

import com.example.amqp.RabbitMQProducer;
import com.example.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.example.amqp", "com.example.notification"})
@EnableDiscoveryClient
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RabbitMQProducer producer, NotificationConfig config) {
        return args -> producer.publish(
                config.getInternalTopicExchange(),
                config.getInternalNotificationRoutingKey(),
                new Person("Yan", 18)
        );
    }

    public record Person(String name, int age) {
    }
}
