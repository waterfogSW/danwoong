package com.dku.danwoong.message.service;

import com.dku.danwoong.dialogflow.service.DialogflowService;
import com.dku.danwoong.message.model.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
public class DefaultMessageService implements MessageService {

    private final DialogflowService dialogflowService;

    public DefaultMessageService(DialogflowService dialogflowService) {
        this.dialogflowService = dialogflowService;
    }

    @Override
    public String process(String id, String message) {
        Assert.isTrue(isNotEmpty(id), "Id must be provided");
        Assert.isTrue(isNotEmpty(id), "Message must be provided");

        final var queryResult = dialogflowService.query(message);
        final var messageType = MessageType.from(queryResult.getIntent().getDisplayName());

        return queryResult.getFulfillmentText();
    }
}
