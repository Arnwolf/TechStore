package com.techstore.services;

import com.techstore.entities.Category;
import com.techstore.repositories.CategoryRepository;
import com.techstore.repositories.Repository;
import com.techstore.specifications.SqlSpecification;
import com.techstore.specifications.categories.CategorySpecificationByParent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriesService {
    private Repository<Category> categoryRepository;
    private final static Logger LOG = Logger.getLogger(CategoriesService.class.getName());

    private static CategoriesService instance = new CategoriesService(new CategoryRepository());

    public static CategoriesService getInstance() {
        return instance;
    }

    private CategoriesService(final Repository<Category> categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private void addCategory(final Category category) {
        try {
            categoryRepository.add(category);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private void removeCategory(final Category category) {
        try {
            categoryRepository.remove(category);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private void updateCategory(final Category category) {
        try {
            categoryRepository.update(category);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private List<Category> getCategories(final SqlSpecification specification) {
        try {
            return categoryRepository.query(specification);
        } catch (final Exception exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }


    public List<Category> getRootCategories() {
        return getCategories(new CategorySpecificationByParent("0"));
    }

    public List<Category> getSubCategories(final String categoryID) {
        return getCategories(new CategorySpecificationByParent(categoryID));
    }

    public Map<String, List<Category>> getSubCategories(final List<Category> categories) {
        Map<String, List<Category>> subCategories = new TreeMap<>();

        for (Category category : categories)
            subCategories.put(category.getId(), getSubCategories(category.getId()));

        return subCategories;
    }


    public List<Category> getAllCategories() {
        return getCategories(() -> "SELECT * FROM categories");
    }

    public List<Category> getSearchedCategories(final Map<String, String> paramMap) {
        final String categoryID = paramMap.get("categoryID");

        if (!categoryID.isEmpty()) {
            return getSubCategories(categoryID);
        } else
            return new ArrayList<>();
    }
}
