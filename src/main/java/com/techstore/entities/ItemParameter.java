package com.techstore.entities;

public class ItemParameter {
    private String id = null;
    private String itemId = null;
    private String itemDetailName = null;
    private String itemDetailValue = null;
    private String itemDetailSymbol = null;
    private String categoryDetailId = null;
    private boolean searchable = false;
    private boolean changeable = false;

    public ItemParameter() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDetailName() {
        return itemDetailName;
    }

    public void setItemDetailName(String itemDetailName) {
        this.itemDetailName = itemDetailName;
    }

    public String getItemDetailValue() {
        return itemDetailValue;
    }

    public void setItemDetailValue(String itemDetailValue) {
        this.itemDetailValue = itemDetailValue;
    }

    public String getItemDetailSymbol() {
        return itemDetailSymbol;
    }

    public void setItemDetailSymbol(String itemDetailSymbol) {
        this.itemDetailSymbol = itemDetailSymbol;
    }

    public String getCategoryDetailId() {
        return categoryDetailId;
    }

    public void setCategoryDetailId(String categoryDetailId) {
        this.categoryDetailId = categoryDetailId;
    }

    @Override
    public String toString() {
        return String.format("ID : %s " +
                "ItemID : %s " +
                "DetailName : %s " +
                "DetailValue : %s "  +
                "DetailSymbol : %s", id, itemId, itemDetailName, itemDetailValue, itemDetailSymbol);
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }
}
