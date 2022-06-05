package com.dku.danwoong.dialogflow.service;

import com.dku.danwoong.dialogflow.model.DialogflowIntent;

public interface DialogflowService {
    DialogflowIntent getIntent(String text);
}
