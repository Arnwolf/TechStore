package com.techstore.services;

import com.techstore.entities.Product;
import com.techstore.entities.Review;
import com.techstore.repositories.ReviewRepository;
import java.util.List;


public class ReviewsService {
    private ReviewRepository reviewRepository = new ReviewRepository();

    private static ReviewsService reviewsService = new ReviewsService();
    public static ReviewsService getInstance() { return reviewsService; }

    private ReviewsService() { }


    public void addReview(final Review review) {
        reviewRepository.add(review);
    }

    public List<Review> reviewList(final Product product) {
        return reviewRepository.findByProductId(product.getId());
    }
}
