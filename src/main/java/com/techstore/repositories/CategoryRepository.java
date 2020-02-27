package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Category;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CategoryRepository {

    public Category findByID(final Integer categoryId) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        Category category = entityManager.find(Category.class, categoryId);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();

        return category;
    }

    public List<Category> findByParentIDs(final Collection<Integer> categoryIds) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        List<Category> categories = entityManager.createQuery(
                String.format("SELECT c FROM Category c WHERE c.parentCategory IN (%s)",
                        categoryIds.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))),
                Category.class).getResultList();

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();

        return categories;
    }

    public List<Category> findAll() {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Category> categories = connection.createQuery(
                "SELECT c FROM Category c",
                Category.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
        return categories;
    }

    public List<Category> findRoots() {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Category> categories = connection.createQuery(
                "SELECT c FROM Category c WHERE c.parentCategory IS NULL",
                Category.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
        return categories;
    }

    public List<Category> findByParentID(final Integer parentCategory) {
        return findByParentIDs(Arrays.asList(parentCategory));
    }

    public void add(final Category newCategory) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.persist(newCategory);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void delete(final Category category) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        Category toRemove = entityManager.merge(category);
        entityManager.remove(toRemove);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Category update(final Category category) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        final Category merged = entityManager.merge(category);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();

        return merged;
    }
}
