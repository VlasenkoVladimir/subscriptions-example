package com.vlasenko.subscriptions_example.mapper;

import com.vlasenko.subscriptions_example.domain.Subscription;
import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import org.mapstruct.*;

/**
 * Subscription mapper
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    Subscription toEntity(SubscriptionDto subscriptionDto);

    SubscriptionDto toDto(Subscription subscription);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subscription partialUpdate(SubscriptionDto subscriptionDto, @MappingTarget Subscription subscription);
}