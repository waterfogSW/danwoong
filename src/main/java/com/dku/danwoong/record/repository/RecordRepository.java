package com.dku.danwoong.record.repository;

import com.dku.danwoong.record.entity.ActivityType;
import com.dku.danwoong.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByUserIdAndActivityType(Long userId, ActivityType activityType);
}
