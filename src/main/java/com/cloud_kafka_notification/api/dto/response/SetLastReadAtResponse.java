package com.cloud_kafka_notification.api.dto.response;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class SetLastReadAtResponse {
    private Instant lastReadAt;

}
