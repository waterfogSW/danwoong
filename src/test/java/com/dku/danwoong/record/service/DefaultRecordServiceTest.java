package com.dku.danwoong.record.service;

import com.dku.danwoong.record.entity.ActivityType;
import com.dku.danwoong.record.entity.Record;
import com.dku.danwoong.record.repository.RecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultRecordServiceTest {
    @Mock
    RecordRepository recordRepository;

    @InjectMocks
    DefaultRecordService defaultRecordService;
    
    @Nested
    @DisplayName("save 메서드는")
    class Describe_save {
    
        @Nested
        @DisplayName("record 가 인자로 전달되면")
        class Context_with_record {
    
            @Test
            @DisplayName("recordRepository 의 save 메서드를 호출한다")
            void It_CallRepositorySave() {
                //given
                final var newRecord = new Record(
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        ActivityType.STUDY
                );
                        
                //when
                defaultRecordService.save(newRecord);

                //then
                verify(recordRepository).save(any(Record.class));
            }
        }
        
        @Nested
        @DisplayName("null 값이 인자로 전달되면")
        class Context_with_null {
        
            @Test
            @DisplayName("IllegalArgumentException 에러를 발생시킨다")
            void It_throwsIllegalArgumentException() {
                //when, then
                assertThrows(IllegalArgumentException.class, () -> defaultRecordService.save(null));
            }
        }
        
    }
}