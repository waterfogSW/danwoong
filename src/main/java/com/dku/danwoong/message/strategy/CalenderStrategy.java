package com.dku.danwoong.message.strategy;

import com.dku.danwoong.calender.entity.CalendarType;
import com.dku.danwoong.calender.service.CalenderService;
import com.dku.danwoong.record.entity.ActivityType;
import com.dku.danwoong.record.entity.Record;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("CALENDER")
public class CalenderStrategy implements MessageStrategy {

    private final CalenderService calenderService;

    public CalenderStrategy(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @Override
    public String doOperation(Long userId, QueryResult queryResult) {
        final var fieldsMap = queryResult.getParameters().getFieldsMap();

        final var calenderType = CalendarType.from(fieldsMap.get("calenderName").getStringValue());

        final var date = calenderService.findByName(calenderType.getName()).getDate();

        return MessageFormat.format(
                "{0} 날짜는 {1} 입니다",
                calenderType,
                date
        );
    }
}
