package com.cloud_kafka_notification.global.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class InstantDateTime {

    public static Instant now() {
        return Instant.now();
    }

    public static Instant deleteDateTime() {
        return Instant.now().plus(90, ChronoUnit.DAYS);
    }

    public static long lastReadAt() {
        return Instant.now().toEpochMilli();
    }
}
