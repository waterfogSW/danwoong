package com.dku.danwoong.message.service;

import com.dku.danwoong.dialogflow.service.DialogflowService;
import com.dku.danwoong.message.model.MessageType;
import com.dku.danwoong.message.strategy.LookupRecordStrategy;
import com.dku.danwoong.message.strategy.SaveRecordStrategy;
import com.dku.danwoong.user.model.Provider;
import com.dku.danwoong.user.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
public class DefaultMessageService implements MessageService {

    private final BeanFactory beanFactory;
    private final DialogflowService dialogflowService;
    private final UserService userService;

    public DefaultMessageService(BeanFactory beanFactory,
                                 DialogflowService dialogflowService,
                                 UserService userService) {
        this.beanFactory = beanFactory;
        this.dialogflowService = dialogflowService;
        this.userService = userService;
    }

    @Override
    public String doOperation(Provider provider, String senderId, String message) {
        Assert.isTrue(isNotEmpty(senderId), "SenderId must be provided");

        if (message == null || message.isBlank()) {
            return "무슨 말씀이신지 잘 모르겠어요";
        }

        final var userId = userService.getUserId(provider, senderId);
        final var queryResult = dialogflowService.query(message);
        final var messageType = MessageType.from(queryResult.getIntent().getDisplayName());

        final var responseText = new StringBuilder().append(queryResult.getFulfillmentText());

        switch (messageType) {
            case SAVE_RECORD -> beanFactory.getBean(messageType.toString(), SaveRecordStrategy.class)
                    .doOperation(userId, queryResult);
            case LOOKUP_RECORD -> {
                final var result = beanFactory.getBean(messageType.toString(), LookupRecordStrategy.class)
                        .doOperation(userId, queryResult);
                responseText.append(result);
            }
        }

        return responseText.toString();
    }
}
