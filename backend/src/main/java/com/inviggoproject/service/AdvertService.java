package com.inviggoproject.service;

import com.inviggoproject.exception.UnauthorizedActionException;
import com.inviggoproject.model.Advert;

import java.util.List;

public interface AdvertService {

    List<Advert> findAllSortedByDate();

    Advert findByCode(String code);

    void create(Advert baseAdvert, String category);

    void update(Advert baseAdvert, String category, String code) throws UnauthorizedActionException;

    void delete(String code) throws UnauthorizedActionException;
}
