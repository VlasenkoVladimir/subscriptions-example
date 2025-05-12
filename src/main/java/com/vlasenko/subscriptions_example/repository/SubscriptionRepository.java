package com.vlasenko.subscriptions_example.repository;

import com.vlasenko.subscriptions_example.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Subscription repository
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findBySubscriptionName(String subscriptionName);

    Optional<Subscription> findById(long id);

//    @Query(nativeQuery = true,
//            value = """
//           SELECT *
//           FROM subscriptions s
//           LEFT JOIN user_subscription us ON s.id = us.subscription_id
//           GROUP BY s.id
//           ORDER BY COUNT(*) DESC
//           LIMIT :limit
//       """)
default List<Subscription> findTopSubscriptions(@Param("limit") long limit) { return new ArrayList<>(); }
}