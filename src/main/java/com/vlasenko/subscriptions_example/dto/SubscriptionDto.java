package com.vlasenko.subscriptions_example.dto;

import java.util.HashMap;

/**
 * DTO for {@link com.vlasenko.subscriptions_example.domain.Subscription}
 */
public record SubscriptionDto(
        long id,
        String subscriptionName,
        HashMap<String, String> parameters) {
}