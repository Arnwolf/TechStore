package com.techstore.dto;

import java.time.LocalDateTime;

public class CreateReviewDto {
    public int productId;
    public String userHahsedId;
    public String description;
    public int rating;
    public LocalDateTime creationDate;
}
