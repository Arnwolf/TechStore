package com.techstore.components.converter;

import com.techstore.dto.CategoryDto;
import com.techstore.entities.Category;


public class CategoryConverter implements Converter<CategoryDto, Category> {

    @Override
    public CategoryDto convert(final Category toConvert) {
        CategoryDto dto = new CategoryDto();
        dto.id = toConvert.getId();
        dto.name = toConvert.getName();

        if (toConvert.getParentCategory() != null)
            dto.parentId = toConvert.getParentCategory().getId();

        return dto;
    }
}
