package com.dku.danwoong.message.strategy;

import com.dku.danwoong.calender.service.CalenderService;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class CalenderStrategy implements MessageStrategy {

    private final CalenderService calenderService;

    public CalenderStrategy(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @Override
    public String doOperation(Long userId, QueryResult queryResult) {
        return MessageFormat.format("")
    }
}
