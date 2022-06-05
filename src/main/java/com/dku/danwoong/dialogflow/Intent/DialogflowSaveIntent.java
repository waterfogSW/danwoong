package com.dku.danwoong.dialogflow.Intent;

import com.dku.danwoong.record.entity.ActivityType;

import java.time.LocalDateTime;

public class DialogflowSaveIntent extends DialogflowIntent {

    private final ActivityType activityType;

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    public DialogflowSaveIntent(ActivityType activityType, LocalDateTime startTime, LocalDateTime endTime) {
        super(IntentType.SAVE_RECORD);
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
