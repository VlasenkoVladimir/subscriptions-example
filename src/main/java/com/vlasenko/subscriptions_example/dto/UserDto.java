package com.vlasenko.subscriptions_example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vlasenko.subscriptions_example.domain.User;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link User}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        long id,
        String username,
        String firstName,
        String middleName,
        String lastName,
        List<SubscriptionDto> subscriptions) implements Serializable {
}