package com.dku.danwoong.message.strategy;

import com.dku.danwoong.record.entity.ActivityType;
import com.dku.danwoong.record.entity.Record;
import com.dku.danwoong.record.repository.RecordRepository;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.MessageFormat;

@Component("LOOKUP_RECORD")
public class LookupRecordStrategy implements MessageStrategy {

    private static final DecimalFormat df = new DecimalFormat("#.##");

    private final RecordRepository recordRepository;

    public LookupRecordStrategy(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public String doOperation(Long userId, QueryResult queryResult) {
        final var fieldsMap = queryResult.getParameters().getFieldsMap();

        final var activityType = ActivityType.from(fieldsMap.get("categoryName").getStringValue());

        final var averageTime = recordRepository.findAllByUserIdAndActivityType(userId, activityType)
                .stream()
                .map(Record::getDuration)
                .mapToLong(i -> i)
                .average()
                .orElse(0);

        return MessageFormat.format(
                "평균 {0} 시간은 {1}분 입니다",
                activityType.getName(),
                df.format(averageTime)
        );
    }
}
