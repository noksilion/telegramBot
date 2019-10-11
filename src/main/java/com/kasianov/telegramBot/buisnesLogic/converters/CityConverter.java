package com.kasianov.telegramBot.buisnesLogic.converters;

import com.kasianov.telegramBot.dao.dtos.CityDto;
import com.kasianov.telegramBot.dao.dtos.CityDtoForReturn;
import com.kasianov.telegramBot.dao.dtos.CityDtoForUpdate;
import com.kasianov.telegramBot.dao.dtos.CityInfoDtoForReturn;
import com.kasianov.telegramBot.dao.entities.City;
import com.kasianov.telegramBot.dao.entities.CityInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityConverter {
    public CityDto convertToCityDto(City city){
        List<String> cityInfoList = new ArrayList<>();
        city.getCityInfoList().forEach(cityInfo -> { cityInfoList.add(cityInfo.getInformation()); });
        return CityDto.builder()
                .name(city.getName())
                .cityInfoList(cityInfoList)
                .build();
    }

    public City convertToCity(CityDto cityDto){
        List<CityInfo> cityInfos = new ArrayList<>();
        City city = City.builder()
                .name(cityDto.getName())
                .build();
        cityDto.getCityInfoList()
                .forEach(cityInfoDto -> { cityInfos.add(CityInfo.builder()
                        .city(city)
                        .information(cityInfoDto.toString())
                        .build()
                );
                });
        city.setCityInfoList(cityInfos);
        return city;
    }

    public CityDtoForReturn convertToCityDtoForReturn(City city){
        List<CityInfoDtoForReturn> cityInfoList = new ArrayList<>();
        city.getCityInfoList().forEach(cityInfo -> { cityInfoList.add(CityInfoDtoForReturn.builder()
                .id(cityInfo.getId())
                .information(cityInfo.getInformation())
                .build());
        });
        return CityDtoForReturn.builder()
                .name(city.getName())
                .id(city.getId())
                .cityInfoList(cityInfoList)
                .build();
    }

}
