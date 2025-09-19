package com.cloud_kafka_notification.notification.repository;

import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.event.NotificationType;
import java.util.Optional;
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
}
