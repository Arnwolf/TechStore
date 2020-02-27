package com.techstore.services;

import com.techstore.entities.Category;
import com.techstore.repositories.CategoryRepository;
import java.util.*;
import java.util.stream.Collectors;


public class CategoriesService {
    private CategoryRepository categoryRepository = new CategoryRepository();

    private static CategoriesService instance = new CategoriesService();
    public static CategoriesService getInstance() { return instance; }

    private CategoriesService() { }

    private void addCategory(final Category category) {
        try {
            categoryRepository.add(category);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new RuntimeException("SQL Problems");
        }
    }

    private void removeCategory(final Category category) {
        try {
            categoryRepository.delete(category);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new RuntimeException("SQL Problems");
        }
    }

    private void updateCategory(final Category category) {
        try {
            categoryRepository.update(category);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new RuntimeException("SQL Problems");
        }
    }

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


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getSearchedCategories(final Map<String, String> paramMap) {
        final String categoryID = paramMap.get("categoryID");

        if (!categoryID.isEmpty()) {
            return getSubCategories(Integer.parseInt(categoryID));
        } else
            return getRootCategories();
    }
}
