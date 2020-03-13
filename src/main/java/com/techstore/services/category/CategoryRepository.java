package com.techstore.services.category;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Category;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


class CategoryRepository {

    List<Category> findByParentIDs(final Collection<Integer> categoryIds) {
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

    List<Category> findAll() {
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

    List<Category> findRoots() {
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

    List<Category> findByParentID(final Integer parentCategory) {
        return findByParentIDs(Arrays.asList(parentCategory));
    }
}
