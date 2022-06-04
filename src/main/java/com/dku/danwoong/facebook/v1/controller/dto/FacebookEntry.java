package com.dku.danwoong.facebook.v1.controller.dto;

import java.util.List;

public record FacebookEntry(
        String id,
        Long time,
        List<FacebookMessaging> messaging
) {
}
