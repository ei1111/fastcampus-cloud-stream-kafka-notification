package com.cloud_kafka_notification.global.util;

import org.bson.types.ObjectId;

public class NotificationIdGenerator {
    public static String generate() {
        //MongoDB에서 만들어주는 ID 값이고 유니크를 보장한다.
        return new ObjectId().toString();
    }
}
