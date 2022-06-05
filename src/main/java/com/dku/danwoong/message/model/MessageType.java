package com.dku.danwoong.message.model;

public enum MessageType {
    WELCOME,
    SAVE_RECORD,
    LOOKUP_RECORD;

    public static MessageType from(String intentType) {
        return MessageType.valueOf(intentType.toUpperCase());
    }
}
