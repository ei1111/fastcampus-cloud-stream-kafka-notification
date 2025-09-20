package com.cloud_kafka_notification.redis.repository;

import com.cloud_kafka_notification.global.util.InstantDateTime;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationReadRepository {
    private final RedisTemplate<String, String> redisTemplate;

    //유저의 읽음 시간을 기록하는 함수
    public Instant setLastReadAt(long userId) {
        String lastReadAt = String.valueOf(InstantDateTime.lastReadAt());
        String key = getKey(userId);
        redisTemplate.opsForValue().set(key, lastReadAt);
        redisTemplate.expire(key, 90, TimeUnit.DAYS); //TTL
        //Long 타입을 Instant로 변환
        return Instant.ofEpochMilli(Long.parseLong(lastReadAt));
    }

    //유저의 읽음 시간을 기록하는 함수
    public Instant getLastReadAt(long userId) {
        String key = getKey(userId);
        String lastReadAtStr = redisTemplate.opsForValue().get(key);

        return Optional.ofNullable(lastReadAtStr)
                .map(lastReadAt -> Instant.ofEpochMilli(Long.parseLong(lastReadAt)))
                .orElse(null);
    }

    private static String getKey(long userId) {
        return userId + ":lastReadAt";
    }
}
