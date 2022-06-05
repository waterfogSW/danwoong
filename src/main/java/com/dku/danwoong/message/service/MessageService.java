package com.dku.danwoong.message.service;

import com.dku.danwoong.user.model.Provider;

public interface MessageService {
    String doOperation(Provider provider, String id, String message);
}
