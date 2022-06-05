package com.dku.danwoong.message.strategy;

import com.dku.danwoong.record.entity.ActivityType;
import com.dku.danwoong.record.entity.Record;
import com.dku.danwoong.record.service.RecordService;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("SAVE_RECORD")
public class SaveRecordStrategy implements MessageStrategy {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    private final RecordService recordService;

    public SaveRecordStrategy(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public String doOperation(Long userId, QueryResult queryResult) {
        final var fieldsMap = queryResult.getParameters().getFieldsMap();

        final var startTime = LocalDateTime.parse(fieldsMap.get("startTime").getStringValue(), formatter);
        final var endTime = LocalDateTime.parse(fieldsMap.get("endTime").getStringValue(), formatter);
        final var activityType = ActivityType.from(fieldsMap.get("categoryName").getStringValue());

        final var newRecord = new Record(userId, startTime, endTime, activityType);
        recordService.save(newRecord);
        return "";
    }
}
