package com.techstore.components.converter;

import com.techstore.dto.ReviewDto;
import com.techstore.entities.Review;


public class ReviewConverter implements Converter<ReviewDto, Review> {
    @Override
    public ReviewDto convert(final Review toConvert) {
        ReviewDto dto = new ReviewDto();
        dto.comment = toConvert.getDescription();
        dto.creationDate = toConvert.getCreationDate();
        dto.id = toConvert.getId();
        dto.rating = toConvert.getRating();
        dto.userName = toConvert.getUser().getName();

        return dto;
    }
}
