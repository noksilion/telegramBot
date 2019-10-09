package com.kasianov.telegramBot.dao.repositories;

import com.kasianov.telegramBot.dao.entities.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityInfoRepository extends JpaRepository<CityInfo,Integer> {
}
