package com.techstore.dto;

import com.techstore.entities.Product;
import com.techstore.entities.ProductParameter;
import com.techstore.entities.Review;
import java.util.List;


public class ProductDTO {
    private Product product;
    private List<ProductParameter> parameters;
    private List<ProductParameter> changeableParameters;
    private List<String> productPhotos;
    private Integer totalReviewScore;
    private String description;
    private List<Review> reviews;


    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public List<ProductParameter> getParameters() { return parameters; }
    public void setParameters(List<ProductParameter> parameters) { this.parameters = parameters; }

    public List<ProductParameter> getChangeableParameters() { return changeableParameters; }
    public void setChangeableParameters(List<ProductParameter> changeableParameters) { this.changeableParameters = changeableParameters; }

    public List<String> getProductPhotos() { return productPhotos; }
    public void setProductPhotos(List<String> productPhotos) { this.productPhotos = productPhotos; }

    public Integer getTotalReviewScore() { return totalReviewScore; }
    public void setTotalReviewScore(Integer totalReviewScore) { this.totalReviewScore = totalReviewScore; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
