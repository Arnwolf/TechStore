package com.techstore.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Item {
    private String id;
    private String manufacturer;
    private String name;
    private BigDecimal price;
    private String category;
    private String mainPhoto;
    private BigDecimal discount;
    private String categoryId;
    private int availability;
    private boolean newItem;

    public void setId(final String id) { this.id = id; }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public void setAvailability(int availability) {
        this.availability = availability;
    }
    public void setNewItem(boolean aNew) {
        newItem = aNew;
    }

    public Item() {}

    public String getId() {
        return id;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
    public String getMainPhoto() { return mainPhoto; }
    public BigDecimal getDiscount() {
        return discount;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public int getAvailability() {
        return availability;
    }
    public boolean getNewItem() {
        return newItem;
    }

    @Override
    public String toString() {
        return String.format(
                " ID : %s " +
                " Name : %s " +
                " Price : %f" +
                " CategoryID : %s" +
                " Discount : %f " +
                " Availability : %d " +
                " MainPhoto : %s", id, manufacturer + name, price, categoryId, discount, availability, mainPhoto);
    }
}
