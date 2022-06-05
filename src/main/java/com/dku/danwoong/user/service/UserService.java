package com.dku.danwoong.user.service;

import com.dku.danwoong.user.model.Provider;

public interface UserService {
    Long getUserId(Provider provider, String senderId);
}
