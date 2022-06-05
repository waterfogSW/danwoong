package com.dku.danwoong.calender.repository;

import com.dku.danwoong.calender.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalenderRepository extends JpaRepository<Calender, Long> {
    Optional<Calender> findByName(String name);
}
