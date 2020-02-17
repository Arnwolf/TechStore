package com.techstore.entities;

public class Category {
    private String name;
    private String id;
    private String parentCategoryId;

    public Category() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentCategoryID(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    @Override
    public String toString() {
        return String.format("ID : %s \n" +
                "Name : %s \n" +
                "ParentCategoryID : %s\n", id, name, parentCategoryId);
    }

}
