package com.inviggoproject.service.impl;

import com.inviggoproject.model.Advert;
import com.inviggoproject.repository.AdvertRepository;
import com.inviggoproject.service.AdvertService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService{

    private final AdvertRepository advertRepository;

    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public List<Advert> findAllSortedByDate() {
        return advertRepository.findAll(Sort.by("creationDate"));
    }
}
