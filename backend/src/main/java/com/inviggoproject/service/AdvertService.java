package com.inviggoproject.service;

import com.inviggoproject.model.Advert;

import java.util.List;

public interface AdvertService {

    List<Advert> findAllSortedByDate();
}
