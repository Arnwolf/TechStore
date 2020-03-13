package com.techstore.dto;

import java.math.BigDecimal;

public class PreviewProductDto {
    public int id;
    public String manufacturer;
    public String name;
    public BigDecimal price;
    public BigDecimal discount;
    public String photo;
    public String categoryName;
    public boolean isNew;
    public int availability;

    public int getId() { return id; }
    public String getManufacturer() { return manufacturer; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getDiscount() { return discount; }
    public String getPhoto() { return photo; }
    public String getCategoryName() { return categoryName; }
    public boolean getIsNew() { return isNew; }
    public int getAvailability() { return availability; }
}
