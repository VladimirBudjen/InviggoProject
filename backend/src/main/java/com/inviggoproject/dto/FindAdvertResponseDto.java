package com.inviggoproject.dto;

import com.inviggoproject.model.Advert;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FindAdvertResponseDto implements Serializable {
    private String name;
    private Integer price;
    private String city;
    private String imageUrl;
    private String description;
    private String category;
    private String code;
    private String ownerUsername;
    private Date creationDate;

    public FindAdvertResponseDto() {
    }

    public FindAdvertResponseDto(String name, Integer price, String city, String imageUrl, String description, String category, String code, String ownerUsername, Date creationDate) {
        this.name = name;
        this.price = price;
        this.city = city;
        this.imageUrl = imageUrl;
        this.description = description;
        this.category = category;
        this.code = code;
        this.ownerUsername = ownerUsername;
        this.creationDate = creationDate;
    }

    public FindAdvertResponseDto(Advert advert){
        this.name = advert.getName();
        this.price= advert.getPrice();
        this.city =advert.getCity();
        this.imageUrl =advert.getImageUrl();
        this.description=advert.getDescription();
        this.category=advert.getCategory().getName();
        this.code= advert.getCode();
        this.ownerUsername=advert.getUser().getUsername();
        this.creationDate=advert.getCreationDate();

    }
}
