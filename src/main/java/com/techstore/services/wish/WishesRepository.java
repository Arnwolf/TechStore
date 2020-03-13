package com.techstore.services.wish;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Wish;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


class WishesRepository {

    void add(final Wish entity) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    void remove(final Wish entity) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        Wish toRemove = entityManager.merge(entity);
        entityManager.remove(toRemove);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    List<Wish> findByUserId(final Integer userId) {
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

    Optional<Wish> findByProductId(final int productId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        final String query = String.format("SELECT w FROM Wish w WHERE w.product=%d", productId);

        List<Wish> wishes = connection.createQuery(query, Wish.class).getResultList();
        Optional<Wish> wish = wishes.isEmpty() ? Optional.empty() : Optional.of(wishes.get(0));

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return wish;
    }

    Optional<Wish> findById(final int wishId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        Optional<Wish> wish = Optional.of(connection.find(Wish.class, wishId));

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return wish;
    }

    List<Wish> findPopular() {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Wish> wishes = connection.createQuery("SELECT p, count(p) AS counter FROM Product p JOIN Wish w ON w.product=p GROUP BY p.id",
                Wish.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return wishes;
    }
}
