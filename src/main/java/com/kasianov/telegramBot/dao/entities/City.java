package com.kasianov.telegramBot.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "city")
@Builder
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CityIdSeqGenerator")
    @SequenceGenerator(name="CityIdSeqGenerator", sequenceName="CITY_SEQ", allocationSize=1)
    private Integer id;

    private String name;
    @OneToMany(mappedBy = "city")
    List<CityInfo> cityInfoList;

}
