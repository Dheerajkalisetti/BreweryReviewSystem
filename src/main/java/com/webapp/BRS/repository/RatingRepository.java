package com.webapp.BRS.repository;

import com.webapp.BRS.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findById(String id);
}
