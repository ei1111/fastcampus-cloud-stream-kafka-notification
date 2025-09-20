package com.cloud_kafka_notification.redis.service;

import com.cloud_kafka_notification.redis.repository.NotificationReadRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LastReadAtService {
    private final NotificationReadRepository repository;

    public Instant setLastReadAt(long userId) {
        return repository.setLastReadAt(userId);
    }

    public Instant getLastReadAt(long userId) {
        return repository.getLastReadAt(userId);
    }
}
