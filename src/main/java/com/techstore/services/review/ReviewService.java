package com.techstore.services.review;

import com.techstore.dto.CreateReviewDto;


public interface ReviewService {
    void create(final CreateReviewDto dto);
}
