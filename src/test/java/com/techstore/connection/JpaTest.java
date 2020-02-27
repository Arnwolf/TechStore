package com.techstore.connection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class JpaTest {
    private EntityManager connection;

    @Before
    public void init() {
        connection =
                Persistence.createEntityManagerFactory("tech-store-unit")
                        .createEntityManager();
        connection.getTransaction().begin();
    }

    @Test
    public void testConnection() { Assert.assertTrue(connection.isOpen()); }

    @After
    public void destroy() {
        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.getEntityManagerFactory().close();
        connection.close();
    }
}
