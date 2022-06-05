package com.dku.danwoong.dialogflow.Intent;

public enum IntentType {
    SAVE_RECORD,
    LOOKUP_RECORD;

    public static IntentType from(String intentType) {
        return IntentType.valueOf(intentType.toUpperCase());
    }
}
