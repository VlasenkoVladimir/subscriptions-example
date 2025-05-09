package com.vlasenko.subscriptions_example.repository;

import com.vlasenko.subscriptions_example.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Subscription repository
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findBySubscriptionName(String subscriptionName);

    List<Subscription> getSubscriptionsByUserId(long userId);

    Optional<Subscription> findById(long id);

    List<Subscription> findTopWithQuantity(long quantity);
}