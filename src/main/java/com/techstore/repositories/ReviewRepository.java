package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Review;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class ReviewRepository {

    public void add(final Review review) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.persist(review);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public void update(final Review review) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.merge(review);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public void remove(final Review review) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.remove(review);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }

    public List<Review> findAll() {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Review> reviews = connection.createQuery("SELECT review FROM Review review",
                Review.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return reviews;
    }

    public List<Review> findByProductId(final Integer itemId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Review> reviews = connection.createQuery(
                String.format("SELECT review FROM Review review WHERE review.item=%d", itemId),
                Review.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return reviews;
    }

    public List<Review> findByUserId(final Integer userId) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        List<Review> reviews = connection.createQuery(
                String.format("SELECT review FROM Review review WHERE review.user=%d", userId),
                Review.class).getResultList();

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();

        return reviews;
    }
}
