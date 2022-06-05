package com.dku.danwoong.dialogflow.utils;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Component
public class IntentTextDetector {

    public QueryResult detect(
            String projectId, String text, String sessionId, String languageCode)
            throws IOException, ApiException {

        Assert.isTrue(isNotEmpty(projectId), "ProjectId must be provided");
        Assert.isTrue(isNotEmpty(text), "Text must be provided");
        Assert.isTrue(isNotEmpty(sessionId), "SessionId must be provided");
        Assert.isTrue(isNotEmpty(languageCode), "LanguageCode must be provided");

        // Instantiates a client
        try (final var sessionsClient = SessionsClient.create()) {
            // Set the session nam
            // e using the sessionId (UUID) and projectID (my-project-id)
            final var session = SessionName.of(projectId, sessionId);

            // Set the text (hello) and language code (en-US) for the query
            final var textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

            // Build the query with the TextInput
            final var queryInput = QueryInput.newBuilder().setText(textInput).build();

            // Performs the detect intent request
            final var response = sessionsClient.detectIntent(session, queryInput);

            return response.getQueryResult();
        }
    }
}
