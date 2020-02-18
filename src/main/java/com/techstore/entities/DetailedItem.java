package com.techstore.entities;

import java.util.List;

public class DetailedItem extends Item {
    private List<ItemParameter> itemParameters;
    private List<ItemReview> itemReviews;
    private List<String> allPhotos;
    private double averageScore;

    public DetailedItem() { }

    public List<ItemParameter> getItemParameters() {
        return itemParameters;
    }

    public void setItemParameters(List<ItemParameter> itemParameters) {
        this.itemParameters = itemParameters;
    }

    public List<ItemReview> getItemReviews() {
        return itemReviews;
    }

    public void setItemReviews(List<ItemReview> itemReviews) {
        for (ItemReview rev : itemReviews) {
            if (rev.getUserName().isEmpty())
                rev.setUserName("Unnamed User");

            averageScore += rev.getRating();
        }

        if (averageScore != 0.0)
            averageScore /= itemReviews.size();

        this.itemReviews = itemReviews;
    }

    public List<String> getAllPhotos() {
        return allPhotos;
    }

    public void setAllPhotos(List<String> allPhotos) {
        this.allPhotos = allPhotos;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
