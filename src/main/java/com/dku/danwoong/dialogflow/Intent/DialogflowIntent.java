package com.dku.danwoong.dialogflow.Intent;

public abstract class DialogflowIntent {
    private final IntentType intentType;

    public DialogflowIntent(IntentType intentType) {
        this.intentType = intentType;
    }

    public IntentType getIntentType() {
        return intentType;
    }
}
