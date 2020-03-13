package com.techstore.services.subscription;

public interface SubscriptionService {
    void subscribe(final String email);
    void unsubscribe(final String email);
}
