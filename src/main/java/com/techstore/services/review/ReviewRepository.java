package com.techstore.services.review;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Review;
import javax.persistence.EntityManager;
import java.util.List;


class ReviewRepository {

    void add(final Review review) {
        EntityManager connection = ConnectionManager.getConnection();
        connection.getTransaction().begin();

        connection.persist(review);

        if (connection.getTransaction().isActive())
            connection.getTransaction().commit();

        connection.close();
    }
}
