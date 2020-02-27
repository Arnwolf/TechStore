package com.techstore.services;

import com.techstore.entities.Subscription;
import com.techstore.repositories.SubscriptionRepository;


public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository = new SubscriptionRepository();

    private static SubscriptionService instance = new SubscriptionService();
    public static SubscriptionService getInstance() { return instance; }

    private SubscriptionService() { }

    public void subscribe(final String email) {
        final Subscription subscription = subscriptionRepository.findByEmail(email);
        if (subscription == null)
            subscriptionRepository.add(new Subscription(email));
        else
            throw new RuntimeException("You are already subscribed!");
    }

    public void unsubscribe(final String email) {
        Subscription subscription = subscriptionRepository.findByEmail(email);

        if (subscription != null)
            subscriptionRepository.remove(subscription);
    }

    public Subscription getSubscription(final String email) { return subscriptionRepository.findByEmail(email); }
}
