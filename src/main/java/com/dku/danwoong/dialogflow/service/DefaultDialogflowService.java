package com.dku.danwoong.dialogflow.service;

import com.dku.danwoong.dialogflow.utils.IntentTextDetector;
import com.google.cloud.dialogflow.v2.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultDialogflowService implements DialogflowService {

    private static final Logger log = LoggerFactory.getLogger(DefaultDialogflowService.class);
    private final IntentTextDetector intentTextDetector;

    @Value("${dialogflow-application.project-id}")
    private String projectId;
    @Value("${dialogflow-application.session-id}")
    private String sessionId;
    @Value("${dialogflow-application.language-code}")
    private String languageCode;

    public DefaultDialogflowService(IntentTextDetector intentTextDetector) {
        this.intentTextDetector = intentTextDetector;
    }

    @Override
    public QueryResult query(String text) {
        try {
            return intentTextDetector.detect(projectId, text, sessionId, languageCode);
        } catch (IOException e) {
            log.info("Dialogflow error: {}", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
