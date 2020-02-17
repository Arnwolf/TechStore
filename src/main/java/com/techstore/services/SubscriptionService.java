package com.techstore.services;

import com.techstore.repositories.Repository;
import com.techstore.repositories.SubscriptionRepository;
import com.techstore.specifications.SqlSpecification;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriptionService {
    private Repository<String> subscriptionRepository;
    private final static Logger LOG = Logger.getLogger(SubscriptionService.class.getName());

    private static SubscriptionService instance;

    public static void init() {
        instance = new SubscriptionService(new SubscriptionRepository());
    }

    public static SubscriptionService getInstance() {
        return instance;
    }

    private SubscriptionService(final Repository<String> subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    private void addSubscription(final String email) {
        try {
            subscriptionRepository.add(email);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private void removeSubscription(final String email) {
        try {
            subscriptionRepository.remove(email);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private List<String> listSubscription(final SqlSpecification spec) {
        try {
            return subscriptionRepository.query(spec);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public List<String> getSubscriptions() {
        return listSubscription(() -> "SELECT * FROM subscription");
    }

    private String getSubscription(final String email) {
        List<String> subscriptions = listSubscription(() ->
                String.format("SELECT email FROM subscription WHERE email='%s'", email));

        return subscriptions.isEmpty() ? null : subscriptions.get(0);
    }

    public void subscribe(final String email) {
        final String subscription = getSubscription(email);
        if (subscription == null)
            addSubscription(email);
        else
            throw new RuntimeException("You are already subscribed!");
    }

    public void unsubscribe(final String email) {
        removeSubscription(email);
    }
}
