package com.dku.danwoong.dialogflow.service;

import com.google.cloud.dialogflow.v2.QueryResult;

public interface DialogflowService {
    QueryResult query(String text);
}
