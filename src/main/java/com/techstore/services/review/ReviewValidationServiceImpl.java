package com.techstore.services.review;

import com.techstore.components.validator.ReviewValidator;
import com.techstore.components.validator.Validator;
import com.techstore.dto.CreateReviewDto;


public class ReviewValidationServiceImpl implements ReviewValidationService {
    private Validator<CreateReviewDto> validator = new ReviewValidator();

    private static ReviewValidationServiceImpl validationService = new ReviewValidationServiceImpl();
    public static ReviewValidationServiceImpl getInstance() { return validationService;}

    private ReviewValidationServiceImpl() { }

    @Override
    public void validate(final CreateReviewDto dto) { validator.validate(dto); }
}
