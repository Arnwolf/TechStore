package com.techstore.specifications.categories;

import com.techstore.specifications.SqlSpecification;

public class CategorySpecificationByID implements SqlSpecification {
    private int categoryID;

    public CategorySpecificationByID(final int categoryID) {
        if (categoryID > 0)
            this.categoryID = categoryID;
    }

    @Override
    public String toSql() {
        return String.format("SELECT id, name, parent_category_id FROM categories WHERE id=%s", categoryID);
    }
}
