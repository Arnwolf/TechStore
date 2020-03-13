package com.techstore.services.subscription;

import com.techstore.entities.Subscription;
import java.util.Optional;


public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository = new SubscriptionRepository();

    private final static SubscriptionServiceImpl instance = new SubscriptionServiceImpl();
    public static SubscriptionServiceImpl getInstance() { return instance; }

    private SubscriptionServiceImpl() { }


    public void subscribe(final String email) {
        final Optional<Subscription> subscription = subscriptionRepository.findByEmail(email);

        if (subscription.isEmpty())
            subscriptionRepository.add(new Subscription(email));
        else
            throw new RuntimeException("You are already subscribed!");
    }

    public void unsubscribe(final String email)
    {
        subscriptionRepository.findByEmail(email).ifPresent(subscriptionRepository::remove);
    }
}
