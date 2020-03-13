package com.techstore.services.category;

import com.techstore.dto.CategoryDto;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<CategoryDto> getRootCategories();
    List<CategoryDto> getSubCategories(final Integer categoryId);
    Map<Integer, List<CategoryDto>> getSubCategories(final List<CategoryDto> categories);
}
