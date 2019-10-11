package com.kasianov.telegramBot.dao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityInfoDtoForCreate {
    @NotNull(message = "city id can not be null")
    private Integer cityId;

    @Size(min = 1,  message = "size must be more than 0 letters")
    @NotNull(message = "information can not be null")
    private String information;
}
