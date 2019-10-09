package com.kasianov.telegramBot.dao.entities;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "cityInfo")
public class CityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CityInfoIdSeqGenerator")
    @SequenceGenerator(name="CityInfoIdSeqGenerator", sequenceName="CITY_INFO_SEQ", allocationSize=1)
    private Integer id;

    private String information;

    @ManyToOne()
    private City city;
}
