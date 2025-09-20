package com.cloud_kafka_notification.redis.repository;

import com.cloud_kafka_notification.global.util.InstantDateTime;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationReadRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    //유저의 읽음 시간을 기록하는 함수
    public java.time.Instant setLastReadAt(long userId) {
        String lastReadAt = String.valueOf(InstantDateTime.lastReadAt());
        String key = userId + ":lastReadAt" ;
        redisTemplate.opsForValue().set(key, lastReadAt);
        redisTemplate.expire(key, 90, TimeUnit.DAYS); //TTL
        //Long 타입을 Instant로 변환
        return Instant.ofEpochMilli(Long.parseLong(lastReadAt));
    }
}
