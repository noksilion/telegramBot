package com.kasianov.telegramBot.dao.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CityDtoForReturn {
    private Integer id;

    private String name;

    @JsonProperty("info_messages_list")
    private List<CityInfoDtoForReturn> cityInfoList;
}
