package com.kasianov.telegramBot.api.controllers;

import com.kasianov.telegramBot.buisnesLogic.services.CityServices;
import com.kasianov.telegramBot.buisnesLogic.validator.CheckIfNullInListThrowVoidMessageExceptionValidator;
import com.kasianov.telegramBot.dao.dtos.CityDto;
import com.kasianov.telegramBot.dao.dtos.CityDtoForReturn;
import com.kasianov.telegramBot.dao.dtos.CityDtoForUpdate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
@AllArgsConstructor
public class CityController {
    private final CityServices cityServices;
    private final CheckIfNullInListThrowVoidMessageExceptionValidator  validator;

    @PostMapping()
    public CityDtoForReturn createCity(@Valid @RequestBody CityDto cityDto){
        validator.checkIfNullInList(cityDto.getCityInfoList(),"city_info can not be null");
        return cityServices.createCity(cityDto);
    }

    @GetMapping
    public CityDtoForReturn getCity(Integer cityId){
        return cityServices.getCity(cityId);
    }

    @DeleteMapping
    public String deleteCity(Integer cityId){
        return cityServices.deleteCity(cityId);
    }

    @GetMapping(value = "/get_all")
    public List<CityDtoForReturn> getAll(){
        return cityServices.getAll();
    }
}
