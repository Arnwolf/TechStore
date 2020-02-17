package com.techstore.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Item {
    private String id;
    private String manufacturer  = null;
    private String name  = null;
    private BigDecimal price = null;
    private String category  = null;
    private String mainPhoto = null;
    private BigDecimal discount = null;
    private String categoryId = null;
    private int availability = 0;
    private boolean newItem = false;

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
    public boolean equals(Object obj) {
        if (obj == null)
            return false;


        BigDecimal d = new BigDecimal(1.0);
        d.multiply(new BigDecimal(2));

        if (obj != this)
            return false;

        if (obj.getClass() != Item.class)
            return false;

        return ((Item) obj).getId().equals(id);
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
