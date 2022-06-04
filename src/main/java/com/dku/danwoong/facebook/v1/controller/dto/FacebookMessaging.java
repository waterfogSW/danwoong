package com.dku.danwoong.facebook.v1.controller.dto;

import java.util.Map;

public record FacebookMessaging(
        Map<String, String> sender,
        Map<String, String> recipient,
        Long timestamp,
        FacebookMessage message
) {
}
