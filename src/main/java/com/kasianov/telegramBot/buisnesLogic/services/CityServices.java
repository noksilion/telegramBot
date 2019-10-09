package com.kasianov.telegramBot.buisnesLogic.services;

import com.kasianov.telegramBot.api.exceptionHandlers.ExceptionLogger;
import com.kasianov.telegramBot.buisnesLogic.converters.CityConverter;
import com.kasianov.telegramBot.dao.dtos.CityDto;
import com.kasianov.telegramBot.dao.entities.City;
import com.kasianov.telegramBot.dao.entities.CityInfo;
import com.kasianov.telegramBot.dao.exceptions.NoSuchEntityException;
import com.kasianov.telegramBot.dao.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CityServices {
    private final CityRepository cityRepository;
    private final ExceptionLogger exceptionLogger;
    private final CityConverter cityConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServices.class);

    public CityDto getCity(Integer cityId){
        LOGGER.info(String.format("Get City with id %s", cityId));
        City city = cityRepository.getOne(cityId);
        if (city == null) {
            throw exceptionLogger.logAndReturnException(LOGGER, new NoSuchEntityException("City", "id "+cityId));
        }
        return cityConverter.convert(city);
    }

    public CityDto createCity(CityDto cityDto){
        City city = City.builder()
                .name(cityDto.getName())
                .build();
        cityDto.getCityInfoDtoList()
                .forEach(cityInfoDto -> { city.getCityInfoList().add(CityInfo.builder()
                            .city(city)
                            .information(cityInfoDto.getCityInformation())
                            .build()
                    );
                });

        City returnedCity = cityRepository.saveAndFlush(city);
        return cityConverter.convert(returnedCity);
    }

}
