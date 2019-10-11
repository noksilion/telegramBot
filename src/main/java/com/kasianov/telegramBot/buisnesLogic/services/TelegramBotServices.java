package com.kasianov.telegramBot.buisnesLogic.services;

import com.kasianov.telegramBot.dao.entities.City;
import com.kasianov.telegramBot.dao.entities.CityInfo;
import com.kasianov.telegramBot.dao.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component

public class TelegramBotServices {
    private final CityRepository cityRepository;
    private final String GET_ALL_CITY_NAMES_COMMAND = "/cities";
    private final String START_COMMAND = "/start";
    private final String HELP_COMMAND = "/help";

    @Value("${telegram_bot_help_message}")
    private String helpMessage;

    @Value("${telegram_bot_first_view_message}")
    private String helloMessage;

    public TelegramBotServices(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public String processMessage(String message){
        if(message.isEmpty()){
            return "Message can not be empty";
        }else {
            if(message.startsWith("/")){
                if(message.equals(GET_ALL_CITY_NAMES_COMMAND)){
                    return getAllCities();
                }
                else if (message.equals(START_COMMAND)){
                    return helloMessage;
                }
                else if (message.equals(HELP_COMMAND)){
                    return helpMessage;
                }
                else{
                    return "no such command as "+message +"\n"+helpMessage;
                }
            }
            return getCityInfo(message);
        }
    }

    public String getAllCities(){
        List<String> cityNameList = cityRepository.findAll()
                .stream()
                .map(City::getName)
                .collect(Collectors.toList());

        if(cityNameList.isEmpty()){
            return "there are no cities in db";
        }
        return String.join("\n", cityNameList);
    }

    public String getCityInfo(String cityName){
        City city = cityRepository.getByName(cityName);
        if (city == null) {
            return "There are no city with name "+cityName;
        }
        List<String> cityInfoList = city.getCityInfoList()
                .stream()
                .map(CityInfo::getInformation)
                .collect(Collectors.toList());
        if(cityInfoList.isEmpty()){
            return "there no information for city "+ cityName;
        }
        Random random = new Random();
        return cityInfoList.get(random.nextInt(cityInfoList.size()));
    }

}

