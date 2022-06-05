package com.dku.danwoong.dialogflow.utils;

import com.dku.danwoong.dialogflow.Intent.DialogflowIntent;
import com.dku.danwoong.dialogflow.Intent.DialogflowLookupIntent;
import com.dku.danwoong.dialogflow.Intent.DialogflowSaveIntent;
import com.dku.danwoong.record.entity.ActivityType;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class IntentConverter {

    public DialogflowIntent queryResultToIntent(QueryResult queryResult) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String intentType = queryResult.getIntent().getDisplayName();
        Map<String, Value> fieldsMap = queryResult.getParameters().getFieldsMap();

        ActivityType activityType = ActivityType.from(fieldsMap.get("categoryName").getStringValue());

        switch (intentType) {
            case "SAVE_RECORD" -> {
                LocalDateTime startTime = LocalDateTime.parse(fieldsMap.get("startTime").getStringValue(), formatter);
                LocalDateTime endTime = LocalDateTime.parse(fieldsMap.get("endTime").getStringValue(), formatter);

                return new DialogflowSaveIntent(activityType, startTime, endTime);
            }

            case "LOOKUP_RECORD" -> {
                LocalDate date = LocalDate.parse(fieldsMap.get("date").getStringValue(), formatter);

                return new DialogflowLookupIntent(activityType, date);
            }
            default -> throw new IllegalArgumentException("Invalid intent type");
        }
    }
}
