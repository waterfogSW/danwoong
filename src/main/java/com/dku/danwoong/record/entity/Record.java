package com.dku.danwoong.record.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Record {
    @Id
    @NotNull
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

    public Record() {/*no-op*/}
}
