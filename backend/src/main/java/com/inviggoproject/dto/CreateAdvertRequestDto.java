package com.inviggoproject.dto;

import com.inviggoproject.model.Advert;
import com.inviggoproject.model.AdvertCategory;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateAdvertRequestDto implements Serializable {

    private String name;
    private Integer price;
    private String city;
    private String imageUrl;
    private String description;
    private String category;

    public Advert generateAdvert(){
        return new Advert(name, description, imageUrl, price, city);
    }
}
