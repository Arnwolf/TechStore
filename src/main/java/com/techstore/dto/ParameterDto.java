package com.techstore.dto;

public class ParameterDto {
    public int id;
    public int categoryParameterId;
    public int productId;
    public boolean isSearchable;
    public String parameterName;
    public String productValue;
    public String parameterSymbol;

    public int getId() { return id; }
    public int getCategoryParameterId() { return categoryParameterId; }
    public int getProductId() { return productId; }
    public boolean getIsSearchable() { return isSearchable; }
    public String getParameterName() { return parameterName; }
    public String getProductValue() { return productValue; }
    public String getParameterSymbol() { return parameterSymbol; }
}
