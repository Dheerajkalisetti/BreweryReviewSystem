package com.webapp.BRS.service;

import com.webapp.BRS.dto.ReviewDto;
import com.webapp.BRS.entity.Review;

import java.util.List;

public interface ReviewService {

    void saveReview(ReviewDto reviewDto);

    List<ReviewDto> findByName(String name);
    List<ReviewDto> findReviewByEmail(String email);

    Review findReviewById(String uuid);

    List<ReviewDto> findAllReviews();

}