package com.webapp.BRS.service.impl;

import com.webapp.BRS.dto.ReviewDto;
import com.webapp.BRS.entity.Review;
import com.webapp.BRS.repository.ReviewRepository;
import com.webapp.BRS.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository)
    {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setUuid(reviewDto.getUuid());
        review.setName(reviewDto.getName());
        review.setEmail(reviewDto.getEmail());
        review.setReview(reviewDto.getReview());
        reviewRepository.save(review);
    }


    private ReviewDto mapToReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setEmail(review.getEmail());
        reviewDto.setReview(review.getReview());
        return reviewDto;
    }

    @Override
    public List<ReviewDto> findReviewByEmail(String email) {
        List<Review> reviews = reviewRepository.findReviewByEmail(email);
        return reviews.stream().map((review) -> mapToReviewDto(review)).collect(Collectors.toList());
    }

    @Override
    public Review findReviewById(String uuid) {
        return reviewRepository.findReviewById(uuid);
    }

    @Override
    public List<ReviewDto> findByName(String name) {
        List<Review> reviews = reviewRepository.findReviewByEmail(name);
        return reviews.stream().map((review) -> mapToReviewDto(review)).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map((review) -> mapToReviewDto(review)).collect(Collectors.toList());
    }
}
