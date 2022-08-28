package com.inviggoproject.dto;

import com.inviggoproject.model.Advert;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CreateAdvertRequestDto implements Serializable {

    @NotBlank(message = "Name can not be blank")
    private String name;
    @Min(value = 1, message = "Minimum price value is 1")
    @Max(value = 999999, message = "Maximum price value is 999999")
    private Integer price;
    @NotBlank(message = "City can not be blank")
    private String city;
    @NotBlank(message = "Image url can not be blank")
    @Size(max = 4000, message = "Image url can have maximum 4000 characters")
    private String imageUrl;
    @NotBlank(message = "Description can not be blank")
    @Size(max = 4000, message = "Description can have maximum 4000 characters")
    private String description;
    @NotBlank(message = "Category can not be blank")
    private String category;

    public Advert generateAdvert() {
        return new Advert(name, description, imageUrl, price, city);
    }
}
