package com.techstore.components.validator;

import com.techstore.dto.CreateReviewDto;


public class ReviewValidator implements Validator<CreateReviewDto> {

    @Override
    public void validate(final CreateReviewDto review) {
        if (review.description.isEmpty())
            throw new RuntimeException("Comment section can not be empty!");

        if (review.rating < 0 || review.rating > 5)
            throw new RuntimeException("Incorrect rating point!");
    }
}
