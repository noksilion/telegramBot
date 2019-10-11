package com.kasianov.telegramBot.dao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityInfoDtoForReturn {
    private Integer id;

    private String information;
}
