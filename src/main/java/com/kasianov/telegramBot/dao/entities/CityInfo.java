package com.kasianov.telegramBot.dao.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "cityInfo")
@Table(name = "city_infos",uniqueConstraints=
@UniqueConstraint(columnNames={"city_id", "information"}))
public class CityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CityInfoIdSeqGenerator")
    @SequenceGenerator(name="CityInfoIdSeqGenerator", sequenceName="CITY_INFO_SEQ", allocationSize=1)
    private Integer id;

    @Column(name = "information")
    private String information;

    @ManyToOne()
    private City city;
}
