package com.dku.danwoong.message.service;

import com.dku.danwoong.dialogflow.service.DialogflowService;
import com.dku.danwoong.message.model.DefaultMessage;
import com.dku.danwoong.user.model.Provider;
import com.dku.danwoong.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DefaultMessageServiceTest {
    @Mock
    BeanFactory beanFactory;

    @Mock
    DialogflowService dialogflowService;

    @Mock
    UserService userService;

    @InjectMocks
    DefaultMessageService defaultMessageService;

    @Nested
    @DisplayName("doOperation 메서드는")
    class Describe_doOperation {

        @Nested
        @DisplayName("provider 가 null 이면")
        class Context_with_NullProvider {

            @Test
            @DisplayName("IllegalArgumentException 에러를 발생시킨다")
            void It_ThrowsIllegalArgumentException() {
                assertThrows(IllegalArgumentException.class,
                        () -> defaultMessageService.doOperation(null, "1", "text"));
            }
        }

        @Nested
        @DisplayName("senderId 가 null 이거나 빈값이면")
        class Context_with_NullAndEmpty {

            @ParameterizedTest
            @NullAndEmptySource
            @DisplayName("IllegalArgumentException 에러를 발생시킨다")
            void It_ThrowsIllegalArgumentException(String senderId) {
                assertThrows(IllegalArgumentException.class,
                        () -> defaultMessageService.doOperation(Provider.FACEBOOK, senderId, "text"));
            }
        }

        @Nested
        @DisplayName("message 가 null 이거나 빈값이면")
        class Context_with_NullAndEmtpy {

            @ParameterizedTest
            @NullAndEmptySource
            @DisplayName("BLANK_MESSAGE_RESPONSE 값을 반환한다")
            void It_ReturnBLANK_MESSAGE_RESPONSE(String message) {
                //when
                final var result = defaultMessageService.doOperation(Provider.FACEBOOK, "1", message);

                //then
                assertEquals(DefaultMessage.BLANK_MESSAGE_RESPONSE, result);
            }
        }
    }
}