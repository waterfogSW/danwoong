package com.dku.danwoong.calender.service;

import com.dku.danwoong.calender.entity.Calender;
import com.dku.danwoong.calender.repository.CalenderRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
public class DefaultCalenderService implements CalenderService {

    private final CalenderRepository calenderRepository;

    public DefaultCalenderService(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    @Override
    public Calender findByName(String name) {
        Assert.isTrue(isNotEmpty(name), "");

        return calenderRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Calender not found"));
    }
}
