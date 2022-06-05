package com.dku.danwoong.user.service;

import com.dku.danwoong.user.model.Provider;
import com.dku.danwoong.user.model.User;
import com.dku.danwoong.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    DefaultUserService defaultUserService;

    @Nested
    @DisplayName("getUserId 메서드는")
    class Describe_getUserId {

        @Nested
        @DisplayName("provider 가 주어지지 않으면 ")
        class Context_with_emptyProvider {

            @Test
            @DisplayName("IllegalArgumentException 에러를 발생시킨다")
            void It_IllegalArgumentException() {
                //when, then
                assertThrows(IllegalArgumentException.class,
                        () -> defaultUserService.getUserId(null, "1"));
            }
        }

        @Nested
        @DisplayName("senderId 가 주어지지 않으면")
        class Context_with_emptySenderId {

            @ParameterizedTest
            @NullAndEmptySource
            @DisplayName("IllegalArgumentException 에러를 발생시킨다")
            void It_IllegalArgumentException(String senderId) {
                //when, then
                assertThrows(IllegalArgumentException.class,
                        () -> defaultUserService.getUserId(Provider.FACEBOOK, senderId));
            }
        }

        @Nested
        @DisplayName("주어진 SenderId, Provider 와 일치하는 User 가 존재하지 않으면")
        class Context_with_UserNotFound {

            @Test
            @DisplayName("새로운 User 를 생성하고 저장한다")
            void It_SaveNewUser() {
                //given
                final var provider = Provider.FACEBOOK;
                final var senderId = "1";
                final var newUser = new User(senderId, provider);

                when(userRepository.findByProviderAndSenderId(any(Provider.class), any(String.class)))
                        .thenReturn(Optional.empty());
                when(userRepository.save(any(User.class))).thenReturn(newUser);

                //when
                defaultUserService.getUserId(Provider.FACEBOOK, "1");

                //then
                verify(userRepository).save(any(User.class));
            }
        }

    }
}