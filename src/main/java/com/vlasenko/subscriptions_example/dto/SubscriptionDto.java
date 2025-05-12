package com.vlasenko.subscriptions_example.dto;

import com.vlasenko.subscriptions_example.domain.Subscription;

import java.io.Serializable;
import java.util.HashMap;

/**
 * DTO for {@link Subscription}
 */
public record SubscriptionDto(
        long id,
        String subscriptionName,
        HashMap<String, String> parameters) implements Serializable {
}