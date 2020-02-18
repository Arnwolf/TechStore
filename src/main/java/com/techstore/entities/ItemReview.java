package com.techstore.entities;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ItemReview {
    private String userId = null;
    private String userName = null;
    private String description = null;
    private int rating = 0;
    private LocalDateTime datetime = null;
    private String itemId = null;

    public ItemReview() {}

    public String getUserID() {
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getItemID() {
        return itemId;
    }

    public void setItemID(String itemID) {
        this.itemId = itemID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
