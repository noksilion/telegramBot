package com.kasianov.telegramBot.buisnesLogic.converters;

import com.kasianov.telegramBot.dao.dtos.CityDto;
import com.kasianov.telegramBot.dao.dtos.CityInfoDto;
import com.kasianov.telegramBot.dao.entities.City;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityConverter {
    public CityDto convert(City city){
        return CityDto.builder()
                .name(city.getName())
                .cityInfoDtoList(city.getCityInfoList().stream()
                        .map(cityInfo -> CityInfoDto
                                .builder()
                                .cityInformation(cityInfo.getInformation())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

}
