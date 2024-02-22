package com.example.notification.service;

import com.example.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(NotificationRepository notificationRepository) {
}
