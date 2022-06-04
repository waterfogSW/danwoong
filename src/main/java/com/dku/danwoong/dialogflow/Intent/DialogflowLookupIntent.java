package com.dku.danwoong.dialogflow.Intent;

import com.dku.danwoong.record.entity.ActivityType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class DialogflowLookupIntent extends DialogflowIntent {
    @Enumerated(EnumType.STRING)
    private final ActivityType activityType;

    private final LocalDate date;

    public DialogflowLookupIntent(ActivityType activityType, LocalDate date) {
        super(IntentType.LOOKUP_RECORD);
        this.activityType = activityType;
        this.date = date;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public LocalDate getDate() {
        return date;
    }
}
