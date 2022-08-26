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

    @Column(length = 1024)
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

    public Advert() {
    }

    public Advert(String name, String description, String imageUrl, Integer price, String city) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.city = city;
    }

    public Advert(String code, String name, String description, String imageUrl, Integer price, String city, Date creationDate, User user, AdvertCategory category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.city = city;
        this.creationDate = creationDate;
        this.user = user;
        this.category = category;
    }
}
