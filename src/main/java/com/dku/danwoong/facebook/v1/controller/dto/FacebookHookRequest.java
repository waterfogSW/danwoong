package com.dku.danwoong.facebook.v1.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public record FacebookHookRequest(
        String object,
        List<FacebookEntry> entry
) {
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("object", object)
                .append("entry", entry)
                .toString();
    }
}
