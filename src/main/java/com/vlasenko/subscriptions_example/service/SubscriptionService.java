package com.vlasenko.subscriptions_example.service;

import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import com.vlasenko.subscriptions_example.mapper.SubscriptionMapper;
import com.vlasenko.subscriptions_example.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Subscription service
 */

@Service
public class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    /**
     * Get top subscriptions with limit
     *
     * @param limit of top subscriptions
     * @return List of SubscriptionDto
     */
    public List<SubscriptionDto> findTopSubscriptions(long limit) {

        logger.debug("Called findTopSubscriptions with limit: {}", limit);

        try {
            return subscriptionRepository
                    .findTopSubscriptions(limit)
                    .stream()
                    .map(subscriptionMapper::toSubscriptionDto)
                    .toList();
        } catch (Exception ex) {

            logger.info("Can't find top subscriptions with reason: {}", ex.getMessage());

            throw new RuntimeException("Can't find top subscriptions");
        }
    }
}
