package com.inviggoproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Advert {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long code;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private AdvertType type;
}
