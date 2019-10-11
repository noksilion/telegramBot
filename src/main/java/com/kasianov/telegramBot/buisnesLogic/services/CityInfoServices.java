package com.kasianov.telegramBot.buisnesLogic.services;

import com.kasianov.telegramBot.api.exceptionHandlers.ExceptionLogger;
import com.kasianov.telegramBot.buisnesLogic.converters.CityInfoConverter;
import com.kasianov.telegramBot.dao.dtos.CityInfoDtoForCreate;
import com.kasianov.telegramBot.dao.dtos.CityInfoDtoForReturn;
import com.kasianov.telegramBot.dao.dtos.CityInfoDtoForUpdate;
import com.kasianov.telegramBot.dao.entities.City;
import com.kasianov.telegramBot.dao.entities.CityInfo;
import com.kasianov.telegramBot.dao.exceptions.NoSuchEntityException;
import com.kasianov.telegramBot.dao.exceptions.VoidMessageException;
import com.kasianov.telegramBot.dao.repositories.CityInfoRepository;
import com.kasianov.telegramBot.dao.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class CityInfoServices {
    private final CityInfoRepository cityInfoRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServices.class);
    private final CityInfoConverter cityInfoConverter;
    private final CityRepository cityRepository;
    private final ExceptionLogger exceptionLogger;

    public CityInfoDtoForReturn getCityInfo(Integer cityInfoId){
        LOGGER.info(String.format("Get City Info with id %s", cityInfoId));
        CityInfo cityInfo = cityInfoRepository.findOne(cityInfoId);
        if (cityInfo == null) {
            throw exceptionLogger.logAndReturnException(LOGGER, new NoSuchEntityException("City Info", "id "+cityInfoId));
        }
        return cityInfoConverter.convert(cityInfo);
    }

    public CityInfoDtoForReturn create(CityInfoDtoForCreate cityInfoDtoForCreate){
        CityInfo cityInfo;
        try {
            cityInfo = cityInfoRepository.save(cityInfoConverter.convert(cityInfoDtoForCreate));
        }catch (DataIntegrityViolationException dataException){
            String exceptionMessage = dataException.getMessage();
            if ( exceptionMessage.contains("constraint [information_for_sity]") ) {
                throw exceptionLogger.logAndReturnException(LOGGER, new VoidMessageException("Duplicate City Info for this city "));
            }
            else {
                throw exceptionLogger.logAndReturnException(LOGGER, new VoidMessageException("Oops something went wrong check data adn resend it"));
            }
        }
        return cityInfoConverter.convert(cityInfo);
    }

    public String deleteCityInfo(Integer cityInfoId){
        try {
            cityInfoRepository.delete(cityInfoId);
        }catch (EmptyResultDataAccessException exeption){
            throw exceptionLogger.logAndReturnException(LOGGER,new NoSuchEntityException("City Info",cityInfoId.toString()));
        }
        return "successfully deleted City Info with id " + cityInfoId;
    }

    public CityInfoDtoForReturn update(CityInfoDtoForUpdate cityInfoDtoForUpdate){
        City city = cityRepository.findOne(cityInfoDtoForUpdate.getCityId());
        if (city == null) {
            throw exceptionLogger.logAndReturnException(LOGGER, new NoSuchEntityException("City", "id "+cityInfoDtoForUpdate.getId().toString()));
        }
        if(cityInfoRepository.existsByIdAndCity(cityInfoDtoForUpdate.getId(),city)){
            throw exceptionLogger.logAndReturnException(LOGGER,new NoSuchEntityException("City Info",cityInfoDtoForUpdate.getId().toString()));
        }else{
           return cityInfoConverter.convert( cityInfoRepository.save(CityInfo.builder()
                    .city(city)
                    .information(cityInfoDtoForUpdate.getInformation())
                    .id(cityInfoDtoForUpdate.getId())
                    .build())
            );
        }
    }

    public List<CityInfoDtoForReturn>  getAll(){
        return cityInfoRepository.findAll().stream()
                .map(cityInfoConverter::convert)
                .collect(Collectors.toList());
    }

}
