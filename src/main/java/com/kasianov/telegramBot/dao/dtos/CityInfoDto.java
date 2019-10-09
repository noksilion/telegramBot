package com.kasianov.telegramBot.dao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityInfoDto {
    @Size(min = 1, max = 100, message = "size must be more than 0 and no more than 100 letters")
    @NotNull(message = "info can not be null")
    private String cityInformation;
}
