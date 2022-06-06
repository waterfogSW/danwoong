package com.dku.danwoong.message.strategy;

import com.dku.danwoong.record.entity.ActivityType;
import com.dku.danwoong.record.entity.Record;
import com.dku.danwoong.record.service.RecordService;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("SAVE_RECORD")
public class SaveRecordStrategy implements MessageStrategy {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    private static final Logger log = LoggerFactory.getLogger(SaveRecordStrategy.class);

    private final RecordService recordService;

    public SaveRecordStrategy(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public String doOperation(Long userId, QueryResult queryResult) {
        final var fieldsMap = queryResult.getParameters().getFieldsMap();

        final var count = fieldsMap.get("categoryName").getListValue().getValuesCount();

        for (int i = 0; i < count; i++) {
            final var categoryName = fieldsMap.get("categoryName")
                    .getListValue()
                    .getValues(i)
                    .getStringValue();

            final var dateTime = fieldsMap.get("dateTime").getListValue();

            final var startDateTime = LocalDateTime.parse(
                    dateTime.getValues(i)
                            .getStructValue()
                            .getFieldsMap()
                            .get("startDateTime")
                            .getStringValue(), formatter
            );

            final var endDateTime = LocalDateTime.parse(
                    dateTime.getValues(i)
                            .getStructValue()
                            .getFieldsMap()
                            .get("endDateTime")
                            .getStringValue(), formatter
            );

            log.info("UserId : {}, startTime : {}, endTime : {}, ActivityType : {}",
                    userId,
                    startDateTime,
                    endDateTime,
                    categoryName
            );

            final var newRecord = new Record(userId, startDateTime, endDateTime, ActivityType.from(categoryName));
            recordService.save(newRecord);
        }
        return "";
    }
}
