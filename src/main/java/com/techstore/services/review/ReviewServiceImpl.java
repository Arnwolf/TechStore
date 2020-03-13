package com.techstore.services.review;

import com.techstore.components.converter.Converter;
import com.techstore.components.converter.ReviewConverter;
import com.techstore.dto.CreateReviewDto;
import com.techstore.dto.ReviewDto;
import com.techstore.entities.Product;
import com.techstore.entities.Review;
import com.techstore.entities.User;
import com.techstore.services.product.ProductServiceImpl;
import com.techstore.services.user.UserServiceImpl;


public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository = new ReviewRepository();

    private final static ReviewServiceImpl reviewsService = new ReviewServiceImpl();
    public static ReviewServiceImpl getInstance() { return reviewsService; }

    private final ReviewValidationService validationService = ReviewValidationServiceImpl.getInstance();

    private ReviewServiceImpl() { }


    public void create(final CreateReviewDto dto) {
        validationService.validate(dto);

        User user = UserServiceImpl.getInstance().findByHashedId(dto.userHahsedId);

        Product product = ProductServiceImpl.getInstance().findProduct(dto.productId);

        Review newReview = new Review(user, product, dto.description, dto.rating, dto.creationDate);
        try {
            reviewRepository.add(newReview);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
        }
    }
}
