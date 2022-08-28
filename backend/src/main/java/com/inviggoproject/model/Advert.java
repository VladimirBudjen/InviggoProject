package com.inviggoproject.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Advert {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column
    private String name;

    @Column(length = 4096)
    private String description;

    @Column(length = 4096)
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

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getCity() {
        return this.city;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public User getUser() {
        return this.user;
    }

    public AdvertCategory getCategory() {
        return this.category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(AdvertCategory category) {
        this.category = category;
    }

    public String toString() {
        return "Advert(id=" + this.getId() + ", code=" + this.getCode() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", imageUrl=" + this.getImageUrl() + ", price=" + this.getPrice() + ", city=" + this.getCity() + ", creationDate=" + this.getCreationDate() + ")";
    }
}
