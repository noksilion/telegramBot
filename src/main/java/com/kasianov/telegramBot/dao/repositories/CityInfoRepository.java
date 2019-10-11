package com.kasianov.telegramBot.dao.repositories;

import com.kasianov.telegramBot.dao.entities.City;
import com.kasianov.telegramBot.dao.entities.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityInfoRepository extends JpaRepository<CityInfo,Integer> {

    public List<CityInfo> getAllByCityName(String cityName);
    public boolean existsByIdAndCity(Integer id, City city);
}
