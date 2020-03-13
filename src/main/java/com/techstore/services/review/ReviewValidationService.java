package com.techstore.services.review;

import com.techstore.dto.CreateReviewDto;


public interface ReviewValidationService {
    void validate(final CreateReviewDto dto);
}
