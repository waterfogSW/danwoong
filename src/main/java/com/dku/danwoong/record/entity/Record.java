package com.dku.danwoong.record.entity;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    protected Record() {/*no-op*/}

    public Record(Long userId, LocalDateTime startTime, LocalDateTime endTime, ActivityType activityType) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
    }

    public long getDuration() {
        return Duration.between(startTime, endTime).toMinutes();
    }
}
