package com.kasianov.telegramBot.buisnesLogic.converters;

import com.kasianov.telegramBot.dao.dtos.CityInfoDtoForCreate;
import com.kasianov.telegramBot.dao.dtos.CityInfoDtoForReturn;
import com.kasianov.telegramBot.dao.entities.CityInfo;
import com.kasianov.telegramBot.dao.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CityInfoConverter {
    private final CityRepository cityRepository;
    public CityInfoDtoForReturn convert(CityInfo cityInfo){
        return CityInfoDtoForReturn.builder()
                .information(cityInfo.getInformation())
                .id(cityInfo.getId())
                .build();
    }

    public CityInfo convert (CityInfoDtoForCreate cityInfoDtoForCreate){
        return CityInfo.builder()
                .city(cityRepository.findOne(cityInfoDtoForCreate.getCityId()))
                .information(cityInfoDtoForCreate.getInformation())
                .build();
    }

}
