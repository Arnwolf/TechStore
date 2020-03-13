package com.techstore.services.user;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


public class UserRepository {

    void add(final User user) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.persist(user);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    User update(final User user) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        User merged = connection.merge(user);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return merged;
    }

    Optional<User> findByEmail(final String email) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final String query = String.format("SELECT user FROM User user WHERE user.email='%s'", email);

        List<User> users = connection.createQuery(query, User.class).getResultList();
        Optional<User> user = users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return user;
    }

    boolean existsByEmail(final String email) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final String query = String.format("SELECT user FROM User user WHERE user.email='%s'", email);

        List<User> users = connection.createQuery(query, User.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return !users.isEmpty();
    }

    Optional<User> findByHashedId(final String hashedId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final String query = String.format("SELECT user FROM User user WHERE user.hashedId='%s'", hashedId);

        List<User> users = connection.createQuery(query, User.class).getResultList();

        Optional<User> user = users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return user;
    }

    boolean existsByName(final String userName) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final String query = String.format("SELECT user FROM User user WHERE user.name='%s'", userName);

        List<User> users = connection.createQuery(query, User.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return !users.isEmpty();
    }

    public Optional<User> findById(final int userId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        Optional<User> user = Optional.of(connection.find(User.class, userId));

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return user;
    }
}
