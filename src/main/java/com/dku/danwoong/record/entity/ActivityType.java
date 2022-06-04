package com.dku.danwoong.record.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ActivityType {
    STUDY("공부"),
    WORKOUT("운동");

    private final String name;

    ActivityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static ActivityType from(String intentType) {
        return ActivityType.valueOf(intentType.toUpperCase());
    }
}
