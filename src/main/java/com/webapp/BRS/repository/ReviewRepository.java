package com.webapp.BRS.repository;

import com.webapp.BRS.dto.ReviewDto;
import com.webapp.BRS.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByName(String name);
    List<Review> findReviewByEmail(String email);
    Review findReviewById(String uuid);

}