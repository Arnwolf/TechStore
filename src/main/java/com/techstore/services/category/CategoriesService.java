package com.techstore.services.category;

import com.techstore.entities.Category;
import java.util.*;
import java.util.stream.Collectors;


public class CategoriesService {
    private final CategoryRepository categoryRepository = new CategoryRepository();

    private static final CategoriesService instance = new CategoriesService();
    public static CategoriesService getInstance() { return instance; }

    private CategoriesService() { }


    public List<Category> getRootCategories() { return categoryRepository.findRoots(); }

    public List<Category> getSubCategories(final Integer categoryID) {
        return categoryRepository.findByParentID(categoryID);
    }

    public Map<Integer, List<Category>> getSubCategories(final List<Category> categories) {
        Map<Integer, List<Category>> categoriesTree = new TreeMap<>();

        List<Integer> categoriesIds = new ArrayList<>(categories.size());
        for (Category category : categories) {
            categoriesIds.add(category.getId());
        }

        List<Category> subCategories = categoryRepository.findByParentIDs(categoriesIds);

        for (Integer id : categoriesIds) {
            categoriesTree.put(id, subCategories.stream()
                    .filter(category -> category.getParentCategory().getId().equals(id))
                    .collect(Collectors.toList()));
        }

        return categoriesTree;
    }

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public List<Category> getSearchedCategories(final Map<String, String> paramMap) {
        final String categoryID = paramMap.get("categoryID");

        if (!categoryID.isEmpty()) {
            return getSubCategories(Integer.parseInt(categoryID));
        } else
            return getRootCategories();
    }
}
