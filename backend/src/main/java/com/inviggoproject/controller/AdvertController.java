package com.inviggoproject.controller;

import com.inviggoproject.dto.FindAllAdvertsResponseDto;
import com.inviggoproject.service.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/advert", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertController {

    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FindAllAdvertsResponseDto>> findAll() {
        return new ResponseEntity<>(advertService.findAllSortedByDate().stream()
                .map(advert ->
                        new FindAllAdvertsResponseDto(
                                advert.getName(),
                                advert.getPrice(),
                                advert.getCity(),
                                advert.getImageUrl(),
                                advert.getDescription(),
                                advert.getCategory().getName(),
                                advert.getCode(),
                                advert.getUser().getUsername(),
                                advert.getCreationDate())
                ).collect(Collectors.toList()), HttpStatus.OK);
    }
}
