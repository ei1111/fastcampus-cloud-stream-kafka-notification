package com.cloud_kafka_notification.notification.service;

import com.cloud_kafka_notification.notification.dto.GetUserNotificationByPivotResult;
import com.cloud_kafka_notification.notification.event.Notification;
import com.cloud_kafka_notification.notification.repository.NotificationRepository;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationListService {

    private final NotificationRepository repository;
    private static final PageRequest PAGE = PageRequest.of(0, 20);

    //목록 조회: Pivot 방식(기준점: occuredAt) vs Paging 방식
    //Pivot 방식 -> 보통 시간을 기준점으로 이후에 데이터를 가져오는 방식(페이징은 새로운 데이터가 들어오면 순서보장이 안된다.)
    //(sns처럼 실시간 방식은 paging이 잘 안쓰임 실시간으로 데이터가 저장되면 순서가 변경되기 때문, paing 방식보다 성능이 좋다.)
    //Paging 방식 -> 페이지 단위로 데이터를 가져 오는거(데이터 갯수, 페이 )
    public GetUserNotificationByPivotResult getUserNotificationsByPivot(long userId, Instant occurredAt) {
        //occurredAt이 없는 경우 -> 처음 목록을 조회할 때
        //알림이벤트 최신순으로  정렬
        Slice<Notification> slice = Optional.ofNullable(occurredAt)
                .map(pivot -> repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, pivot, PAGE))
                .orElseGet(() -> repository.findAllByUserIdOrderByOccurredAtDesc(userId, PAGE));

        return  GetUserNotificationByPivotResult.from(slice);
    }
}
