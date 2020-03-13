package com.techstore.services.category;

import com.techstore.components.converter.CategoryConverter;
import com.techstore.components.converter.Converter;
import com.techstore.dto.CategoryDto;
import com.techstore.entities.Category;
import java.util.*;
import java.util.stream.Collectors;


public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepository();

    private final Converter<CategoryDto, Category> converter = new CategoryConverter();

    private static final CategoryServiceImpl instance = new CategoryServiceImpl();
    public static CategoryServiceImpl getInstance() { return instance; }

    private CategoryServiceImpl() { }


    public List<CategoryDto> getRootCategories() {
        return categoryRepository.findRoots()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getSubCategories(final Integer categoryId) {
        return categoryRepository.findByParentID(categoryId)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<CategoryDto>> getSubCategories(final List<CategoryDto> categories) {
        Map<Integer, List<CategoryDto>> categoriesTree = new TreeMap<>();

        List<Integer> categoriesIds = categories.stream()
                .map(CategoryDto::getId)
                .collect(Collectors.toList());


        List<Category> subCategories = categoryRepository.findByParentIDs(categoriesIds);

        for (final Integer id : categoriesIds) {
            categoriesTree.put(id, subCategories.stream()
                    .filter(category -> category.getParentCategory().getId().equals(id))
                    .map(converter::convert)
                    .collect(Collectors.toList()));
        }

        return categoriesTree;
    }
}
