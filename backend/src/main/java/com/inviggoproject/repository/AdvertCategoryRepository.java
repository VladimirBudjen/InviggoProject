package com.inviggoproject.repository;

import com.inviggoproject.model.AdvertCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertCategoryRepository extends JpaRepository<AdvertCategory, Long> {
    AdvertCategory findByName(String categoryName);
}
