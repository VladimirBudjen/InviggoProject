package com.inviggoproject.controller;

import com.inviggoproject.dto.CreateAdvertRequestDto;
import com.inviggoproject.dto.FindAdvertResponseDto;
import com.inviggoproject.dto.UpdateAdvertRequestDto;
import com.inviggoproject.exception.ExceptionUtils;
import com.inviggoproject.exception.UnauthorizedActionException;
import com.inviggoproject.service.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping(value = "/advert", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertController {

    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FindAdvertResponseDto>> findAll() {
        return new ResponseEntity<>(advertService.findAllSortedByDate().stream()
                .map(advert ->
                        new FindAdvertResponseDto(
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

    @GetMapping("/code/{code}")
    public ResponseEntity<FindAdvertResponseDto> findByCode(@PathVariable String code) {
        return new ResponseEntity<>(new FindAdvertResponseDto(advertService.findByCode(code)), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('BASIC_USER')")
    public ResponseEntity<String> create(@Valid @RequestBody CreateAdvertRequestDto dto) {
        advertService.create(dto.generateAdvert(), dto.getCategory());
        return new ResponseEntity<>("Advert created", HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasRole('BASIC_USER')")
    public ResponseEntity<String> update(@Valid @RequestBody UpdateAdvertRequestDto dto) {
        try {
            advertService.update(dto.generateAdvert(), dto.getCategory(), dto.getCode());
            return new ResponseEntity<>("Advert updated", HttpStatus.OK);
        } catch (UnauthorizedActionException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("code/{code}")
    @PreAuthorize("hasRole('BASIC_USER')")
    public ResponseEntity<String> delete(@PathVariable String code) {
        try {
            advertService.delete(code);
            return new ResponseEntity<>("Advert deleted", HttpStatus.OK);
        } catch (UnauthorizedActionException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(ExceptionUtils.extractDefaultMessages(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ExceptionUtils.extractDefaultMessages(e), HttpStatus.BAD_REQUEST);
    }
}
