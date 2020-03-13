package com.techstore.services.subscription;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Subscription;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


class SubscriptionRepository {

    void add(final Subscription subscription) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.persist(subscription);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    void remove(final Subscription subscription) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.remove(subscription);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    Optional<Subscription> findByEmail(final String email) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        final String query = String.format("SELECT s FROM Subscription s WHERE s.email='%s'", email);

        List<Subscription> subscriptions = entityManager.createQuery(query, Subscription.class).getResultList();

        Optional<Subscription> subscription = subscriptions.isEmpty() ?
                Optional.empty() : Optional.of(subscriptions.get(0));

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();

        return subscription;
    }
}
