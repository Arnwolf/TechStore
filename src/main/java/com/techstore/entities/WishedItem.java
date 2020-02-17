package com.techstore.entities;

public class WishedItem extends Item {
    private String userId = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
