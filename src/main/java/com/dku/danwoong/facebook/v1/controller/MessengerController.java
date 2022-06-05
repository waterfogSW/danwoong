package com.dku.danwoong.facebook.v1.controller;

import com.dku.danwoong.facebook.v1.controller.dto.FacebookHookRequest;
import com.dku.danwoong.facebook.v1.controller.dto.FacebookMessageResponse;
import com.dku.danwoong.message.service.MessageService;
import com.dku.danwoong.user.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("api/v1/webhook")
public class MessengerController {

    private static final Logger log = LoggerFactory.getLogger(MessengerController.class);

    private final RestTemplate template = new RestTemplate();

    private final MessageService messageService;

    public MessengerController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Value("${facebook-application.message-url}")
    private String messageUrl;

    @Value("${facebook-application.verify-token}")
    private String verifyToken;

    @Value("${facebook-application.access-token}")
    private String accessToken;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String registerWebhook(
            @NotBlank @RequestParam(name = "hub.verify_token") String token,
            @NotBlank @RequestParam(name = "hub.challenge") String challenge,
            @NotBlank @RequestParam(name = "hub.mode") String mode) {
        Assert.isTrue(token.equals(verifyToken), "Invalid verify token");

        log.info("Facebook Webhook register -> Token : {}, Challenge : {}, Mode : {}", token, challenge, mode);

        return challenge;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void post(@NotNull @RequestBody FacebookHookRequest request) {
        Assert.notNull(request, "Request must be provided");

        log.info("Message from chat: {}", request);

        request.entry().forEach(e -> e.messaging().forEach(m -> {
            String id = m.sender().get("id");
            String content = m.message().text();
            String result = messageService.doOperation(Provider.FACEBOOK, id, content);
            sendReply(id, result);
        }));
    }

    private void sendReply(String id, String text) {
        final var response = new FacebookMessageResponse("text");
        response.recipient().put("id", id);
        response.message().put("text", text);
        final var entity = new HttpEntity<>(response);

        String result = template.postForEntity(messageUrl + accessToken, entity, String.class).getBody();
        log.info("Message result: {}", result);
    }
}
