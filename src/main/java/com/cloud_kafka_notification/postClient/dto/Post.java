package com.cloud_kafka_notification.postClient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Post {

    private Long id;
    private Long userId;
    private String imgUrl;
    private String content;

}
