package com.dku.danwoong.dialogflow.service;

import com.dku.danwoong.dialogflow.Intent.DialogflowIntent;

public interface DialogflowService {
    DialogflowIntent getIntent(String text);
}
