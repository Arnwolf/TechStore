package com.techstore.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UserTest {
    private EntityManager connection;

    @Before
    public void init() {
        connection =
                Persistence.createEntityManagerFactory("tech-store-unit")
                        .createEntityManager();
        connection.getTransaction().begin();
    }


    @Test
    public void testUsers() {
        User testUser = new User();
        testUser.setName("TEST USER");
        testUser.setSubscribed(false);
        testUser.setPhoneNumber("000000000");
        testUser.setStreet("TEST STREET");
        testUser.setPass("TEST");
        testUser.setCity("TEST CITY");
        testUser.setEmail("TEST@mail.com");
        testUser.setPass("TEST PASSWORD");

        connection.persist(testUser);
        connection.getTransaction().commit();

        connection.getTransaction().begin();
        connection.remove(testUser);
    }


    @After
    public void destroy() {
        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.getEntityManagerFactory().close();
        connection.close();
    }
}
