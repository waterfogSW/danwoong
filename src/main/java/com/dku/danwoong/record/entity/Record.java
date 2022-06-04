package com.dku.danwoong.record.entity;

import lombok.Builder;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Record {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    public Record(String id, LocalDateTime startTime, LocalDateTime endTime, ActivityType activityType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
    }

    public Record() {

    }
}
