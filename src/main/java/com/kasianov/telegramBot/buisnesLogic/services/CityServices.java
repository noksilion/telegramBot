package com.kasianov.telegramBot.buisnesLogic.services;

import com.kasianov.telegramBot.api.exceptionHandlers.ExceptionLogger;
import com.kasianov.telegramBot.buisnesLogic.converters.CityConverter;
import com.kasianov.telegramBot.dao.dtos.CityDto;
import com.kasianov.telegramBot.dao.dtos.CityDtoForReturn;
import com.kasianov.telegramBot.dao.dtos.CityDtoForUpdate;
import com.kasianov.telegramBot.dao.entities.City;
import com.kasianov.telegramBot.dao.exceptions.NoSuchEntityException;
import com.kasianov.telegramBot.dao.exceptions.VoidMessageException;
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
public class CityServices {
    private final CityRepository cityRepository;
    private final ExceptionLogger exceptionLogger;
    private final CityConverter cityConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServices.class);

    public CityDtoForReturn getCity(Integer cityId){
        LOGGER.info(String.format("Get City with id %s", cityId));
        City city = cityRepository.findOne(cityId);
        if (city == null) {
            throw exceptionLogger.logAndReturnException(LOGGER, new NoSuchEntityException("City", "id "+cityId));
        }
        return cityConverter.convertToCityDtoForReturn(city);
    }

    public CityDtoForReturn createCity(CityDto cityDto){
        City returnedCity;
        try {
            returnedCity = cityRepository.save(cityConverter.convertToCity(cityDto));
        }
        catch( DataIntegrityViolationException dataException ) {
            String exceptionMessage = dataException.getMessage();

            if ( exceptionMessage.contains("constraint [city_name]") ) {
                throw exceptionLogger.logAndReturnException(LOGGER, new VoidMessageException("This City Name already exists in db"));
            }
            else if ( exceptionMessage.contains("constraint [information_for_sity]") ) {
                throw exceptionLogger.logAndReturnException(LOGGER, new VoidMessageException("Duplicate City Info for this city "));
            }
            else {
                throw exceptionLogger.logAndReturnException(LOGGER, new VoidMessageException("Oops something went wrong check data adn resend it"));
            }
        }
        return cityConverter.convertToCityDtoForReturn(cityRepository.findOne(returnedCity.getId()));
    }

    public String deleteCity(Integer cityId){
        try {
            cityRepository.delete(cityId);
        }catch (EmptyResultDataAccessException exeption){
            throw exceptionLogger.logAndReturnException(LOGGER,new NoSuchEntityException("City",cityId.toString()));
        }
        return "successfully deleted City with id " + cityId;
    }

    public List<CityDtoForReturn> getAll(){
        return cityRepository.findAll().stream()
                .map(cityConverter::convertToCityDtoForReturn)
                .collect(Collectors.toList());
    }

}
