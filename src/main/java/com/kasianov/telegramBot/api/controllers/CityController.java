package com.kasianov.telegramBot.api.controllers;

import com.kasianov.telegramBot.buisnesLogic.services.CityServices;
import com.kasianov.telegramBot.dao.dtos.CityDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/city")
@AllArgsConstructor
public class CityController {
    private final CityServices cityServices;

    @PostMapping()
    public CityDto createCity(@Valid @RequestBody CityDto cityDto){
        return cityServices.createCity(cityDto);
    }

    @GetMapping
    public CityDto getCity(Integer cityId){
        return cityServices.getCity(cityId);
    }
}
