package com.inviggoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Advert {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private Integer price;

    @Column
    private String city;

    @Column
    private Date creationDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private AdvertCategory category;


}
