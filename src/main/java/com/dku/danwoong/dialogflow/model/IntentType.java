package com.dku.danwoong.dialogflow.model;

public enum IntentType {
    SAVE_RECORD,
    LOOKUP_RECORD;

    public static IntentType from(String intentType) {
        return IntentType.valueOf(intentType.toUpperCase());
    }
}
