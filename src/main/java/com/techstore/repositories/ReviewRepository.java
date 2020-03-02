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
}
