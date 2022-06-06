package com.dku.danwoong.record.service;

import com.dku.danwoong.record.entity.Record;
import com.dku.danwoong.record.repository.RecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class DefaultRecordService implements RecordService {

    private final RecordRepository recordRepository;

    public DefaultRecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void save(Record record) {
        Assert.notNull(record, "Record must be provided");
        recordRepository.save(record);
    }
}
