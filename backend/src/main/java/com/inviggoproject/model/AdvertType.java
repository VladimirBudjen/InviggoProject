package com.inviggoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class AdvertType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Advert> adverts = new HashSet<>();
}
