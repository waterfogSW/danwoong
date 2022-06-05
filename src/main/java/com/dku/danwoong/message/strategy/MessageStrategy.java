package com.dku.danwoong.message.strategy;

import com.google.cloud.dialogflow.v2.QueryResult;

public interface MessageStrategy {
    String doOperation(Long userId, QueryResult queryResult);
}
