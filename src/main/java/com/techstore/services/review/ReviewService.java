package com.techstore.services.review;

import com.techstore.dto.CreateReviewDto;
import com.techstore.dto.ReviewDto;
import java.util.List;


public interface ReviewService {
    void create(final CreateReviewDto dto);
}
