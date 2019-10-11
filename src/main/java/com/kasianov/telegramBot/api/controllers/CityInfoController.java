package com.kasianov.telegramBot.api.controllers;

import com.kasianov.telegramBot.buisnesLogic.services.CityInfoServices;
import com.kasianov.telegramBot.dao.dtos.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city_info")
@AllArgsConstructor
public class CityInfoController {
    private final CityInfoServices cityInfoServices;

    @GetMapping()
    public CityInfoDtoForReturn getCityInfo(Integer cityInfoId){
        return cityInfoServices.getCityInfo(cityInfoId);
    }

    @PutMapping
    public CityInfoDtoForReturn update(@Valid @RequestBody CityInfoDtoForUpdate cityDtoForUpdate){
        return cityInfoServices.update(cityDtoForUpdate);
    }

    @PostMapping()
    public CityInfoDtoForReturn create (@Valid @RequestBody CityInfoDtoForCreate cityInfoDtoForCreate){
        return cityInfoServices.create(cityInfoDtoForCreate);
    }

    @DeleteMapping()
    public String delete (Integer cityInfoId){
        return cityInfoServices.deleteCityInfo(cityInfoId);
    }

    @GetMapping(value = "/get_all")
    public List<CityInfoDtoForReturn> getAll(){
        return cityInfoServices.getAll();
    }
}
