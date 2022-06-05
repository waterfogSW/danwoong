package com.dku.danwoong.dialogflow.model;

import org.springframework.util.Assert;

public abstract class DialogflowIntent {
    private final IntentType intentType;

    public DialogflowIntent(IntentType intentType) {
        Assert.notNull(intentType, "IntentType must be provided");
        this.intentType = intentType;
    }

    public IntentType getIntentType() {
        return intentType;
    }
}
