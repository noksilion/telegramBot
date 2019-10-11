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
public class CityDtoForUpdate {

    @NotNull(message = "id can not be null")
    private Integer id;

    @Size(min = 1, max = 20, message = "size must be more than 0 and no more than 20 letters")
    @NotNull(message = "name can not be null")
    private String name;

    @NotNull(message = "city info can not be null")
    @JsonProperty("info_messages_list")
    private List<String> cityInfoList;
}
