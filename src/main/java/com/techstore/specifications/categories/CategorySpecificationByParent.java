package com.techstore.specifications.categories;

import com.techstore.specifications.SqlSpecification;

import java.util.Collection;

public class CategorySpecificationByParent implements SqlSpecification {
    private Collection<String> categoriesId;

    public CategorySpecificationByParent(final Collection<String> categoriesId) {
        this.categoriesId = categoriesId;
    }

    @Override
    public String toSql() {
        return String.format("SELECT id, name, parent_category_id FROM categories WHERE parent_category_id IN (%s)",
                String.join(",", categoriesId));
    }
}
