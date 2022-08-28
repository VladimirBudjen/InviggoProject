package com.inviggoproject.service.impl;

import com.inviggoproject.dto.AdvertsWithPageNumbersDto;
import com.inviggoproject.dto.FindAllAdvertsByPageRequestDto;
import com.inviggoproject.exception.UnauthorizedActionException;
import com.inviggoproject.model.Advert;
import com.inviggoproject.model.AdvertCategory;
import com.inviggoproject.model.User;
import com.inviggoproject.repository.AdvertCategoryRepository;
import com.inviggoproject.repository.AdvertRepository;
import com.inviggoproject.service.AdvertService;
import com.inviggoproject.service.AuthService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertCategoryRepository advertCategoryRepository;
    private final AuthService authService;

    public AdvertServiceImpl(AdvertRepository advertRepository, AdvertCategoryRepository advertCategoryRepository, AuthService authService) {
        this.advertRepository = advertRepository;
        this.advertCategoryRepository = advertCategoryRepository;
        this.authService = authService;
    }

    @Override
    public AdvertsWithPageNumbersDto findAllWithPages(FindAllAdvertsByPageRequestDto dto) {
        String categoryName = dto.getCategoryName().equals("all") ? "" : dto.getCategoryName();
        Pageable pageParams = PageRequest.of(dto.getPage(), dto.getPageSize());
        Page<Advert> pages;
        pages = findByOwnerAndCategory(dto, categoryName, pageParams);
        return filterByPrice(dto, pages);
    }

    private Page<Advert> findByOwnerAndCategory(FindAllAdvertsByPageRequestDto dto, String categoryName, Pageable pageParams) {
        Page<Advert> pages;
        if (dto.getOwnerUserName().equals("")) {
            pages = advertRepository.findByNameContainingAndCategoryNameContainingAndUserUsernameContainingOrderByCreationDateDesc
                    (pageParams, dto.getName(), categoryName, "");
        } else {
            pages = advertRepository.findByNameContainingAndCategoryNameContainingAndUserUsernameIsOrderByCreationDateDesc
                    (pageParams, dto.getName(), categoryName, dto.getOwnerUserName());
        }
        return pages;
    }

    private AdvertsWithPageNumbersDto filterByPrice(FindAllAdvertsByPageRequestDto dto, Page<Advert> pages) {
        switch (dto.getMinOrMax()){
            case max -> {
                var max = pages.stream().max(Comparator.comparing(Advert::getPrice)).stream().toList();
                return new AdvertsWithPageNumbersDto(max, 1);
            }
            case min ->{
                var min = pages.stream().min(Comparator.comparing(Advert::getPrice)).stream().toList();
                return new AdvertsWithPageNumbersDto(min, 1);
            }
            default -> {
                return new AdvertsWithPageNumbersDto(pages.stream().toList(), pages.getTotalPages());
            }
        }
    }

    @Override
    public Advert findByCode(String code) {
        return advertRepository.findByCode(code);
    }

    @Override
    public void create(Advert baseAdvert, String categoryName) {
        AdvertCategory category = advertCategoryRepository.findByName(categoryName);
        User user = authService.getActiveUser();
        Date date = new Date();
        String code = UUID.randomUUID().toString();
        advertRepository.save(
                new Advert(code, baseAdvert.getName(), baseAdvert.getDescription(),
                        baseAdvert.getImageUrl(), baseAdvert.getPrice(), baseAdvert.getCity(), date, user, category));
    }

    @Override
    public void update(Advert baseAdvert, String categoryName, String code) throws UnauthorizedActionException {
        AdvertCategory category = advertCategoryRepository.findByName(categoryName);
        Advert advert = findByCode(code);
        checkIfAdvertBelongsToUser(advert, "You can only update your adverts.");

        advert.setCategory(category);
        advert.setDescription(baseAdvert.getDescription());
        advert.setImageUrl(baseAdvert.getImageUrl());
        advert.setPrice(baseAdvert.getPrice());
        advert.setCity(baseAdvert.getCity());
        advert.setName(baseAdvert.getName());

        advertRepository.save(advert);
    }

    @Override
    public void delete(String code) throws UnauthorizedActionException {
        var advert = findByCode(code);
        checkIfAdvertBelongsToUser(advert, "You can only delete your adverts.");
        advertRepository.delete(advert);
    }

    private void checkIfAdvertBelongsToUser(Advert advert, String message) throws UnauthorizedActionException {
        if (!Objects.equals(advert.getUser().getUsername(), authService.getActiveUser().getUsername())) {
            throw new UnauthorizedActionException(message);
        }
    }
}
