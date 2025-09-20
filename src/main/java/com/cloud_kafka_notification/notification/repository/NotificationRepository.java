package com.cloud_kafka_notification.notification.repository;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    @Query("{'type': ?0, 'commentId':  ?1}")
    Optional<Notification> findByTypeAndCommentId(NotificationType notificationType, Long commentId);

    @Query("{'type': ?0, 'postId':  ?1}")
    Optional<Notification> findByTypeAndPostId(NotificationType notificationType, Long postId);

    @Query("{'type': ?0, 'userId':  ?1, 'followerId':  ?2}")
    Optional<Notification> findByTypeAndUserIdAndFollowerId(NotificationType notificationType, Long userId, Long followerId);

    //occuredAt이 없는 경우, Sice는 다음페이지의 여부만 알려준다.
    //20개씩 받기로 해서 Pagable 사용
    Slice<Notification> findAllByUserIdOrderByOccurredAtDesc(Long userId, Pageable pageable);

    //occuredAt이 있는 경우
    Slice<Notification> findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(Long userId, Instant occuredAt, Pageable pageable);

    Optional<Notification> findFirstByUserIdOrderByLastUpdateAtDesc(Long userId);
}
