package com.dku.danwoong.dialogflow.service;

import com.dku.danwoong.dialogflow.Intent.DialogflowIntent;
import com.dku.danwoong.dialogflow.utils.DetectIntentTexts;
import com.dku.danwoong.dialogflow.utils.IntentConverter;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultDialogflowService implements DialogflowService {

    private static final Logger log = LoggerFactory.getLogger(DefaultDialogflowService.class);

    private final IntentConverter intentConverter;

    public DefaultDialogflowService(IntentConverter intentConverter) {
        this.intentConverter = intentConverter;
    }

    @Value("${dialogflow-application.project-id}")
    private String projectId;

    @Value("${dialogflow-application.session-id}")
    private String sessionId;

    @Value("${dialogflow-application.language-code}")
    private String languageCode;

    @Override
    public DialogflowIntent getIntent(String text) {
        try {
            QueryResult queryResult = DetectIntentTexts.detectIntentTexts(projectId, text, sessionId, languageCode);
            return intentConverter.queryResultToIntent(queryResult);
        } catch (IOException e) {
            log.info("Dialogflow error: {}", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
