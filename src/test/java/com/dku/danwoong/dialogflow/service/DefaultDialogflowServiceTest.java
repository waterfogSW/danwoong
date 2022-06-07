package com.dku.danwoong.dialogflow.service;

import com.dku.danwoong.dialogflow.utils.IntentTextDetector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultDialogflowServiceTest {

    @Mock
    IntentTextDetector intentTextDetector = new IntentTextDetector();

    @InjectMocks
    DefaultDialogflowService defaultDialogflowService;

    @Nested
    @DisplayName("query 메서드는")
    class Describe_query {

        @Nested
        @DisplayName("text 가 null 이거나 빈값이면")
        class Context_with_NullOrEmpty {

            @ParameterizedTest
            @NullAndEmptySource
            @DisplayName("IllegalArgumentException 을 발생시킨다")
            void It_ThrowsIllegalArgumentException(String text) {
                //when, then
                assertThrows(IllegalArgumentException.class, () -> defaultDialogflowService.query(text));
            }
        }

        @Nested
        @DisplayName("빈값이 아닌 text 가 전달되면")
        class Context_with_NotBlankText {

            @Test
            @DisplayName("intentTextDetector 의 detect 메서드를 호출한다")
            void It_CallIntentTextDetector() throws IOException {
                //given
                ReflectionTestUtils.setField(defaultDialogflowService, "projectId", "dku-sir-bot-ihdl");
                ReflectionTestUtils.setField(defaultDialogflowService, "sessionId", "123456789");
                ReflectionTestUtils.setField(defaultDialogflowService, "languageCode", "ko-KR");

                //when
                defaultDialogflowService.query("test");

                //then
                verify(intentTextDetector).detect(anyString(), anyString(), anyString(), anyString());
            }
        }
    }
}