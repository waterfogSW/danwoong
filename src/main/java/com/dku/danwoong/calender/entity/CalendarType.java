package com.dku.danwoong.calender.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CalendarType {
    MIDTERM("중간고사"),
    FINAL("기말고사"),
    SIGNUP("수강신청"),
    BEGINNING("개강"),
    END("종강");

    private final String name;

    CalendarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static CalendarType from(String intentType) {
        return CalendarType.valueOf(intentType.toUpperCase());
    }
}
