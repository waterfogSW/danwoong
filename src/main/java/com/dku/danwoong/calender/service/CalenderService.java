package com.dku.danwoong.calender.service;

import com.dku.danwoong.calender.entity.Calender;

public interface CalenderService {
    Calender findByName(String name);
}
