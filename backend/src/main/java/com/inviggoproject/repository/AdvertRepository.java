package com.inviggoproject.repository;

import com.inviggoproject.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Advert findByCode(String code);
}
