package com.vlasenko.subscriptions_example.mapper;

import com.vlasenko.subscriptions_example.domain.Subscription;
import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Subscription mapper
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    Subscription toEntity(SubscriptionDto subscriptionDto);

    SubscriptionDto toSubscriptionDto(Subscription subscription);

    Subscription updateWithNull(SubscriptionDto subscriptionDto, @MappingTarget Subscription subscription);
}