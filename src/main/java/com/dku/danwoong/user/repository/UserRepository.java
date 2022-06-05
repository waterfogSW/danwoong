package com.dku.danwoong.user.repository;

import com.dku.danwoong.user.model.Provider;
import com.dku.danwoong.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByProviderAndSenderId(Provider provider, String senderId);
}
