package com.techstore.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CategoryTest {
    private EntityManager connection;

    @Before
    public void init() {
        connection =
                Persistence.createEntityManagerFactory("tech-store-unit")
                        .createEntityManager();
        connection.getTransaction().begin();
    }

    @Test
    public void testCategories() {
        Category testCategory = new Category();

        connection.persist(testCategory);
        connection.getTransaction().commit();

        connection.getTransaction().begin();
        connection.remove(testCategory);
    }

    @After
    public void destroy() {
        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.getEntityManagerFactory().close();
        connection.close();
    }
}
