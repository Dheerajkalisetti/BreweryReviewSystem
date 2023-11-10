package com.webapp.BRS.service.impl;

import com.webapp.BRS.dto.RatingDto;
import com.webapp.BRS.dto.ReviewDto;
import com.webapp.BRS.dto.UserDto;
import com.webapp.BRS.entity.Rating;
import com.webapp.BRS.entity.Review;
import com.webapp.BRS.entity.User;
import com.webapp.BRS.repository.RatingRepository;
import com.webapp.BRS.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<RatingDto> findAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream().map((rating) -> mapToRatingDto(rating)).collect(Collectors.toList());
    }

    private RatingDto mapToRatingDto(Rating rating){
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(rating.getId());
        ratingDto.setRating(rating.getRating());
        return ratingDto;
    }
}
