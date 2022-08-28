package com.inviggoproject.service;

import com.inviggoproject.dto.AdvertsWithPageNumbersDto;
import com.inviggoproject.dto.FindAllAdvertsByPageRequestDto;
import com.inviggoproject.exception.UnauthorizedActionException;
import com.inviggoproject.model.Advert;


public interface AdvertService {

    Advert findByCode(String code);

    void create(Advert baseAdvert, String category);

    void update(Advert baseAdvert, String category, String code) throws UnauthorizedActionException;

    void delete(String code) throws UnauthorizedActionException;

    AdvertsWithPageNumbersDto findAllWithPages(FindAllAdvertsByPageRequestDto dto);
}
