package com.kasianov.telegramBot.dao.repositories;

import com.kasianov.telegramBot.dao.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {

    public City getByName(String name);
}
