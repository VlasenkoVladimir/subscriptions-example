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

    Optional<Subscription> findById(long id);

    List<Subscription> findTop3ByOrderByIdDesc();
}