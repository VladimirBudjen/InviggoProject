package com.inviggoproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindAllAdvertsByPageResponseDto {
    private List<FindAdvertResponseDto> adverts;
    private Integer numberOfPages;

    public FindAllAdvertsByPageResponseDto(List<FindAdvertResponseDto> adverts, Integer numberOfPages) {
        this.adverts = adverts;
        this.numberOfPages = numberOfPages;
    }
}
