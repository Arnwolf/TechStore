package com.techstore.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnectionManager {
    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("tech-store-unit");

    public static EntityManager getConnection() { return factory.createEntityManager(); }

    private ConnectionManager() {}

    public static void closeFactory() { factory.close(); }
}
