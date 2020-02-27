package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class UserRepository {

    public void add(final User user) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.persist(user);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public void update(final User user) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.merge(user);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public void remove(final User user) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.remove(user);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public List<User> findAll() {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<User> users = connection.createQuery("SELECT user FROM User user",
                User.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return users;
    }

    public List<User> findByEmail(final String email) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<User> users = connection.createQuery(
                String.format("SELECT user FROM User user WHERE user.email='%s'", email),
                User.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return users;
    }

    public List<User> findByHashedId(final String hashedId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<User> users = connection.createQuery(
                String.format("SELECT user FROM User user WHERE user.hashedId='%s'", hashedId),
                User.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return users;
    }

    public List<User> findByName(final String userName) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<User> users = connection.createQuery(
                String.format("SELECT user FROM User user WHERE user.name='%s'", userName),
                User.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return users;
    }
}
