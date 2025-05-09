package com.vlasenko.subscriptions_example.dto;

import java.util.List;

/**
 * DTO for {@link com.vlasenko.subscriptions_example.domain.User}
 */
public record UserDto(
        long id,
        String username,
        String firstName,
        String middleName,
        String lastName,
        List<SubscriptionDto> subscriptions) {
}