package com.inviggoproject.dto;

import com.inviggoproject.model.Advert;
import lombok.Data;

import java.util.List;

@Data
public class AdvertsWithPageNumbersDto {
    private List<Advert> adverts;
    private Integer totalPages;

    public AdvertsWithPageNumbersDto(List<Advert> adverts, Integer totalPages) {
        this.adverts=adverts;
        this.totalPages=totalPages;
    }
}
