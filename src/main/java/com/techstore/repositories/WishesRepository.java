package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Wish;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class WishesRepository {

    public void add(final Wish entity) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void update(final Wish entity) { throw new UnsupportedOperationException(); }

    public void remove(final Wish entity) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        Wish toRemove = entityManager.merge(entity);
        entityManager.remove(toRemove);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Wish> findByUserId(final Integer userId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Wish> wishes = connection.createQuery(
                String.format("SELECT w FROM Wish w WHERE w.user=%d", userId),
                Wish.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return wishes;
    }

    public List<Wish> findByItemId(final Integer productId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Wish> wishes = connection.createQuery(
                String.format("SELECT w FROM Wish w WHERE w.product=%d", productId),
                Wish.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return wishes;
    }
}
