package com.dku.danwoong.facebook.v1.controller.dto;

public record FacebookMessage(
        String mid,
        Long seq,
        String text
) {
}
