package com.webapp.BRS.service;

import com.webapp.BRS.dto.RatingDto;

import java.util.List;

public interface RatingService {
    List<RatingDto> findAllRatings();
}
