package com.kasianov.telegramBot.dao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {
    @Size(min = 1, max = 20, message = "size must be more than 0 and no more than 20 letters")
    @NotNull(message = "name can not be null")
    private String name;

    @NotNull(message = "city info can not be null")
    private List<CityInfoDto> cityInfoDtoList;
}
