package com.techstore.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class DetailedProductDto {
    public PreviewProductDto product;
    public List<ParameterDto> parameters;
    public List<ParameterDto> changeableParameters;
    public List<String> productPhotos;
    public List<ReviewDto> reviews;
    public int totalReviewsScore;
    public String description;


    public PreviewProductDto getProduct() { return product; }
    public List<ParameterDto> getParameters() { return parameters; }
    public List<ParameterDto> getChangeableParameters() { return changeableParameters; }
    public List<String> getProductPhotos() { return productPhotos; }
    public int getTotalReviewsScore() { return totalReviewsScore; }
    public String getDescription() { return description; }
    public List<ReviewDto> getReviews() { return reviews; }
}
