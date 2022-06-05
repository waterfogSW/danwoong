package com.dku.danwoong.dialogflow.model;

import com.dku.danwoong.record.entity.ActivityType;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class DialogflowSaveIntent extends DialogflowIntent {

    private final ActivityType activityType;

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    public DialogflowSaveIntent(ActivityType activityType, LocalDateTime startTime, LocalDateTime endTime) {
        super(IntentType.SAVE_RECORD);

        Assert.notNull(activityType, "ActivityType must be provided");
        Assert.notNull(startTime, "StartTime must be provided");
        Assert.notNull(endTime, "EndTime must be provided");

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
