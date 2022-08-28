package com.inviggoproject.repository;

import com.inviggoproject.model.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;


public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Advert findByCode(String code);
    Page<Advert> findByNameContainingAndCategoryNameContainingAndUserUsernameContainingOrderByCreationDateDesc(Pageable pageable, String name, String category, String username);
    Page<Advert> findByNameContainingAndCategoryNameContainingAndUserUsernameIsOrderByCreationDateDesc(Pageable pageable, String name, String category, String username);
}
