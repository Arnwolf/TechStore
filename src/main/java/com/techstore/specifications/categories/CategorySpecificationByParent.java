package com.techstore.specifications.categories;

import com.techstore.specifications.SqlSpecification;

public class CategorySpecificationByParent implements SqlSpecification {
    private String categoryID;

    public CategorySpecificationByParent(final String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toSql() { //TODO: join categories on categories
        return String.format("SELECT id, name, parent_category_id FROM categories WHERE parent_category_id=%s", categoryID);
    }
}
