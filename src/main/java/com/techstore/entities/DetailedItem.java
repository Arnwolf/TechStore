package com.techstore.entities;

import java.util.List;

public class DetailedItem extends Item {
    private List<Parameter> itemParameters;
    private List<Review> itemReviews;
    private List<String> allPhotos;
    private double averageScore = 0.0;

    public DetailedItem() { }

    public List<Parameter> getItemParameters() {
        return itemParameters;
    }

    public void setItemParameters(List<Parameter> itemParameters) {
        this.itemParameters = itemParameters;
    }

    public List<Review> getItemReviews() {
        return itemReviews;
    }

    public void setItemReviews(List<Review> itemReviews) {
        for (Review rev : itemReviews) {
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
