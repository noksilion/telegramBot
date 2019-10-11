package com.kasianov.telegramBot.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "city")
@Builder
@Getter
@Setter
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CityIdSeqGenerator")
    @SequenceGenerator(name="CityIdSeqGenerator", sequenceName="CITY_SEQ", allocationSize=1)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST,orphanRemoval = true,fetch = FetchType.EAGER)
    List<CityInfo> cityInfoList = new ArrayList<>();

}
