package com.dku.danwoong.dialogflow.model;

import com.dku.danwoong.record.entity.ActivityType;
import org.springframework.util.Assert;

import java.time.LocalDate;

public class DialogflowLookupIntent extends DialogflowIntent {

    private final ActivityType activityType;

    private final LocalDate date;

    public DialogflowLookupIntent(ActivityType activityType, LocalDate date) {
        super(IntentType.LOOKUP_RECORD);

        Assert.notNull(activityType, "ActivityType must be provided");
        Assert.notNull(date, "Date must be provided");

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
