package com.dku.danwoong.user.service;

import com.dku.danwoong.user.model.Provider;
import com.dku.danwoong.user.model.User;
import com.dku.danwoong.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
@Transactional
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long getUserId(Provider provider, String senderId) {
        Assert.notNull(provider, "Provider must be provided");
        Assert.isTrue(isNotEmpty(senderId), "SenderId mus be provided");

        final var foundUser = userRepository.findByProviderAndSenderId(provider, senderId);

        if (foundUser.isEmpty()) {
            final var newUser = new User(senderId, provider);
            final var savedUser = userRepository.save(newUser);
            return savedUser.getId();
        }

        return foundUser.get().getId();
    }
}
