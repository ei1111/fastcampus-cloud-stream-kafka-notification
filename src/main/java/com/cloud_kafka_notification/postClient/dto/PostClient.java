package com.cloud_kafka_notification.postClient.dto;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PostClient {
    private final Map<Long, Post> posts = new HashMap<>();

    public PostClient() {
        posts.put(1L, new Post(1L,111L, "imag1", "content1"));
        posts.put(2L, new Post(2L,222L, "imag2", "content2"));
        posts.put(3L, new Post(3L,333L, "imag3", "content3"));
    }

    public Post getPost(Long postId) {
        return posts.get(postId);
    }
}
