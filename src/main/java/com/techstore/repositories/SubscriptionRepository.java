package com.techstore.repositories;

import com.techstore.connection.ConnectionManager;
import com.techstore.entities.Subscription;
import javax.persistence.EntityManager;
import java.util.List;


public class SubscriptionRepository {

    public void add(final Subscription subscription) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.persist(subscription);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void remove(final Subscription subscription) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        entityManager.remove(subscription);

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Subscription findByEmail(final String email) {
        EntityManager entityManager = ConnectionManager.getConnection();
        entityManager.getTransaction().begin();

        List<Subscription> subscriptions = entityManager.createQuery(
                String.format("SELECT s FROM Subscription s WHERE s.email='%s'", email),
                Subscription.class).getResultList();

        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();

        entityManager.close();

        return subscriptions.isEmpty() ? null : subscriptions.get(0);
    }
}
